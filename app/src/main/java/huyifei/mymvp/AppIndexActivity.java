package huyifei.mymvp;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.RandomAccessFile;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

import huyifei.mymvp.views.MyView;
import huyifei.mymvp.views.MyViewGroup;
import rx.internal.util.LinkedArrayList;

public class AppIndexActivity extends AppCompatActivity {
    private static final String TAG = "AppIndexActivity";

    Stack mStack;
    ArrayList mArrayList;
    LinkedList mLinkedList;
    LinkedArrayList mLinkedArrayList;
    HashMap<String,String> mStringStringHashMap;


    ClassLoader mClassLoader;

    Arrays mArrays;
    Array mArray;

    Handler mHandler;

    HandlerThread mHandlerThread = new HandlerThread("a");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_index);


        TAG.compareTo("1.2.3");

        viewEvent();
        viewGroupEvent();

        RandomAccessFile file;

        BitmapFactory f;


    }


    private class MyCallBack implements Handler.Callback {

        @Override
        public boolean handleMessage(Message msg) {
            return false;
        }
    }


    /**
     * ViewGroup 分发测试
     */
    private void viewGroupEvent() {
        MyViewGroup viewgroup = (MyViewGroup) findViewById(R.id.myViewGroup);
        Button button = (Button) findViewById(R.id.button);
        TextView tv = (TextView) findViewById(R.id.tv);

        Log.e(TAG, "Button clickable is " + button.isClickable());
        Log.e(TAG, "TextView clickable is " + tv.isClickable());

        viewgroup.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e(TAG, "ViewGroup onTouch: event type is " + event.getAction());
                return false;
            }
        });

        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e(TAG, "Button onTouch: event type is " + event.getAction());
                return false;
            }
        });

        tv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e(TAG, "TextView onTouch: event type is " + event.getAction());
                return false;
            }
        });
    }

    /**
     * view 事件分发测试
     */
    private void viewEvent() {
        MyView myView = (MyView) findViewById(R.id.myView);

        //
        myView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e(TAG, "onTouch: event type is " + event.getAction());
                return false;
            }
        });

        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
