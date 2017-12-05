package home.smart.fly.http;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_base);
        ButterKnife.bind(this);
    }

    @OnClick({R2.id.basic1, R2.id.basic2,
            R2.id.basic3, R2.id.basic4,
            R2.id.basic5, R2.id.basic6})
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
            Consumer();
        } else if (v.getId() == R.id.basic4) {
            Thread();
        } else if (v.getId() == R.id.basic5) {
            MultiThread();
        } else if (v.getId() == R.id.basic6) {
            WithRetrofit2();
        }
    }

    private void WithRetrofit2() {
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
//        builder.addConverterFactory(GsonConverterFactory.create());
//        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        Retrofit mRetrofit = builder.build();

        final GankApi mGankApi = mRetrofit.create(GankApi.class);
        final Call<ResponseBody> mCall = mGankApi.getJson("10/1");

        Observable.create(new ObservableOnSubscribe<ResponseBody>() {
            @Override
            public void subscribe(ObservableEmitter<ResponseBody> e) throws Exception {
                e.onNext(mCall.execute().body());

            }
        })

                .map(new Function<ResponseBody, String>() {

                    @Override
                    public String apply(ResponseBody responseBody) throws Exception {
                        return responseBody.string();
                    }
                })
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


    private void MultiThread() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("This msg from work thread :" + Thread.currentThread().getName());
                sb.append("\nsubscribe: currentThreadName==" + Thread.currentThread().getName());
            }
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e(TAG, "accept: s= " + s);
                        Log.e(TAG, "accept: currentThreadName==" + Thread.currentThread().getName());

                        sb.append("\naccept: currentThreadName==" + Thread.currentThread().getName());
                        sb.append("\n\n简单的来说, subscribeOn() 指定的是上游发送事件的线程, observeOn() 指定的是下游接收事件的线程.\n" +
                                "\n" +
                                "多次指定上游的线程只有第一次指定的有效, 也就是说多次调用subscribeOn() 只有第一次的有效, 其余的会被忽略.\n" +
                                "多次指定下游的线程是可以的, 也就是说每调用一次observeOn() , 下游的线程就会切换一次.");
                        logContent.setText(sb.toString());
                    }
                });
    }

    private void Thread() {
        Observable mObservable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter e) throws Exception {
                Log.e(TAG, "subscribe: currentThreadName==" + Thread.currentThread().getName());
                sb.append("\nsubscribe: currentThreadName==" + Thread.currentThread().getName());
                e.onNext("1000");


            }
        });

        Consumer mObserver = new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e(TAG, "accept: currentThreadName==" + Thread.currentThread().getName());
                Log.e(TAG, "accept: s=" + s);

                sb.append("\naccept: currentThreadName==" + Thread.currentThread().getName());
                logContent.setText(sb.toString());
            }
        };

        mObservable.subscribe(mObserver);
    }

    private void Consumer() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("Hello World");
                e.onError(new Throwable("Some Thing wrong !"));
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e(TAG, "accept: s=" + s);
                logContent.setText(s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e(TAG, "accept: throwable=" + throwable.toString());
                logContent.setText(throwable.toString());
            }
        });
    }


    private void basicRxjava2() {
        Observable mObservable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter e) throws Exception {
                e.onNext("1");
                e.onNext("2");
                e.onNext("3");
                e.onNext("4");
                e.onComplete();
            }
        });

        Observer mObserver = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe: d=" + d);
                sb.append("\nonSubcribe: d=" + d);

            }

            @Override
            public void onNext(Object s) {
                Log.e(TAG, "onNext: " + s);
                sb.append("\nonNext: " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e);
                sb.append("\nonError: " + e.toString());
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
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("A");
                e.onNext("B");
                e.onNext("C");
                e.onNext("D");
                e.onComplete();
                e.onNext("E");
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe: d=" + d);
                sb.append("\nonSubcribe: d=" + d);

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext: " + s);
                sb.append("\nonNext: " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e);
                sb.append("\nonError: " + e.toString());
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
}

