package huyifei.mymvp.service.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import huyifei.mymvp.IMyAidlInterface;

public class CustomRemoteService extends Service {

    private static final String TAG = "CustomRemoteService";

    private class ServiceBinder extends IMyAidlInterface.Stub {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
        }

        @Override
        public int add(int a, int b) throws RemoteException {
            Log.e(TAG, "add: a="+a);
            Log.e(TAG, "add: b="+b);
            return a+b;
        }



        @Override
        public String loadData(String url) throws RemoteException {
            return url.concat(url);
        }

        @Override
        public String reback() throws RemoteException {
            return "Hello This is Msg from Remote";
        }


    }


    private Binder mBinder=new ServiceBinder();



    public CustomRemoteService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
       return mBinder;
    }
}
