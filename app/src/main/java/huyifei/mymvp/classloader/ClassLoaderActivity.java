package huyifei.mymvp.classloader;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import huyifei.mymvp.R;

public class ClassLoaderActivity extends AppCompatActivity {
    private static final String TAG = "ClassLoaderActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_loader);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ClassLoader mClassLoader = ClassLoaderActivity.class.getClassLoader();
        while (mClassLoader != null) {
            Log.e(TAG, "onCreate: mClassLoader=" + mClassLoader);
            mClassLoader = mClassLoader.getParent();
        }

    }

}
