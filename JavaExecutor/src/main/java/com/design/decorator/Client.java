package com.design.decorator;

/**
 * @author: Rookie
 * @since: 2018-11-16
 * @desc
 */
public class Client {
    public static void main(String[] args) {
        AbstractComponent apple = new ConcreteComponentApple();
        apple.doSomeThing();

        // 装饰一下
        apple = new ConcreteDecorator(apple);
        apple.doSomeThing();

        AbstractComponent subApple = new SubConcreteComponentApp();
        subApple.doSomeThing();
    }
}
