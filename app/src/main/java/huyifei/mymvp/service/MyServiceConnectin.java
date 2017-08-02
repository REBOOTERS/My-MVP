package huyifei.mymvp.service;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by rookie on 2017/2/14.
 */

public class MyServiceConnectin implements ServiceConnection {
    private static final String TAG = "MyServiceConnectin";
    private MyBinder mMyBinder;

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        Log.e(TAG, "onServiceConnected: ComponentName=" + name);
        Log.e(TAG, "onServiceConnected: service=" + service);

        mMyBinder = (MyBinder) service;
        mMyBinder.startDownload();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        Log.e(TAG, "onServiceDisconnected: ComponentName=" + name);
    }
}
