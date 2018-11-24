package com.design.decorator;

/**
 * @author: Rookie
 * @since: 2018-11-16
 * @desc
 */
public class AbstractDecorator implements AbstractComponent {

    protected AbstractComponent mAbstractComponent;

    public AbstractDecorator(AbstractComponent abstractComponent) {
        mAbstractComponent = abstractComponent;
    }

    @Override
    public void doSomeThing() {
         mAbstractComponent.doSomeThing();
    }
}
