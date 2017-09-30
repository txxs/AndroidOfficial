package syway.txxs.com.syway.utils.httputil;

import okhttp3.Request;

/**
 * Created by jianghuimin on 2017/9/30.
 */

public  abstract class ResultCallback<T> {

    public abstract void onError(Request request, Exception e);

    public abstract void onResponse(T response);
}
