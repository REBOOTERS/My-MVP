package home.smart.fly.httpurlconnectiondemo.retrofit2;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import home.smart.fly.httpurlconnectiondemo.R;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by rookie on 2016/11/3.
 * <p>
 * Retrofit2.0 使用
 */

public class Retrofit2DemoActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = Retrofit2DemoActivity.class.getSimpleName();
    private final String BASE_URL = "https://api.github.com/";
    private final String DOWNLOAD_URL = "http://dl.bizhi.sogou.com/images/2015/06/26/1214911.jpg";
    private final String FILE_PATH = Environment.getExternalStorageDirectory().getPath() + File.separator + "test.jpg";
    private Context mContext;

    private TextView tv;
    private ProgressBar loading;
    private ImageView imageView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;


        setContentView(R.layout.activity_retrofit_demo);
        tv = (TextView) findViewById(R.id.editText);
        loading = (ProgressBar) findViewById(R.id.loading);
        imageView = (ImageView) findViewById(R.id.image);
        findViewById(R.id.get).setOnClickListener(this);
        findViewById(R.id.get1).setOnClickListener(this);
        findViewById(R.id.get2).setOnClickListener(this);
        findViewById(R.id.get3).setOnClickListener(this);

    }

    private void SimpleRetrofit() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL);
        Retrofit retrofit = builder.client(httpClient.build()).build();
        GithubSimpleService simpleService = retrofit.create(GithubSimpleService.class);
        Call<ResponseBody> call = simpleService.getUser("mike");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                loading.setVisibility(View.GONE);
                try {
                    String result = response.body().string();
                    tv.setText(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                loading.setVisibility(View.GONE);
            }
        });
    }

    private void LazyRetrofit() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.client(httpClient.build()).build();
        GithubService service = retrofit.create(GithubService.class);
        Call<GithubBean> call = service.getUser("mike");
        call.enqueue(new Callback<GithubBean>() {
            @Override
            public void onResponse(Call<GithubBean> call, Response<GithubBean> response) {
                GithubBean bean = response.body();
                Toast.makeText(mContext, "mike's id is " + bean.getId(), Toast.LENGTH_SHORT).show();
                loading.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<GithubBean> call, Throwable t) {
                loading.setVisibility(View.GONE);
            }
        });
    }

    private void EasyRetrofit() {
        GithubService service = GenServiceUtil.createService(GithubService.class);
        Call<GithubBean> call = service.getUser("mike");
        call.enqueue(new Callback<GithubBean>() {
            @Override
            public void onResponse(Call<GithubBean> call, Response<GithubBean> response) {
                GithubBean bean = response.body();
                Toast.makeText(mContext, "mike's id is " + bean.getId(), Toast.LENGTH_SHORT).show();
                loading.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<GithubBean> call, Throwable t) {
                loading.setVisibility(View.GONE);
            }
        });
    }

    private void RxRetrofit() {
        GithubService service = GenServiceUtil.createService(GithubService.class);
        final Call<GithubBean> call = service.getUser("mike");
        final Observable myObserable = Observable.create(new Observable.OnSubscribe<GithubBean>() {
            @Override
            public void call(Subscriber<? super GithubBean> subscriber) {
                Response<GithubBean> bean = null;
                try {
                    bean = call.execute();
                    subscriber.onNext(bean.body());

                } catch (IOException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }

                subscriber.onCompleted();
            }
        });


        myObserable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GithubBean>() {
                    @Override
                    public void onCompleted() {
                        loading.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        loading.setVisibility(View.GONE);
                    }

                    @Override
                    public void onNext(GithubBean o) {

                        Toast.makeText(mContext, "id is " + o.getId(), Toast.LENGTH_SHORT).show();
                    }
                });

    }


    @Override
    public void onClick(View v) {
        loading.setVisibility(View.VISIBLE);
        int i = v.getId();
        if (i == R.id.get) {
            SimpleRetrofit();
        } else if (i == R.id.get1) {
            LazyRetrofit();
        } else if (i == R.id.get2) {
            EasyRetrofit();
        } else if (i == R.id.get3) {
            RxRetrofit();
        }
    }
}
