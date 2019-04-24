package com.engineer.jetpack.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.SystemClock;
import android.util.Log;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author: Rookie
 * @date: 2018-09-30
 * @desc
 */
public class LiveDataTimerViewModel extends ViewModel {

    private MutableLiveData<Long> mElapsedTime = new MutableLiveData<>();
    private MutableLiveData<Integer> mClockTime = new MutableLiveData<>();
    private boolean isLive = true;

    private Long mInitalTime;
    private ScheduledExecutorService mScheduledExecutorService;
    private Runnable mRunnable;

    public LiveDataTimerViewModel() {
        mInitalTime = SystemClock.elapsedRealtime();
        mScheduledExecutorService = Executors.newScheduledThreadPool(1);
        mRunnable = () -> {
            long newValue = (SystemClock.elapsedRealtime() - mInitalTime) / 1000;
            mElapsedTime.postValue(newValue);
        };

    }

    public void startRealTime() {
        mScheduledExecutorService.scheduleAtFixedRate(mRunnable, 0, 1, TimeUnit.SECONDS);

        new Thread(() -> {
            while (isLive) {
                int newValue = (int) ((SystemClock.elapsedRealtime() - mInitalTime) / 1000);
                mClockTime.postValue(newValue);
            }
        }).start();
    }


    public MutableLiveData<Long> getElapsedTime() {
        return mElapsedTime;
    }

    public LiveData<Integer> getClockTime() {
        return mClockTime;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.e("VMActivity", "onCleared:");
        isLive = false;
        mScheduledExecutorService.shutdown();
    }
}
