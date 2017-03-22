package home.smart.fly.httpurlconnectiondemo.download;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;

import home.smart.fly.httpurlconnectiondemo.R;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;

public class DownloadActivity extends AppCompatActivity implements View.OnClickListener, ProgressResponseBody.ProgressListener {
    private static final String TAG = "DownloadActivity";
    public static final String PACKAGE_URL = "http://gdown.baidu.com/data/wisegame/df65a597122796a4/weixin_821.apk";
    ProgressBar progressBar;
    private long breakPoints;
    private ProgressDownloader downloader;
    private File file;
    private long totalBytes;
    private long contentLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        progressBar = (ProgressBar) findViewById(R.id.mProgressBar);
        findViewById(R.id.downloadButton).setOnClickListener(this);
        findViewById(R.id.pause_button).setOnClickListener(this);
        findViewById(R.id.continue_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        if (id == R.id.downloadButton) {
            // 新下载前清空断点信息
            breakPoints = 0L;
            file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "sample.apk");
            downloader = new ProgressDownloader(PACKAGE_URL, file, this);
            downloader.download(0L);
        } else if (id == R.id.pause_button) {
            downloader.pause();
            Toast.makeText(this, "下载暂停", Toast.LENGTH_SHORT).show();
            // 存储此时的totalBytes，即断点位置。
            breakPoints = totalBytes;
        } else if (id == R.id.continue_button) {
            downloader.download(breakPoints);
        }


    }

    @Override
    public void onPreExecute(long contentLength) {
        // 文件总长只需记录一次，要注意断点续传后的contentLength只是剩余部分的长度
        if (this.contentLength == 0L) {
            this.contentLength = contentLength;
            progressBar.setMax((int) (contentLength / 1024));
        }
    }

    @Override
    public void update(long totalBytes, boolean done) {
        // 注意加上断点的长度
        this.totalBytes = totalBytes + breakPoints;
        progressBar.setProgress((int) (totalBytes + breakPoints) / 1024);
        if (done) {
            // 切换到主线程
            Observable
                    .empty()
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnCompleted(new Action0() {
                        @Override
                        public void call() {
                            Toast.makeText(DownloadActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .subscribe();
        }
    }

}
