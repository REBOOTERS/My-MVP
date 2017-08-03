package huyifei.mymvp.architecture.mvc;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import huyifei.mymvp.R;
import huyifei.mymvp.architecture.common.Constants;
import huyifei.mymvp.architecture.common.DownloadCallback;
import huyifei.mymvp.architecture.common.HttpUtil;

public class MVCActivity extends AppCompatActivity {
    private static final String TAG = "MVCActivity";

    private Context mContext;
    private ImageView mImageView;
    private ProgressDialog progressDialog;


    final Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 300:
                    int percent = msg.arg1;
                    if (percent < 100) {
                        progressDialog.setProgress(percent);
                    } else {
                        progressDialog.dismiss();
                        Glide.with(mContext).load(Constants.LOCAL_FILE_PATH).into(mImageView);
                    }
                    break;
                case 404:
                    progressDialog.dismiss();
                    Toast.makeText(mContext, "Download fail !", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc);
        mContext = this;
        init();
    }

    private void init() {
        //view init
        mImageView = (ImageView) findViewById(R.id.image);
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                progressDialog.dismiss();
            }
        });
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setTitle("下载文件");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);


        //click-event
        findViewById(R.id.downloadBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                HttpUtil.HttpGet(Constants.DOWNLOAD_URL, new DownloadCallback(mHandler));
            }
        });

        findViewById(R.id.downloadBtn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                HttpUtil.HttpGet(Constants.DOWNLOAD_ERROR_URL, new DownloadCallback(mHandler));
            }
        });


        Looper mLooper = getMainLooper();
        Thread mThread=mLooper.getThread();
        String threadName = mThread.getName();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            MessageQueue mMessageQueue=mLooper.getQueue();
        }


        Log.e(TAG, "threadName: "+threadName);

    }


}
