package com.avaj.threadtest;

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
                System.out.println("计时: " + mTasks.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int amount;
        try {
            amount = mTasks.get();
            System.out.println("finish: " + amount);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}
