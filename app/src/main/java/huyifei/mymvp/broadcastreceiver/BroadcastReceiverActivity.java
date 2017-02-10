package huyifei.mymvp.broadcastreceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import huyifei.mymvp.R;
import huyifei.mymvp.util.Tools;

public class BroadcastReceiverActivity extends AppCompatActivity {
    private static final String MY_ACTION = "huiyifei.mymvp.broadcast";
    private static final String MY_ACTION_1 = "huiyifei.mymvp.broadcast_1";


    private MyReceiver mMyReceiver;
    private SecondReceiver mSecondReceiver;

    private TextView battery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver);
        mMyReceiver = new MyReceiver();
        mSecondReceiver = new SecondReceiver();
        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(MY_ACTION);
                intent.putExtra("date", Tools.getCurrentTime());
                sendBroadcast(intent);
            }
        });

        findViewById(R.id.sendSecond).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(MY_ACTION_1);
                intent.putExtra("msg", String.valueOf(System.currentTimeMillis()));
                sendBroadcast(intent);
            }
        });


        getBattery();
    }

    /**
     * 获取系统电量
     */
    private void getBattery() {
        battery = (TextView) findViewById(R.id.battery);
        IntentFilter batteryFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryIntent = getApplicationContext().registerReceiver(null, batteryFilter);
        int total = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, 1);
        int current = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
        float percent = current * 100 / total;
        battery.setText(String.valueOf(percent));
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MY_ACTION);
        registerReceiver(mMyReceiver, intentFilter);


        IntentFilter intentFilter1 = new IntentFilter();
        intentFilter1.addAction(MY_ACTION_1);
        registerReceiver(mSecondReceiver, intentFilter1);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mMyReceiver);
        unregisterReceiver(mSecondReceiver);
    }
}
