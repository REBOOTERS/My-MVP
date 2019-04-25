package com.design.single;

/**
 * @author: zhuyongging
 * @since: 2019-04-25
 */
public class Single {

    private Single() {
    }

    public static Single getInstance() {
        return Holder.mInstance;
    }

    private static class Holder {
        static final Single mInstance = new Single();
    }

}
