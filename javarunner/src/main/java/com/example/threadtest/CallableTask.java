package com.example.threadtest;

import java.util.concurrent.Callable;

/**
 * Created by rookie on 2017/1/19.
 */

/**
 * 定义一个Callable 任务，返回类型为Integer
 */
public class CallableTask implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        int hours=5;
        int amount = 0;
        while(hours>0){
            System.out.println("I'm working,rest is "+hours);
            amount++;
            hours--;
            Thread.sleep(1000);
        }
        return amount;
    }
}
