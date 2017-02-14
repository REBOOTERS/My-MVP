package huyifei.mymvp.service;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * Created by rookie on 2017/2/14.
 */

public class MyServiceConnectin implements ServiceConnection {
    private MyBinder mMyBinder;

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        mMyBinder = (MyBinder) service;
        mMyBinder.startDownload();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
