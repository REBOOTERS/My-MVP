package com.example.threadtest.reaecondition;

/**
 * Created by engineer on 2017/5/29.
 */

public class TestSync implements Runnable {

    private static final int COUNT = 500000;
    private int count;


    @Override
    public void run() {
        for (int i = 0; i < COUNT; i++) {
            increment();
            System.err.println("count=" + count);
        }
    }

    private synchronized void increment() {
        count = count + 1;
    }

    public static void main(String[] args) {
        TestSync mRunnable = new TestSync();
        Thread a = new Thread(mRunnable);
        Thread b = new Thread(mRunnable);
        a.start();
        b.start();
    }
}
