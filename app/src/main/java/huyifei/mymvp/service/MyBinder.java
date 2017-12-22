package huyifei.mymvp.service;

import android.os.Binder;
import android.util.Log;

/**
 * Created by rookie on 2017/2/14.
 */

public class MyBinder extends Binder {

    private boolean run = true;

    public void startDownload() {
        run = true;
        // 执行具体的下载任务

        new Thread(new Runnable() {
            @Override
            public void run() {
                int x = 1;
                while (run) {
                    try {
                        Thread.sleep(1000);
                        x++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.e("TAG", "startDownload() executed x=" + x);
                }
                Log.e("TAG", "service stoped !");

            }
        }).start();
    }

    public void stopDownload() {
        run = false;
        Log.e("TAG", "try to stop service");
    }
}
