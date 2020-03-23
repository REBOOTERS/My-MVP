package huyifei.mymvp.classloader;

import android.os.Bundle;
import android.os.Environment;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import huyifei.mymvp.R;

public class SimpleHotFixActivity extends AppCompatActivity {

    @BindView(R.id.loadPatch)
    Button mLoadPatch;
    @BindView(R.id.testBug)
    Button mTestBug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_hot_fix);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.loadPatch, R.id.testBug})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loadPatch:
                FixDexUtils.loadFixedDex(this, Environment.getExternalStorageDirectory());
                break;
            case R.id.testBug:
                SimpleHotFixBugTest mBugTest=new SimpleHotFixBugTest();
                mBugTest.getBug(this);
                break;
            default:
                break;
        }
    }
}
