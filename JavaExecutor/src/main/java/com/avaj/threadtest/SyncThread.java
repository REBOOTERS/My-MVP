package com.avaj.threadtest;

/**
 * Created by rookie on 2017/1/16.
 */

public class SyncThread implements Runnable {
    private static int count;

    public SyncThread() {
        count = 0;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                add();
                System.out.println(Thread.currentThread().getName() + ":" + count);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void add() {
        count++;
    }

    public int getCount() {
        return count;
    }
}
