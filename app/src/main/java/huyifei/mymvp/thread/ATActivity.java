package huyifei.mymvp.thread;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import huyifei.mymvp.R;

public class ATActivity extends AppCompatActivity {
    private static final String TAG = "ATActivity";
    private StringBuilder sb = new StringBuilder();
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_at);
        result = (TextView) findViewById(R.id.result);
        findViewById(R.id.execute).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: " + Runtime.getRuntime().availableProcessors());
                for (int i = 0; i < 138; i++) {
//                    new MyAsyncTask().execute("Task#" + i);
                    new MyAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "AsyncTask#" + i);
                }

            }
        });
    }

    class MyAsyncTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress();
            return params[0];


        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
            Log.e(TAG, s + "execute finish at " + df.format(new Date()));
        }
    }
}
