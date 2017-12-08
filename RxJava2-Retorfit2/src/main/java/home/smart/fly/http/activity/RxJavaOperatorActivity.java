package home.smart.fly.http.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import home.smart.fly.http.R;
import home.smart.fly.http.R2;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_operator);
        ButterKnife.bind(this);
    }

    @OnClick({R2.id.basic1, R2.id.basic2, R2.id.basic3})
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
        }
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
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(100);
                e.onNext(101);
                e.onNext(102);
                e.onNext(103);
                e.onNext(104);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());


        Observable<String> mStringObservable = Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> e) throws Exception {
                        e.onNext("Monday");
                        e.onNext("Tuesday");
                        e.onNext("Wednesday");
                        e.onNext("Thursday");
                        e.onNext("Friday");
                        e.onComplete();
                    }
                })
                .subscribeOn(Schedulers.io());

        Observable
                .zip(mIntegerObservable, mStringObservable, new BiFunction<Integer, String, String>() {
                    @Override
                    public String apply(Integer integer, String s) throws Exception {
                        return s + "<---------->" + integer;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        sb.append(s + "\n");
                        logContent.setText(sb.toString());
                        Log.e(TAG, "accept: " + s);
                    }
                });
    }

    /**
     * http://www.jianshu.com/p/128e662906af
     */
    private void flatMapOperator() {
        Observable
                .create(new ObservableOnSubscribe<Object>() {
                    @Override
                    public void subscribe(ObservableEmitter<Object> e) throws Exception {
                        sb.append("FlatMap将一个发送事件的上游Observable变换为多个发送事件的Observables，然后将它们发射的事件合并后放进一个单独的Observable里");
                        sb.append("\n");

                        sb.append("e---> ");
                    }
                })
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        logContent.setText(sb.toString());
                    }
                });

        Observable
                .create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                        sb.append("FlatMap将一个发送事件的上游Observable变换为多个发送事件的Observables，然后将它们发射的事件合并后放进一个单独的Observable里");
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
                    public ObservableSource<String> apply(Integer integer) throws Exception {
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
                    public void accept(String s) throws Exception {
                        Log.e(TAG, "accept: s=" + s);
                        sb.append("accept= " + s).append("\n");

                        logContent.setText(sb.toString());
                    }
                });
    }

    private void mapOperator() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                sb.append("e: 1,2,3\n");
                sb.append("通过Map, 可以将上游发来的事件转换为任意的类型, 可以是一个Object, 也可以是一个集合");
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();


            }
        }).map(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) throws Exception {
                return integer * integer;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e(TAG, "accept: interger=" + integer);
                sb.append("result=" + integer).append("\n");
                logContent.setText(sb.toString());

            }
        });
    }
}
