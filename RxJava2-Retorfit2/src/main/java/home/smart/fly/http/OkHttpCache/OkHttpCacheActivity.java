package home.smart.fly.http.OkHttpCache;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import home.smart.fly.http.R;
import home.smart.fly.http.R2;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpCacheActivity extends AppCompatActivity {
    private final String TAG = "OkHttpCacheActivity";
    private final String BASE_URL = "http://gank.io/api/data/Android/10/1";

    @BindView(R2.id.basic)
    Button basic;

    private final int TIME = 30;

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
        }
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