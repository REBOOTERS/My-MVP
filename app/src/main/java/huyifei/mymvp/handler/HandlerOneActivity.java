package huyifei.mymvp.handler;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import huyifei.mymvp.R;

public class HandlerOneActivity extends AppCompatActivity {

    private static final String TAG = "HandlerOneActivity";

    private static class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String info = "";
            switch (msg.what) {

                case 100:
                     info = (String) msg.obj;
                    Log.e(TAG, "handleMessage: info==" + info);
                    break;
                case 200:
                     info = (String) msg.obj;
                    Log.e(TAG, "handleMessage: act-1-info==" + info);
                    break;
                default:
                    break;
            }

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_one);
        findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message mMessage=Message.obtain();
                mMessage.what=100;
                mMessage.obj="from-activity-1";
                Handler mHandler=new MyHandler();
                Log.e(TAG, "onClick: looper=="+mHandler.getLooper() );
                Log.e(TAG, "onClick: messageQueue=="+mHandler.getLooper().getQueue() );
                mHandler.sendMessage(mMessage);
            }
        });
        findViewById(R.id.button8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HandlerOneActivity.this, HandlerTwoActivity.class));
            }
        });
    }
}
