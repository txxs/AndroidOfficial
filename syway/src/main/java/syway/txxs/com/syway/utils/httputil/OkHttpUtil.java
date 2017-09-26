package syway.txxs.com.syway.utils.httputil;

import android.service.carrier.CarrierMessagingService;
import android.util.Log;

import com.squareup.okhttp.Call;
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

    /**
     * 同步的Get请求
     * @param url
     * @return Response
     */
    private Response getSync(String url) throws IOException {
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        Response execute = call.execute();
        return execute;
    }

    /**
     * 同步的Get请求
     * @param url
     * @return 字符串
     */
    private String getSyncString(String url) throws IOException {
        Response execute = getSync(url);
        return execute.body().string();
    }

    /**
     * 异步的get请求
     * @param url
     * @param callback
     
    private void getAsync(String url, final ResultCallback callback) {
        final Request request = new Request.Builder()
                .url(url)
                .build();
        deliveryResult(callback, request);
    }     */
}
