package com.engineer.jetpack.ui;

import androidx.databinding.DataBindingUtil;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.engineer.jetpack.R;
import com.engineer.jetpack.databinding.ActivityDbBinding;
import com.engineer.jetpack.model.User;


/**
 *  Data Binding demo
 */
public class DBActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDbBinding dbBinding = DataBindingUtil.setContentView(this, R.layout.activity_db);
        User user = new User("mike","22");
        dbBinding.setUser(user);
    }
}
