package huyifei.mymvp;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import huyifei.mymvp.util.V;

public class InfoActivity extends AppCompatActivity {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_info);
        TextView heapSizeTv = V.f(this, R.id.heapSizeTv);
        int heap_size = ActivityManager.MemoryInfo.CONTENTS_FILE_DESCRIPTOR;
        heapSizeTv.setText(heap_size + "");


        //        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Looper.prepare();
//                Looper.loop();
//
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                Toast.makeText(mContext, "Update UI !", Toast.LENGTH_SHORT).show();
//            }
//        }).start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(mContext, "Update UI !", Toast.LENGTH_SHORT).show();
            }
        }, 1000);
    }
}
