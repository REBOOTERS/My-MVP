package huyifei.mymvp.architecture.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import huyifei.mymvp.R;

public class MVPActivity extends AppCompatActivity {
    private Context mContext;
    private Button downloadBtn;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        init();
    }

    private void init() {
        //view init
        downloadBtn = (Button) findViewById(R.id.downloadBtn);
        mImageView = (ImageView) findViewById(R.id.image);
    }
}
