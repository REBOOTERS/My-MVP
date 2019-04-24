package com.avaj.threadtest;

/**
 * @author: zhuyongging
 * @since: 2019-04-21
 */
public class DeadLockDemo {

    private static Object mObjectA = new Object();
    private static Object mObjectB = new Object();

    public static void main(String[] args) {

        Thread threadA = new Thread(() -> {
            synchronized (mObjectA) {
                System.out.println(Thread.currentThread() + "get objectA");
//                sleep();

                System.out.println(Thread.currentThread() + "try to get objectB");
                synchronized (mObjectB) {
                    System.out.println(Thread.currentThread() + "get ObjectB");
                }
            }
        });

        Thread threadB = new Thread(() -> {
            synchronized (mObjectB) {
                System.out.println(Thread.currentThread() + "get objectB");
//                sleep();

                System.out.println(Thread.currentThread() + "try to get objectA");
                synchronized (mObjectA) {
                    System.out.println(Thread.currentThread() + "get ObjectA");
                }
            }
        });

        threadA.start();
        sleep();
//        sleep();
        threadB.start();
    }

    private static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
