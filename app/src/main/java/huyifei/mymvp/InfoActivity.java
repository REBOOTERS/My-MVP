package huyifei.mymvp;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

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




        new Thread(new Runnable() {
            @Override
            public void run() {

                Looper.prepare();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                String currentName=Thread.currentThread().getName();

                Toast.makeText(mContext, "I'm in  Thread "+currentName, Toast.LENGTH_SHORT).show();


                Looper.loop();

            }
        }).start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String currentName=Thread.currentThread().getName();
                Toast.makeText(mContext, "I'm new Thread "+currentName, Toast.LENGTH_SHORT).show();
            }
        }, 4000);


    }
}
