package home.smart.fly.http;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RxJavaBaseActivity extends AppCompatActivity {
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_base);
        ButterKnife.bind(this);
    }

    @OnClick({R2.id.basic1,R2.id.basic2,R2.id.basic3,R2.id.basic4,R2.id.basic5})
    public void onClick(View v) {
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
        }
    }



    private void MultiThread() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("This msg from work thread :" + Thread.currentThread().getName());
            }
        }).subscribeOn(Schedulers.newThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e(TAG, "accept: s= " + s);
                        Log.e(TAG, "accept: currentThreadName==" + Thread.currentThread().getName());
                    }
                });
    }

    private void Thread() {
        Observable mObservable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter e) throws Exception {
                Log.e(TAG, "subscribe: currentThreadName==" + Thread.currentThread().getName());
                e.onNext("1000");

            }
        });

        Consumer mObserver = new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e(TAG, "accept: currentThreadName==" + Thread.currentThread().getName());
                Log.e(TAG, "accept: s=" + s);
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
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e(TAG, "accept: throwable=" + throwable.toString());
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
                Log.e(TAG, "onSubscribe: " + d);
            }

            @Override
            public void onNext(Object o) {
                Log.e(TAG, "onNext: " + o);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.toString());
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete ");
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
            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext: " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e);
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete");
            }
        });
    }
}

