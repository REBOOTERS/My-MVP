package com.engineer.jetpack;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.engineer.jetpack.ui.DBActivity;
import com.engineer.jetpack.ui.VMActivity;
import com.engineer.jetpack.ui.VMFragmentActivity;

public class JetPackActivity extends AppCompatActivity implements View.OnClickListener {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_jet_pack);
        findViewById(R.id.data_binding).setOnClickListener(this);
        findViewById(R.id.data_binding_livedata).setOnClickListener(this);
        findViewById(R.id.livedata_fragment).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent = null;
        if (id == R.id.data_binding) {
            intent = new Intent(mContext, DBActivity.class);
        } else if (id == R.id.data_binding_livedata) {
            intent = new Intent(mContext, VMActivity.class);
        } else if (id == R.id.livedata_fragment) {
            intent = new Intent(mContext, VMFragmentActivity.class);
        }

        if (intent != null) {
            startActivity(intent);
        }
    }
}
