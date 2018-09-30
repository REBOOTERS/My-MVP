package com.engineer.jetpack.model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.SystemClock;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhuyongging
 * @date: 2018-09-30
 * @desc
 */
public class LiveDataTimerViewModel extends ViewModel {

    private MutableLiveData<Long> mElapsedTime = new MutableLiveData<>();

    private Long mInitalTime;

    public LiveDataTimerViewModel() {
        mInitalTime = SystemClock.elapsedRealtime();

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                long newValue = (SystemClock.elapsedRealtime() - mInitalTime) / 1000;
                mElapsedTime.postValue(newValue);
            }
        }, 0,1, TimeUnit.SECONDS);

    }

    public MutableLiveData<Long> getElapsedTime() {
        return mElapsedTime;
    }
}
