package huyifei.mymvp;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;

public class GlideActivity extends AppCompatActivity {
    private String picUrl = "http://img1.3lian.com/img013/v4/96/d/45.jpg";

    private static final int ONE_BYTE = 1024;
    private static final int ONE_MB = 1024 * ONE_BYTE;


    private ActivityManager mActivityManager;
    private StringBuilder sb = new StringBuilder();
    private TextView info;

    private static final String TAG = "GlideActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: ");
        setContentView(R.layout.activity_glide);
        mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        sb.append("当前应用可用Heap Size is " + mActivityManager.getMemoryClass() + "MB\n");
        sb.append("要加载的图片在虚拟机中所占的内存: " + 2560 * 1600 * 4 / ONE_MB + "MB\n");

        info = findViewById(R.id.info);
        info.setText(sb);

        final ImageView imageView = findViewById(R.id.image);
        Glide.with(this).load(picUrl).into(imageView);
        findViewById(R.id.loadimg)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.large_image);
                        imageView.setImageBitmap(bitmap);
                    }
                });

        Handler mHandler;
        AsyncTask m;

        try {
            Runtime.getRuntime().exec("adb logcat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart: ");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.e(TAG, "onSaveInstanceState: ");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e(TAG, "onRestoreInstanceState: ");
    }
}
