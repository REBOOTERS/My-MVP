package com.avaj.threadtest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rookie on 2017/2/21.
 */

public class MainClass {
    public static void main(String[] args) {
//        SubExecutor subExecutor = new SubExecutor();
//        subExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//
//                System.out.println("This is subExecutor in :" + Thread.currentThread().getName());
//            }
//        });
//
//        DirectExecutor directExecutor=new DirectExecutor();
//        directExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("This is directExecutor in :" + Thread.currentThread().getName());
//            }
//        });

        List list = new ArrayList();
        list.add(111);
        list.add("111");

        for(int i=0;i<list.size();i++) {
            String value = (String) list.get(i);
            System.out.println("the value is "+value);
        }
    }
}
