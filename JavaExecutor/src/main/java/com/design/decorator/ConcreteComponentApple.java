package com.design.decorator;

/**
 * @author: Rookie
 * @since: 2018-11-16
 * @desc
 */
public class ConcreteComponentApple implements AbstractComponent {
    @Override
    public void doSomeThing() {
        String result = "I'm just an Apple";
        System.out.println(result);
    }
}
