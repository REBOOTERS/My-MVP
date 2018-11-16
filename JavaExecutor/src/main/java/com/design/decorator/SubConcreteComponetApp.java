package com.design.decorator;

/**
 * @author: zhuyongging
 * @since: 2018-11-16
 * @desc
 */
public class SubConcreteComponetApp extends ConcreteCompoentApple {
    @Override
    public void doSomeThing() {
        System.out.println("I'm a sub apple");
        super.doSomeThing();
    }
}
