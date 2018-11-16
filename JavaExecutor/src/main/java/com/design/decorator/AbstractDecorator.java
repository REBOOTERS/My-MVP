package com.design.decorator;

/**
 * @author: zhuyongging
 * @since: 2018-11-16
 * @desc
 */
public class AbstractDecorator implements AbstrctCompoent {

    protected AbstrctCompoent mAbstrctCompoent;

    public AbstractDecorator(AbstrctCompoent abstrctCompoent) {
        mAbstrctCompoent = abstrctCompoent;
    }

    @Override
    public void doSomeThing() {
         mAbstrctCompoent.doSomeThing();
    }
}
