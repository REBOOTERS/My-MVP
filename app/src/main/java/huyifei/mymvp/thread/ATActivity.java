package huyifei.mymvp.thread;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;

import huyifei.mymvp.R;

public class ATActivity extends AppCompatActivity {
    private static final String TAG = "ATActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_at);
        findViewById(R.id.execute).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: " + Runtime.getRuntime().availableProcessors());
                for (int i = 0; i < 137; i++) {
//                    new MyAsyncTask("task#" + i).execute("");
                    new MyAsyncTask("AsyncTask#" + i).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
                }

            }
        });
    }

    class MyAsyncTask extends AsyncTask<String, Integer, String> {
        private String taskName;

        public MyAsyncTask(String taskName) {
            super();
            this.taskName = taskName;
        }


        @Override
        protected String doInBackground(String... params) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return taskName;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Log.e(TAG, s + "execute finish at " + df.format(new Date()));
        }
    }
}
