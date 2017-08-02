package huyifei.mymvp.service;

import android.os.Binder;
import android.util.Log;

/**
 * Created by rookie on 2017/2/14.
 */

public class MyBinder extends Binder {
    public void startDownload() {

        // 执行具体的下载任务

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.e("TAG", "startDownload() executed");
                }
            }
        }).start();
    }
}
