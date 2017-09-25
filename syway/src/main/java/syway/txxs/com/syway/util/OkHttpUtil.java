package syway.txxs.com.syway.util;

import android.util.Log;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by jianghuimin on 2017/9/25.
 */

public class OkHttpUtil {

    final static OkHttpClient client = new OkHttpClient();

    public static void getRequest() {
        final Request request=new Request.Builder()
                .get()
                .tag("User")
                .url("120.77.222.5:8080/user")
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        Log.i("WY","打印GET响应的数据：" + response.body().string());
                    } else {
                        throw new IOException("Unexpected code " + response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * post请求方法
     */
    public static void postRequest() {

        RequestBody formBody = new FormEncodingBuilder()
                .add("","")
                .build();

        final Request request = new Request.Builder()
                .url("http://www.wooyun.org")
                .post(formBody)
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        Log.i("WY","打印POST响应的数据：" + response.body().string());
                    } else {
                        throw new IOException("Unexpected code " + response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
