package com.example.threadtest;

import com.example.threadtest.executor.DirectExecutor;
import com.example.threadtest.executor.SubExecutor;

/**
 * Created by rookie on 2017/2/21.
 */

public class MainClass {
    public static void main(String[] args) {
        SubExecutor subExecutor = new SubExecutor();
        subExecutor.execute(new Runnable() {
            @Override
            public void run() {

                System.out.println("This is subExecutor in :" + Thread.currentThread().getName());
            }
        });

        DirectExecutor directExecutor=new DirectExecutor();
        directExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("This is directExecutor in :" + Thread.currentThread().getName());
            }
        });



    }
}
