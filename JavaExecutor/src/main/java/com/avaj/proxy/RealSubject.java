package com.avaj.proxy;

/**
 * Created by Rookie on 2017/9/28.
 */

public class RealSubject implements Subject {
    @Override
    public void doSomething() {
        System.out.println("This is real doSomeThing");
    }
}
