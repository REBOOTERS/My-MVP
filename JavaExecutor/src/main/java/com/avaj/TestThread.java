package com.avaj;

/**
 * Created by Rookie on 2017/11/3.
 * <p>
 * <a href="https://juejin.im/post/59f9804851882554b836dd8b">problem</a>
 */

public class TestThread implements Runnable {
    int b = 100;

    synchronized void m1() throws InterruptedException {
        b = 1000;
        Thread.sleep(500); //6
        System.out.println("b=" + b);
    }

    synchronized void m2() throws InterruptedException {
        Thread.sleep(250); //5
        b = 2000;
    }

    public static void main(String[] args) throws InterruptedException {
        TestThread tt = new TestThread();
        Thread t = new Thread(tt);  //1
        t.start(); //2
//
        tt.m2(); //3
        System.out.println("main thread b=" + tt.b); //4


        String size = "middle";

        String result = size.equals("large") ? "100" : size.equals("middle") ? "50" : size.equals("samll") ? "20" : size;

        System.out.println("the result is " + result);

    }

    @Override
    public void run() {
        try {
            m1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
