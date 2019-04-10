package home.smart.fly.http.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

import home.smart.fly.http.R;
import io.reactivex.Observable;

public class LifeCycleActivity extends RxAppCompatActivity implements View.OnClickListener {
    private static final String TAG = "LifeCycleActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);
        findViewById(R.id.simple).setOnClickListener(this);
        findViewById(R.id.simple_1).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.simple:
                Observable.interval(1, TimeUnit.SECONDS)
                        .doOnDispose(() -> {
                            Log.e(TAG, "auto disposed");
                        })
                        .compose(bindUntilEvent(ActivityEvent.DESTROY))
                        .subscribe(aLong -> Log.e(TAG, "accept: " + aLong));
                break;

            case R.id.simple_1:
                Observable.interval(1, TimeUnit.SECONDS)
                        .doOnDispose(() -> Log.e(TAG, "auto disposed"))
                        .compose(bindToLifecycle())
                        .subscribe(aLong -> Log.e(TAG, "accept: " + aLong));
                break;
            default:
                break;
        }
    }
}
