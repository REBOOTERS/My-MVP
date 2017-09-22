package home.smart.fly.httpurlconnectiondemo.downloads;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

import home.smart.fly.httpurlconnectiondemo.R;


/**
 * Created by rookie on 2016/11/3.
 * <p>
 */

public class Okhttp3DownloadsThreadActivity extends AppCompatActivity {

    private static final String TAG = "Okhttp3DownloadsThreadActivity";
    private Context mContext;


    private ProgressBar mProgressBar;
    private TextView progressValue;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_okhttp_three_download_thread);
        mProgressBar = (ProgressBar) findViewById(R.id.mProgressBar);
        progressValue = (TextView) findViewById(R.id.progressValue);
    }
}
