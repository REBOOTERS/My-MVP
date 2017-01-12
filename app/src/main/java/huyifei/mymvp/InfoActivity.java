package huyifei.mymvp;

import android.app.ActivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import huyifei.mymvp.util.V;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        TextView heapSizeTv = V.f(this, R.id.heapSizeTv);
        int heap_size = ActivityManager.MemoryInfo.CONTENTS_FILE_DESCRIPTOR;
        heapSizeTv.setText(heap_size+"");
    }
}
