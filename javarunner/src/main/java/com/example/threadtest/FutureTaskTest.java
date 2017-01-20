package com.example.threadtest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by rookie on 2017/1/19.
 */

public class FutureTaskTest {

    public static void main(String args[]) throws ExecutionException {
        CallableTask worker = new CallableTask();
        FutureTask<Integer> mTasks = new FutureTask<>(worker);
        new Thread(mTasks).start();

        while (!mTasks.isDone()) {
            try {
                System.out.println("看长工做完了没..." + mTasks.get());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int amount;
        try {
            amount = mTasks.get();
            System.out.println("工作做完了,上交了" + amount);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}
