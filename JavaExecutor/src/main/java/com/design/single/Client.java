package com.design.single;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: zhuyongging
 * @since: 2019-04-25
 */
public class Client {
    public static void main(String[] args) {
        Single single = Single.getInstance();
        print(single.hashCode());
        long t = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                Single single1 = Single.getInstance();
                print(single1.hashCode());
            });
        }
        System.out.println("host time ==" + (System.currentTimeMillis() - t));
        executorService.shutdown();
    }

    private static void print(int hashcode) {
        System.out.print("currentThread==" + Thread.currentThread().getName() + "     ");
        System.out.println("single's hashcode==" + String.valueOf(hashcode));
    }
}
