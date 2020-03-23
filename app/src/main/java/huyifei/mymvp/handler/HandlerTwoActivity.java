package huyifei.mymvp.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import huyifei.mymvp.R;

public class HandlerTwoActivity extends AppCompatActivity {

    private static final String TAG = "HandlerTwoActivity";

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
//                case 200:
//                    info = (String) msg.obj;
//                    Log.e(TAG, "handleMessage: act-2-info==" + info);
//                    break;
                default:
                    break;
            }

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_two);
        findViewById(R.id.button7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message mMessage=Message.obtain();
                mMessage.what=200;
                mMessage.obj="from-activity-2";
                Handler mHandler=new MyHandler();
                Log.e(TAG, "onClick: looper=="+mHandler.getLooper() );
                Log.e(TAG, "onClick: messageQueue=="+mHandler.getLooper().getQueue() );
                mHandler.sendMessage(mMessage);
            }
        });
    }
}
