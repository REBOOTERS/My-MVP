package huyifei.mymvp.databind;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import huyifei.mymvp.R;
import huyifei.mymvp.databinding.ActivityContentBinding;

public class ContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityContentBinding mActivityContentBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_content);
        User mUser=new User("mike","123456789");
        mActivityContentBinding.setUser(mUser);
    }
}
