package home.smart.fly.httpurlconnectiondemo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by rookie on 2016/11/3.
 * <p>
 */

public class Okhttp3DownloadsActivity extends AppCompatActivity {
    private static final String TAG = "Okhttp3DownloadsActivit";
    public static final String PACKAGE_URL = "https://static.dongqiudi.com/app/apk/dongqiudi_website.apk";
    private final String FILE_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + File.separator + "dqd.apk";
    private Context mContext;


    private OkHttpClient client;
    private Request request;
    private MyHandler handler;

    private Call downloadCall;

    private ProgressBar mProgressBar;
    private TextView progressValue;

    private int startPoint = 0;
    private int breakPointValue;
    private int totalValue;

    private ImageView pause;
    private boolean paused = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        handler = new MyHandler();
        client = new OkHttpClient();

        setContentView(R.layout.activity_okhttp_three_download);


        mProgressBar = (ProgressBar) findViewById(R.id.mProgressBar);
        progressValue = (TextView) findViewById(R.id.progressValue);


        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                    breakPointValue = 0;
                    totalValue = 0;
                    download(0);
                }
            }
        });

        pause = (ImageView) findViewById(R.id.pause);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!paused) {
                    paused = true;
                    pause.setImageResource(R.drawable.ic_play_circle_outline_black_24dp);
                    downloadCall.cancel();
                    breakPointValue = totalValue;
                } else {
                    pause.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp);
                    paused = false;
                    download(breakPointValue);
                }

            }
        });

        findViewById(R.id.retry).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                download(breakPointValue);
            }
        });

        findViewById(R.id.stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadCall.cancel();
                mProgressBar.setProgress(0);
                progressValue.setText("0");
            }
        });

    }

    private void download(int startPoint) {
        Log.e(TAG, "download: the startPoint is " + startPoint);
        this.startPoint = startPoint;
        request = new Request.Builder()
                .url(PACKAGE_URL)
                .header("RANGE", "bytes=" + startPoint + "-")
                .build();
        downloadCall = client.newCall(request);
        downloadCall.enqueue(new DownloadCallback());
    }


    private class DownloadCallback implements Callback {

        @Override
        public void onFailure(Call call, IOException e) {
            Toast.makeText(mContext, "error", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onResponse(Call call, Response response) {
            FileChannel fileChannel = null;
            ResponseBody body = response.body();
            int total = (int) body.contentLength();
            int currentLength = 0;
            InputStream inputStream = body.byteStream();

            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(FILE_PATH, "rws");
                fileChannel = randomAccessFile.getChannel();
                Log.e(TAG, "onResponse: startPoint=" + startPoint + " ,total=" + total);
                MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, startPoint, total);
                int len;
                byte[] buffer = new byte[1024];
                while ((len = inputStream.read(buffer)) != -1) {

                    currentLength = currentLength + len;
                    mappedByteBuffer.put(buffer, 0, len);

                    Message msg = Message.obtain();
                    msg.arg1 = total;
                    msg.arg2 = currentLength;
                    msg.what = 300;
                    handler.sendMessage(msg);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                    if (fileChannel != null) {
                        fileChannel.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
    }


    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 100:
                    Object e = msg.obj;
                    Toast.makeText(mContext, e.toString(), Toast.LENGTH_SHORT).show();
                    break;
                case 300:
                    int total = msg.arg1;
                    int current = msg.arg2;
                    if (!paused) {
                        totalValue = current + breakPointValue;
                        Log.e(TAG, "handleMessage: totalvalue=" + totalValue);

                        int percent = (int) (totalValue * 100f / (total + breakPointValue));
                        if (percent < 100) {
                            mProgressBar.setProgress(percent);
                            progressValue.setText(String.valueOf(percent));
                        } else {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setDataAndType(Uri.parse("file://" + FILE_PATH),
                                    "application/vnd.android.package-archive");
                            mContext.startActivity(intent);
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
        if (downloadCall != null) {
            downloadCall.cancel();
        }
    }
}
