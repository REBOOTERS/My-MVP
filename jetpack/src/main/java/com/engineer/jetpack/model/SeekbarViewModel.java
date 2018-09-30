package com.engineer.jetpack.model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * @author: zhuyongging
 * @date: 2018-09-30
 * @desc
 */
public class SeekbarViewModel extends ViewModel {
    public MutableLiveData<Integer> seekbarValue = new MutableLiveData<>();
}
