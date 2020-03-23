package huyifei.mymvp.classloader;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;

import huyifei.mymvp.R;

public class ClassLoaderActivity extends AppCompatActivity {
    private static final String TAG = "ClassLoaderActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_loader);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
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
