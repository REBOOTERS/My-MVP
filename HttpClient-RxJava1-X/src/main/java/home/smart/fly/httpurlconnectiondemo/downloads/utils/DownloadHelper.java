package home.smart.fly.httpurlconnectiondemo.downloads.utils;

import android.os.Handler;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by engineer on 2017/3/25.
 */

public class DownloadHelper {


    public static OkHttpClient mClient = new OkHttpClient();

    private static Call mCall;

    public static void startDownload(int startPoint, int endPoint, Handler mHandler) {
        Request request = new Request.Builder()
                .url(Constants.PACKAGE_URL)
                .header("RANGE", "bytes=" + startPoint + "-" + endPoint)
                .build();
        mCall = mClient.newCall(request);
        mCall.enqueue(new OkHttpCallback(startPoint, mHandler));
    }

    public static void startDownload(int startPoint, Handler mHandler) {
        Request request = new Request.Builder()
                .url(Constants.PACKAGE_URL)
                .header("RANGE", "bytes=" + startPoint + "-")
                .build();
        mCall = mClient.newCall(request);
        mCall.enqueue(new OkHttpCallback(startPoint, mHandler));
    }

    public static void cancelDownload() {
        if (mCall != null) {
            mCall.cancel();
        }
    }

}
