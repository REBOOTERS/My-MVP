package home.smart.fly.httpurlconnectiondemo.downloads;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import home.smart.fly.httpurlconnectiondemo.R;
import home.smart.fly.httpurlconnectiondemo.downloads.utils.Constants;
import home.smart.fly.httpurlconnectiondemo.downloads.utils.DownloadHelper;


/**
 * Created by rookie on 2016/11/3.
 * <p>
 */

public class Okhttp3DownloadsActivity extends AppCompatActivity {
    private static final String TAG = "Okhttp3DownloadsActivity";
    private Context mContext;


    private ProgressBar mProgressBar;
    private TextView progressValue;

    private int breakPointValue = 0;
    private int totalValue = 0;

    private ImageView pause;
    private boolean isPause = true;
    private boolean isStop = false;

    private MyHandler mMyHandler = new MyHandler();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_okhttp_three_download);
        mProgressBar = (ProgressBar) findViewById(R.id.mProgressBar);
        progressValue = (TextView) findViewById(R.id.progressValue);


        pause = (ImageView) findViewById(R.id.pause);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPause) {
                    pause.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp);
                    DownloadHelper.startDownload(breakPointValue, mMyHandler);
                } else {
                    pause.setImageResource(R.drawable.ic_play_circle_outline_black_24dp);
                    DownloadHelper.cancelDownload();
                    breakPointValue = totalValue;
                }
                isPause = !isPause;

            }
        });

        findViewById(R.id.retry).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetStatus();
                pause.performClick();
            }
        });

        findViewById(R.id.stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isStop = true;
                resetStatus();
            }
        });

    }


    private void resetStatus() {
        DownloadHelper.cancelDownload();
        mProgressBar.setProgress(0);
        progressValue.setText("0");
        isPause = true;
        isStop = false;
        pause.setImageResource(R.drawable.ic_play_circle_outline_black_24dp);
        breakPointValue = 0;
        totalValue = 0;
    }


    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 400:
                    Toast.makeText(mContext, "不支持断点续传", Toast.LENGTH_SHORT).show();
                    break;
                case 100:
                    Toast.makeText(mContext, "fail", Toast.LENGTH_SHORT).show();
                    break;
                case 300:
                    int total = msg.arg1;
                    int current = msg.arg2;
                    if (!isPause && !isStop) {
                        totalValue = current + breakPointValue;

                        int percent = (int) (totalValue * 100f / (total + breakPointValue));
                        if (percent < 100) {
                            mProgressBar.setProgress(percent);
                            progressValue.setText(String.valueOf(percent));
                        } else {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setDataAndType(Uri.parse("file://" + Constants.FILE_PATH),
                                    "application/vnd.android.package-archive");
                            mContext.startActivity(intent);
                            resetStatus();
                        }
                    }


                    break;
                default:
                    break;
            }
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        DownloadHelper.cancelDownload();
    }
}
