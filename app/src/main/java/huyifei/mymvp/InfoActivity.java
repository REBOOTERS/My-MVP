package huyifei.mymvp;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import huyifei.mymvp.util.V;

import static huyifei.mymvp.R.id.heapSizeTv;

public class InfoActivity extends AppCompatActivity {
    private Context mContext;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_info);
        mTextView = V.f(this, heapSizeTv);
        int heap_size = ActivityManager.MemoryInfo.CONTENTS_FILE_DESCRIPTOR;
        mTextView.setText(heap_size + "");
    }
}
