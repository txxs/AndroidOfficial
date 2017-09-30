package syway.txxs.com.syway.utils.httputil;



import android.os.Handler;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by jianghuimin on 2017/9/25.
 * OKhttpUtil git
 */

public class OkHttpUtil {

    public final static int CONNECT_TIMEOUT = 60;
    public final static int READ_TIMEOUT = 100;
    public final static int WRITE_TIMEOUT = 60;

    private static final OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static Handler handler = new Handler();

    static {
        client.newBuilder().readTimeout(READ_TIMEOUT, TimeUnit.SECONDS); // 读取超时
        client.newBuilder().writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS); // 写入超时
        client.newBuilder().connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS); // 连接超时
    }

    /**
     * 同步get
     * @param address
     * @return
     * @throws IOException
     */
    public static String syncGet(String address) throws IOException {
        Request request = new Request.Builder()
                .get()
                .url(address)
                .build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    /**
     * 同步post
     * @param address
     * @param jsonStr
     * @return
     * @throws IOException
     */
    public static String syncPost(String address, String jsonStr) throws IOException {
        RequestBody body = RequestBody.create(JSON, jsonStr);
        Request request = new Request.Builder()
                .post(body)
                .url(address)
                .build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    /**
     * 异步get
     * @param address
     * @param callback
     */
    public static void asynGet(String address, Callback callback) {
        Request request = new Request.Builder()
                .get()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }

    /**
     * 异步post
     * @param address
     * @param jsonStr
     * @param callback
     */
    public static void asynPost(String address, String jsonStr, Callback callback) {
        RequestBody body = RequestBody.create(JSON, jsonStr);
        Request request = new Request.Builder()
                .post(body)
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }


    /**
     * 异步get 可以访问主线程
     * @param address
     * @param callback
     */
    public static void asynGet(String address, OkHttpUtil.ResultCallback callback) {
        Request request = new Request.Builder()
                .get()
                .url(address)
                .build();
        deliveryResult(request, callback);
    }

    /**
     * 异步post 可以访问主线程
     * @param address
     * @param jsonStr
     * @param callback
     */
    public static void asynPost(String address, String jsonStr, ResultCallback callback) {
        RequestBody body = RequestBody.create(JSON, jsonStr);
        Request request = new Request.Builder()
                .post(body)
                .url(address)
                .build();
        deliveryResult(request, callback);
    }

    private static void deliveryResult(final Request request, final ResultCallback callback) {
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                sendFailedStringCallback(request, e, callback);
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                sendSuccessResultCallback(response.body().string(), callback);
            }
        });
    }

    private static void sendFailedStringCallback(final Request request, final Exception e, final ResultCallback callback) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null)
                    callback.onError(request, e);
            }
        });
    }

    private static void sendSuccessResultCallback(final Object object, final ResultCallback callback) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onResponse(object);
                }
            }
        });
    }

    public static abstract class ResultCallback<T> {
        public abstract void onError(Request request, Exception e);

        public abstract void onResponse(T response);
    }
}