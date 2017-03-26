package huyifei.mymvp.datastorage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import huyifei.mymvp.MyApplication;
import huyifei.mymvp.R;
import huyifei.mymvp.util.V;

import static huyifei.mymvp.MyApplication.getDaoInstant;

public class GreenDaoActivity extends AppCompatActivity {
    private static final String TAG = "GreenDaoActivity";
    private Button add, get;

    private int key = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        setView();
    }

    private void setView() {
        add = V.f(this, R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                key++;
                User user = new User(key, "mike_" + key, "famle", 18);
                getDaoInstant().getUserDao().insert(user);
            }
        });

        get = V.f(this, R.id.get);
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<User> users = MyApplication.getDaoInstant().getUserDao().loadAll();
                Log.e(TAG, "onClick: user's size " + users.size() + "\n");
                for (User user : users) {
                    Log.e(TAG, "\n usrename " + user.getName());
                }

            }
        });
    }
}
