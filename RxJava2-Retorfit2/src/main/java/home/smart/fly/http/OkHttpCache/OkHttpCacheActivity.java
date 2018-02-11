package home.smart.fly.http.OkHttpCache;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.OnClick;
import home.smart.fly.http.R;
import home.smart.fly.http.R2;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpCacheActivity extends AppCompatActivity {
    private final String TAG = "OkHttpCacheActivity";
        private final String BASE_URL = "http://gank.io/api/data/Android/10/1";
//    private final String BASE_URL = "https://www.baidu.com";


    private final int TIME = 30;

    private final int ONE_M = 1024 * 1024;

    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http_cache);
        ButterKnife.bind(this);
    }

    @OnClick({R2.id.basic, R2.id.cache, R2.id.log})
    public void OnClick(View v) {
        int id = v.getId();
        if (id == R.id.basic) {
            basicUse();
        } else if (id == R.id.log) {
            logUse();
        } else if (id == R.id.cache) {
            cacheUse();
        }
    }

    class CacheInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originResponse = chain.proceed(chain.request());

            originResponse = originResponse
                    .newBuilder()
                    .removeHeader("pragma")
                    .addHeader("Cache-Control", "max-age=60")
                    .build();

            return originResponse;
        }
    }

    private void cacheUse() {
        String cacheDir = getExternalCacheDir().getAbsolutePath();
        File mFile = new File(cacheDir, "android_cache");
        final Cache mCache = new Cache(mFile, 10 * ONE_M);


        OkHttpClient mClient =
                new OkHttpClient.Builder()
                        .readTimeout(10, TimeUnit.SECONDS)
                        .writeTimeout(10, TimeUnit.SECONDS)
                        .retryOnConnectionFailure(true)
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .addNetworkInterceptor(new CacheInterceptor())
                        .cache(mCache)
                        .build();
        Request mRequest = new Request.Builder()
                .url(BASE_URL)
                .build();

        final Call mCall = mClient.newCall(mRequest);
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: e==" + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "onResponse: body==" + response.body().string());
                Log.e(TAG, "onResponse: netResponse==" + response.networkResponse());
                Log.e(TAG, "onResponse: cacheResponse==" + response.cacheResponse());
                Log.e(TAG, "onResponse: cacheControl==" + response.cacheControl().toString());
                Log.e(TAG, "onResponse: threadName==" + Thread.currentThread().getName());


            }
        });
        Call mCall1 = mClient.newCall(mRequest);
        mCall1.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: e==" + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "onResponse1: body==" + response.body().string());
                Log.e(TAG, "onResponse1: netResponse==" + response.networkResponse());
                Log.e(TAG, "onResponse1: cacheResponse==" + response.cacheResponse());
                Log.e(TAG, "onResponse1: cacheControl==" + response.cacheControl().toString());
                Log.e(TAG, "onResponse1: threadName==" + Thread.currentThread().getName());


            }
        });


    }

    private void logUse() {

        HttpLoggingInterceptor mLoggingInterceptor = new HttpLoggingInterceptor();
        mLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient mClient = new OkHttpClient.Builder()
                .connectTimeout(TIME, TimeUnit.SECONDS)
                .readTimeout(TIME, TimeUnit.SECONDS)
                .writeTimeout(TIME, TimeUnit.SECONDS)
                .addInterceptor(mLoggingInterceptor)
                .build();
        Request mRequest = new Request.Builder()
                .url(BASE_URL)
                .build();

        Call mCall = mClient.newCall(mRequest);
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    private void basicUse() {
        OkHttpClient.Builder mBuilder = new OkHttpClient.Builder();
        mBuilder.connectTimeout(TIME, TimeUnit.SECONDS)
                .readTimeout(TIME, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);

        OkHttpClient mClient = mBuilder.build();
        Request mRequest = new Request.Builder()
                .url(BASE_URL)
                .method("GET", null)
                .build();
        Call mCall = mClient.newCall(mRequest);
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: call==" + call.isExecuted());
                Log.e(TAG, "onFailure: e===" + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "onResponse: call==" + call.toString());
                Log.e(TAG, "onResponse: response==" + response.toString());
                Log.e(TAG, "onResponse: response.head=" + response.headers().toString());
                Log.e(TAG, "onResponse: response.body=" + response.body().string());
            }
        });

    }
}