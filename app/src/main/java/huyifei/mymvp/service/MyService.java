package huyifei.mymvp.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import androidx.annotation.RequiresApi;
import android.util.Log;

import huyifei.mymvp.MainActivity;
import huyifei.mymvp.R;
import huyifei.mymvp.util.Tools;

public class MyService extends Service {
    private static final String TAG = "MyService";

    private MyBinder myBinder = new MyBinder();


    public MyService() {
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onCreate() {
        super.onCreate();


        Notification.Builder builer = new Notification.Builder(getApplicationContext());

        builer.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("有通知到来")
                .setContentText(Tools.getCurrentTime());

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);
        Notification notification = builer.build();

        builer.setContentIntent(pendingIntent);
        startForeground(1, notification);


        Log.e(TAG, "onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.e(TAG, "onBind: ");
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

}
