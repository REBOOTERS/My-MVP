package com.avaj.threadtest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by co-mall on 2017/6/7.
 */

public class FutureAndCallableTest {
    static class MyCallable implements Callable<Long> {

        private Integer count;

        public MyCallable(Integer count) {
            this.count = count;
        }

        @Override
        public Long call() throws Exception {
            long sum = 0;
            for (int i = 0; i < count; i++) {
                sum = sum + i;
                System.out.println("now sum is " + sum);
                Thread.sleep(5000);
            }
            return sum;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable mMyCallable = new MyCallable(10);

        FutureTask<Long> mTask = new FutureTask<>(mMyCallable);
        Thread mThread = new Thread(mTask);
        mThread.start();

        while (!mTask.isDone()){
            if (mTask.get() >= 21) {
                if (mTask.cancel(true)) {
                    System.err.println("cancel success");
                } else {
                    System.err.println("cancel failure");

                }
            }
        }





        System.out.println("now the sum is " + mTask.get());


    }
}
