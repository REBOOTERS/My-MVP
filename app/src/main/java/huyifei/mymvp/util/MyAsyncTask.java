package huyifei.mymvp.util;

import android.os.AsyncTask;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created by co-mall on 2017/6/7.
 */

public class MyAsyncTask<Params,Progress,Result> {

    private static abstract class WorkRunnable<Params,Result> implements Callable<Result>{
        Params[] params;
    }

    WorkRunnable<Params,Result> mWorker;
    FutureTask<Result> mFutureTask;

    public MyAsyncTask(){
        mWorker=new WorkRunnable<Params, Result>() {
            @Override
            public Result call() {
                return null;
            }
        };

        mFutureTask=new FutureTask<Result>(mWorker){
            @Override
            protected void done() {
                super.done();
            }
        };

        new MyTask().execute("");
    }

    class MyTask extends AsyncTask<String,Void,Void>{

        @Override
        protected Void doInBackground(String... strings) {
            return null;
        }
    }
}
