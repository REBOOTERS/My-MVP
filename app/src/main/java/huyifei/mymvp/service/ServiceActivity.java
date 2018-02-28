package huyifei.mymvp.service;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import huyifei.mymvp.R;

public class ServiceActivity extends AppCompatActivity {
    private Context mContext;
    private Intent mIntent;
    //
    private MyServiceConnection connection;

    private Object mObject;

    /**
     * [Service 生命周期]("http://www.jianshu.com/p/8d0cde35eb10")
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_service);
        mIntent = new Intent(mContext, MyService.class);
        //
        connection = new MyServiceConnection();
        actionView();



    }

    private void actionView() {
        findViewById(R.id.startService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(mIntent);
            }
        });

        findViewById(R.id.stopService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(mIntent);
            }
        });
        findViewById(R.id.bindService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(mIntent, connection, BIND_AUTO_CREATE);
            }
        });

        findViewById(R.id.unbindService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(connection);
            }
        });

        /**
         * http://www.jianshu.com/p/8a3c44a9173a Android多线程全面解析：IntentService用法&源码
         */
        findViewById(R.id.intentService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                MyIntentService.startActionBaz(mContext, "Hello ", "Baz");
                MyIntentService.startActionFoo(mContext, "Hello", "Foo");
            }
        });
    }

}
