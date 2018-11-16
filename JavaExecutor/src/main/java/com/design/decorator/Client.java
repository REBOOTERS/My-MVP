package com.design.decorator;

/**
 * @author: zhuyongging
 * @since: 2018-11-16
 * @desc
 */
public class Client {
    public static void main(String[] args) {
        AbstrctCompoent apple = new ConcreteCompoentApple();
        apple.doSomeThing();

        // 装饰一下
        apple = new ConcreteDecorator(apple);
        apple.doSomeThing();

        AbstrctCompoent subApple = new SubConcreteComponetApp();
        subApple.doSomeThing();
    }
}
