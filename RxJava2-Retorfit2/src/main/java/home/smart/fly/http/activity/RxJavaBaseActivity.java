package home.smart.fly.http.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import home.smart.fly.http.model.GankAndroid;
import home.smart.fly.http.model.GankApi;
import home.smart.fly.http.R;
import home.smart.fly.http.R2;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Rookie
 */
public class RxJavaBaseActivity extends AppCompatActivity {
    private static final String BASE_URL = "http://gank.io/api/";

    private static final String TAG = "RxJavaBaseActivity";
    @BindView(R2.id.basic1)
    Button mBasic1;
    @BindView(R2.id.basic2)
    Button mBasic2;
    @BindView(R2.id.basic3)
    Button mBasic3;
    @BindView(R2.id.basic4)
    Button mBasic4;
    @BindView(R2.id.basic5)
    Button mBasic5;
    @BindView(R2.id.logContent)
    TextView logContent;


    private StringBuilder sb = new StringBuilder();
    private CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_base);
        ButterKnife.bind(this);
        mCompositeDisposable = new CompositeDisposable();
    }

    @OnClick({R2.id.basic1, R2.id.basic2,
            R2.id.basic3, R2.id.basic4,
            R2.id.basic5, R2.id.basic6,
            R2.id.basic7})
    public void onClick(View v) {
        if (sb != null) {
            sb = null;
        }
        sb = new StringBuilder();
        logContent.setText("");

        if (v.getId() == R.id.basic1) {
            basicRxjava2();
        } else if (v.getId() == R.id.basic2) {
            basicRxjava2Chian();
        } else if (v.getId() == R.id.basic3) {
            consumer();
        } else if (v.getId() == R.id.basic4) {
            thread();
        } else if (v.getId() == R.id.basic5) {
            multiThread();
        } else if (v.getId() == R.id.basic6) {
            withRetrofit2();
        } else if (v.getId() == R.id.basic7) {
            withRetrofit2AndGson();
        }
    }

    private void withRetrofit2AndGson() {
        final OkHttpClient mClient = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        final Retrofit mRetrofit = new Retrofit.Builder()
                .client(mClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        GankApi mGankApi = mRetrofit.create(GankApi.class);
        Observable<GankAndroid> mAndroidObservable = mGankApi.getData("10/1");
        mCompositeDisposable.add(mAndroidObservable
                .subscribeOn(Schedulers.io())
                .map(gankAndroid -> gankAndroid.getResults().get(0))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(resultsEntity -> {
                    sb.append(resultsEntity.getCreatedAt()).append("\n")
                            .append(resultsEntity.getType()).append("\n")
                            .append(resultsEntity.getDesc()).append("\n")
                            .append(resultsEntity.getUrl()).append("\n")
                            .append(resultsEntity.getWho());

                    logContent.setText(sb.toString());
                }));

    }

    private void withRetrofit2() {
        OkHttpClient.Builder mBuilder = new OkHttpClient.Builder();
        mBuilder.readTimeout(10, TimeUnit.SECONDS);
        mBuilder.connectTimeout(30, TimeUnit.SECONDS);

        HttpLoggingInterceptor mLoggingInterceptor = new HttpLoggingInterceptor();
        mLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        mBuilder.addInterceptor(mLoggingInterceptor);

        OkHttpClient mClient = mBuilder.build();

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(BASE_URL);
        builder.client(mClient);

        Retrofit mRetrofit = builder.build();

        final GankApi mGankApi = mRetrofit.create(GankApi.class);
        final Call<ResponseBody> mCall = mGankApi.getJson("10/1");

        Observable.create((ObservableOnSubscribe<ResponseBody>) e -> e.onNext(mCall.execute().body()))
                .map(ResponseBody::string)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe: d=" + d);
                    }

                    @Override
                    public void onNext(String s) {
                        logContent.setText(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        logContent.setText(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }


    private void multiThread() {
        mCompositeDisposable.add(Observable.create((ObservableOnSubscribe<String>) e -> {
            e.onNext("This msg from work thread :" + Thread.currentThread().getName());
            sb.append("\nsubscribe: currentThreadName==").append(Thread.currentThread().getName());
        })
                .subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    Log.e(TAG, "accept: s= " + s);
                    Log.e(TAG, "accept: currentThreadName==" + Thread.currentThread().getName());

                    sb.append("\naccept: currentThreadName==").append(Thread.currentThread().getName());
                    sb.append("\n\n简单的来说, subscribeOn() 指定的是上游发送事件的线程, observeOn() 指定的是下游接收事件的线程.\n" +
                            "\n" +
                            "多次指定上游的线程只有第一次指定的有效, 也就是说多次调用subscribeOn() 只有第一次的有效, 其余的会被忽略.\n" +
                            "多次指定下游的线程是可以的, 也就是说每调用一次observeOn() , 下游的线程就会切换一次.");
                    logContent.setText(sb.toString());
                }));
    }

    private void thread() {
        Observable<String> mObservable = Observable.create(e -> {
            Log.e(TAG, "subscribe: currentThreadName==" + Thread.currentThread().getName());
            sb.append("\nsubscribe: currentThreadName==").append(Thread.currentThread().getName());
            e.onNext("1000");
        });

        Consumer<String> mConsumer = s -> {
            Log.e(TAG, "accept: currentThreadName==" + Thread.currentThread().getName());
            Log.e(TAG, "accept: s=" + s);

            sb.append("\naccept: currentThreadName==").append(Thread.currentThread().getName());
            logContent.setText(sb.toString());
        };

        mCompositeDisposable.add(mObservable.subscribe(mConsumer));
    }

    private void consumer() {
        mCompositeDisposable.add(Observable.create((ObservableOnSubscribe<String>) e -> {
            e.onNext("Hello World");
            e.onError(new Throwable("Some Thing wrong !"));
        }).subscribe(s -> {
            Log.e(TAG, "accept: s=" + s);
            logContent.setText(s);
        }, throwable -> {
            Log.e(TAG, "accept: throwable=" + throwable.toString());
            logContent.setText(throwable.toString());
        }));
    }


    private void basicRxjava2() {

        Observable<String> mObservable = Observable.create(e -> {
            e.onNext("1");
            e.onNext("2");
            e.onNext("3");
            e.onNext("4");
            e.onComplete();
        });


        Observer<String> mObserver = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe: d=" + d);
                sb.append("\nonSubcribe: d=").append(d);
            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext: " + s);
                sb.append("\nonNext: ").append(s);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e);
                sb.append("\nonError: ").append(e.toString());
                logContent.setText(sb.toString());
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete");
                sb.append("\nonComplete: ");
                logContent.setText(sb.toString());
            }
        };

        mObservable.subscribe(mObserver);
    }


    private void basicRxjava2Chian() {

        Observable.create((ObservableOnSubscribe<String>) e -> {
            e.onNext("A");
            e.onNext("B");
            e.onNext("C");
            e.onNext("D");
            e.onComplete();
            e.onNext("E");
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe: d=" + d);
                sb.append("\nonSubcribe: d=").append(d);

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext: " + s);
                sb.append("\nonNext: ").append(s);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e);
                sb.append("\nonError: ").append(e.toString());
                logContent.setText(sb.toString());
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete");
                sb.append("\nonComplete: ");
                logContent.setText(sb.toString());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }
}

