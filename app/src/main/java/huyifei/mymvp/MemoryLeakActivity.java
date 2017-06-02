package huyifei.mymvp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import java.lang.ref.WeakReference;

public class MemoryLeakActivity extends AppCompatActivity {

    private MyHandler mLeakyHandler = new MyHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_leak);

        mLeakyHandler.postDelayed(new Job(), 10 * 1000);
        finish();
    }

    private static class Job implements Runnable{

        @Override
        public void run() {

        }
    }




    private static class MyHandler extends Handler {
        private final WeakReference<MemoryLeakActivity> mActivity;

        public MyHandler(MemoryLeakActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            MemoryLeakActivity activity = mActivity.get();
            if (activity != null) {
                // ...
            }
        }
    }
}
