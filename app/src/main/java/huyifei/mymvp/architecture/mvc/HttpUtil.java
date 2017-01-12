package huyifei.mymvp.architecture.mvc;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by rookie on 2017/1/12.
 */

public class HttpUtil {
    private static OkHttpClient client = new OkHttpClient();

    /**
     * 简单封装的一次OKHTTP GET请求
     *
     * @param url
     * @param mCallback
     */
    public static void HttpGet(String url, Callback mCallback) {
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .build();
        client.newCall(request).enqueue(mCallback);
    }


}
