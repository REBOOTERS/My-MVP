package home.smart.fly.http.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import home.smart.fly.http.R;
import home.smart.fly.http.R2;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * author : Rookie
 * e-mail : yingkongshi11@gmail.com
 * time   : 2017/12/06
 * desc   :
 * version: 1.0
 */
public class RxJavaOperatorActivity extends AppCompatActivity {
    private static final String TAG = "RxJavaOperatorActivity";
    @BindView(R2.id.logContent)
    TextView logContent;
    @BindView(R2.id.basic1)
    Button basic1;

    private StringBuilder sb;

    private Subscription mSubscription;
    private CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_operator);
        ButterKnife.bind(this);
        mCompositeDisposable = new CompositeDisposable();
    }

    @OnClick({R2.id.basic1, R2.id.basic2, R2.id.basic3, R2.id.basic4, R2.id.basic5,
            R2.id.basic6, R2.id.basic7, R2.id.basic8, R2.id.basic9})
    public void OnClick(View v) {
        if (sb != null) {
            sb = null;
        }
        sb = new StringBuilder();
        logContent.setText("");

        if (v.getId() == R.id.basic1) {
            mapOperator();
        } else if (v.getId() == R.id.basic2) {
            flatMapOperator();
        } else if (v.getId() == R.id.basic3) {
            zipOperator();
        } else if (v.getId() == R.id.basic4) {
            sampleOperator();
        } else if (v.getId() == R.id.basic5) {
            flowableOperator();
        } else if (v.getId() == R.id.basic6) {
            flowableOperatorPro();
        } else if (v.getId() == R.id.basic7) {
            flowableOperatorBackpressure();
        } else if (v.getId() == R.id.basic8) {
            intervalOperator();
        } else if (v.getId() == R.id.basic9) {
            intervalRangeOperator();
        }
    }

    private void intervalRangeOperator() {
        Observable.intervalRange(1,10,0,500,TimeUnit.MILLISECONDS,AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.e(TAG, "onNext: aLong=" + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void intervalOperator() {
        Observable.interval(1,TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.e(TAG, "onNext: aLong==" + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * http://www.jianshu.com/p/36e0f7f43a51
     */
    private void flowableOperatorBackpressure() {
        Flowable.interval(1, TimeUnit.SECONDS)
                .onBackpressureDrop()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        mSubscription = s;
                        s.request(Integer.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.e(TAG, "onNext: aLong=" + aLong);
                        logContent.setText("aLong=" + String.valueOf(aLong));
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void flowableOperatorPro() {
        Flowable<Integer> mIntegerFlowable = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) {
                for (int i = 0; i < 1000; i++) {
                    e.onNext(i);
                    Log.e(TAG, "subscribe: " + i);
                    sb.append("e----> " + i + "\n");
                }
            }
        }, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Subscriber<Integer> mIntegerSubscriber = new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                mSubscription = s;
                Log.e(TAG, "onSubscribe: s=" + s);
                s.request(Integer.MAX_VALUE);
            }

            @Override
            public void onNext(Integer integer) {
                Log.e(TAG, "onNext: integer=" + integer);

                sb.append(integer + "\n");
                logContent.setText(sb.toString());
            }

            @Override
            public void onError(Throwable t) {
                Log.e(TAG, "onError: t=" + t);
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete: ");
            }
        };

        mIntegerFlowable.subscribe(mIntegerSubscriber);


    }

    /**
     * http://www.jianshu.com/p/9b1304435564
     * <p>
     * Flowable在设计的时候采用了一种新的思路也就是响应式拉取的方式来更好的解决上下游流速不均衡的问题
     * <p>
     * 我们把request当做是一种能力, 当成下游处理事件的能力, 下游能处理几个就告诉上游我要几个,
     * 这样只要上游根据下游的处理能力来决定发送多少事件, 就不会造成一窝蜂的发出一堆事件来, 从而导致OOM.
     */
    private void flowableOperator() {
        Flowable<Integer> mIntegerFlowable = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);
                e.onNext(5);
            }
        }, BackpressureStrategy.ERROR);

        Subscriber<Integer> mIntegerSubscriber = new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                mSubscription = s;
                Log.e(TAG, "onSubscribe: s=" + s);
                s.request(Integer.MAX_VALUE);
            }

            @Override
            public void onNext(Integer integer) {
                Log.e(TAG, "onNext: integer=" + integer);
                sb.append(integer + "\n");

                logContent.setText(sb.toString());
            }

            @Override
            public void onError(Throwable t) {
                Log.e(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete: ");
            }
        };

        mIntegerFlowable.subscribe(mIntegerSubscriber);

    }

    /**
     * 解决上游主题发送事件过快，导致异步线程OOM 的问题
     */
    private void sampleOperator() {
        Observable<Integer> mIntegerObservable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) {
                for (int i = 0; ; i++) {
                    e.onNext(i);
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .sample(2, TimeUnit.SECONDS);

        Observable<String> mStringObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) {
                e.onNext("Message");
            }
        })
                .subscribeOn(Schedulers.io());


        mCompositeDisposable.add(Observable.zip(mIntegerObservable, mStringObservable, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) {
                return integer + " & " + s;
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        Log.e(TAG, "accept: s=" + s);
                        sb.append(s + "\n");
                        logContent.setText(sb.toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        Log.e(TAG, "accept: trowable=" + throwable);
                    }
                }));
    }

    /**
     * http://www.jianshu.com/p/bb58571cdb64
     * <p>
     * Zip通过一个函数将多个Observable发送的事件结合到一起，
     * 然后发送这些组合到一起的事件. 它按照严格的顺序应用这个函数。
     * 它只发射与发射数据项最少的那个Observable一样多的数据。
     */
    private void zipOperator() {
        Observable<Integer> mIntegerObservable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) {

                sb.append("Zip通过一个函数将多个Observable发送的事件结合到一起，\n" +
                        " 然后发送这些组合到一起的事件. 它按照严格的顺序应用这个函数。\n" +
                        " 它只发射与发射数据项最少的那个Observable一样多的数据。\n");

                e.onNext(100);
                e.onNext(101);
                e.onNext(102);
                e.onNext(103);
                e.onNext(104);
                e.onNext(105);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());


        Observable<String> mStringObservable = Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> e) {
                        e.onNext("Monday");
                        e.onNext("Tuesday");
                        e.onNext("Wednesday");
                        e.onNext("Thursday");
                        e.onNext("Friday");
                        e.onComplete();
                    }
                })
                .subscribeOn(Schedulers.io());

        mCompositeDisposable.add(Observable
                .zip(mIntegerObservable, mStringObservable, new BiFunction<Integer, String, String>() {
                    @Override
                    public String apply(Integer integer, String s) {
                        return s + "<---------->" + integer;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        sb.append(s + "\n");
                        logContent.setText(sb.toString());
                        Log.e(TAG, "accept: " + s);
                    }
                }));
    }

    /**
     * http://www.jianshu.com/p/128e662906af
     */
    private void flatMapOperator() {


        mCompositeDisposable.add(Observable
                .create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> e) {
                        sb.append("FlatMap将一个发送事件的上游Observable变换为多个发送事件的Observables，" +
                                "然后将它们发射的事件合并后放进一个单独的Observable里");
                        sb.append("\n");
                        sb.append(" flatMap并不保证事件的顺序\n");

                        sb.append("e---> 1\n");
                        sb.append("e---> 2\n");
                        sb.append("e---> 3\n");
                        sb.append("e---> 4\n");
                        sb.append("e---> onComplete\n");
                        e.onNext(1);
                        e.onNext(2);
                        e.onNext(3);
                        e.onNext(4);
                        e.onComplete();

                    }
                })
                .flatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) {
                        final List<String> mList = new ArrayList<>();
                        for (int i = 0; i < 4; i++) {
                            mList.add("the Square is " + integer * integer);
                        }
                        return Observable.fromIterable(mList).delay(1000, TimeUnit.MILLISECONDS);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        Log.e(TAG, "accept: s=" + s);
                        sb.append("accept= " + s).append("\n");

                        logContent.setText(sb.toString());
                    }
                }));
    }

    private void mapOperator() {
        mCompositeDisposable.add(Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) {
                sb.append("e: 1,2,3\n");
                sb.append("通过Map, 可以将上游发来的事件转换为任意的类型, 可以是一个Object, 也可以是一个集合");
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        }).map(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer * integer;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                Log.e(TAG, "accept: interger=" + integer);
                sb.append("result=" + integer).append("\n");
                logContent.setText(sb.toString());

            }
        }));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSubscription != null) {
            mSubscription.cancel();
        }
        mCompositeDisposable.clear();
    }
}
