package huyifei.mymvp.service.ipc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import huyifei.mymvp.IMyAidlInterface;
import huyifei.mymvp.R;

public class AIDLActivity extends AppCompatActivity {
    private static final String TAG = "AIDLActivity";
    private Intent mIntent;

    private IMyAidlInterface mIMyAidlInterface;
    private boolean isConnected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
        mIntent = new Intent(this, CustomRemoteService.class);
        mIntent.setPackage(getPackageName());
        findViewById(R.id.bind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isConnected = true;
                bindService(mIntent, mConnection, Context.BIND_AUTO_CREATE);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isConnected && mConnection != null) {


            unbindService(mConnection);
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(TAG, "onServiceConnected: " + name);
            Log.e(TAG, "onServiceConnected: " + service.toString());
            try {
                mIMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
                int result = mIMyAidlInterface.add(10, 20);
                Log.e(TAG, "onServiceConnected: 10+20=" + result);
                String msg = mIMyAidlInterface.reback();
                Log.e(TAG, "onServiceConnected: msg=" + msg);

                String info = mIMyAidlInterface.loadData("http://www.baidu.com");
                Log.e(TAG, "onServiceConnected: info=" + info);


                int pos = mIMyAidlInterface.binarySearch(5);
                Log.e(TAG, "onServiceConnected: find 5 at pos=" + pos);

            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "onServiceDisconnected: " + name);
        }
    };
}
