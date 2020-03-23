package com.engineer.jetpack.ui.fragment;


import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.engineer.jetpack.R;
import com.engineer.jetpack.model.SeekbarViewModel;
import com.engineer.jetpack.util.OnSeekBarChangeAdapterListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class SeekbarFragment extends Fragment {

    private SeekBar mSeekBar;
    private SeekbarViewModel mSeekbarViewModel;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_seekbar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSeekBar = view.findViewById(R.id.seekbar);
        mSeekbarViewModel = ViewModelProviders.of(getActivity()).get(SeekbarViewModel.class);
        subscribe();
    }

    private void subscribe() {
        mSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeAdapterListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                super.onProgressChanged(seekBar, progress, fromUser);
                if (fromUser) {
                    mSeekbarViewModel.seekbarValue.setValue(progress);
                }
            }
        });

        mSeekbarViewModel.seekbarValue.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                if (integer != null) {
                    mSeekBar.setProgress(integer);
                }
            }
        });
    }
}
