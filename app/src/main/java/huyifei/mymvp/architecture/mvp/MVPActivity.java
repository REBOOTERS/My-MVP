package huyifei.mymvp.architecture.mvp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import huyifei.mymvp.R;
import huyifei.mymvp.architecture.common.Constants;

public class MVPActivity extends AppCompatActivity implements IDownloadView {
    private Context mContext;
    private ImageView mImageView;
    private ProgressDialog progressDialog;

    private DownloadPresenter mDownloadPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_mvp);
        init();
    }

    private void init() {
        mDownloadPresenter = new DownloadPresenter(this);
        //view init
        mImageView = findViewById(R.id.image);
        findViewById(R.id.downloadBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDownloadPresenter.download(Constants.DOWNLOAD_URL);
            }
        });

        findViewById(R.id.downloadBtn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDownloadPresenter.download(Constants.DOWNLOAD_ERROR_URL);
            }
        });

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

    }

    @Override
    public void showProgressBar(boolean show) {
        if (show) {
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }
    }

    @Override
    public void setProcessProgress(int progress) {
        progressDialog.setProgress(progress);
    }

    @Override
    public void setView(String result) {
        Glide.with(mContext).load(result).into(mImageView);
    }

    @Override
    public void showFailToast() {
        Toast.makeText(mContext, "Download fail !", Toast.LENGTH_SHORT).show();
    }
}
