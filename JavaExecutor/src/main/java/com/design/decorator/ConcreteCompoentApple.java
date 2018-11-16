package com.design.decorator;

/**
 * @author: zhuyongging
 * @since: 2018-11-16
 * @desc
 */
public class ConcreteCompoentApple implements AbstrctCompoent {
    @Override
    public void doSomeThing() {
        String result = "I'm just an Apple";
        System.out.println(result);
    }
}
