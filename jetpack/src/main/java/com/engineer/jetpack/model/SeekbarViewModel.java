package com.engineer.jetpack.model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author: Rookie
 * @date: 2018-09-30
 * @desc
 */
public class SeekbarViewModel extends ViewModel {
    public MutableLiveData<Integer> seekbarValue = new MutableLiveData<>();
}
