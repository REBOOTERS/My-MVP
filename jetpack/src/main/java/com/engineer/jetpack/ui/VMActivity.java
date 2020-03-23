package com.engineer.jetpack.ui;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.engineer.jetpack.R;
import com.engineer.jetpack.model.LiveDataTimerViewModel;

public class VMActivity extends AppCompatActivity {
    private static final String TAG = "VMActivity";
    private LiveDataTimerViewModel mLiveDataTimerViewModel;
    private TextView mTimerText;
    private TextView mClockText;
    private Observer<Integer> mIntegerObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vm);
        mTimerText = findViewById(R.id.timer_text);
        mClockText = findViewById(R.id.clock_text);
        mLiveDataTimerViewModel = ViewModelProviders.of(this).get(LiveDataTimerViewModel.class);

        mIntegerObserver = updateClock();
        subscribe();

        findViewById(R.id.button).setOnClickListener(v -> mLiveDataTimerViewModel.startRealTime());
        findViewById(R.id.finish).setOnClickListener(v -> finish());
    }

    private void subscribe() {
        mLiveDataTimerViewModel.getElapsedTime().observe(this, updateTime());
//        mLiveDataTimerViewModel.getClockTime().observe(this, mIntegerObserver);
    }

    private Observer<Long> updateTime() {
        return aLong -> {
            String value = String.valueOf(aLong);
            mTimerText.setText(value);
            Log.e(TAG, "onChanged: aLong==" + aLong);
        };
    }

    private Observer<Integer> updateClock() {
        return result -> {
            String value = String.valueOf(result);
            mClockText.setText(value);
            Log.e(TAG, "updateClock: result==" + result);
        };
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
    }
}
