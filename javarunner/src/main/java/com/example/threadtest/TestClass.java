package com.example.threadtest;

/**
 * Created by rookie on 2017/1/16.
 */

public class TestClass {
    public static void main(String[] args) {
        SyncThread syncThread = new SyncThread();
        Thread thread1 = new Thread(syncThread, "SyncThread1");
        Thread thread2 = new Thread(syncThread, "SyncThread2");
        Thread thread3 = new Thread(syncThread, "SyncThread3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
