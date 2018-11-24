package com.design.decorator;

/**
 * @author: Rookie
 * @since: 2018-11-16
 * @desc
 */
public class SubConcreteComponentApp extends ConcreteComponentApple {
    @Override
    public void doSomeThing() {
        System.out.println("I'm a sub apple");
        super.doSomeThing();
    }
}
