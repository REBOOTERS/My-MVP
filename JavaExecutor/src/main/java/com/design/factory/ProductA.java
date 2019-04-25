package com.design.factory;

/**
 * @author: zhuyongging
 * @since: 2019-04-25
 */
public class ProductA implements IProduct {
    @Override
    public void buildProduct() {
        System.out.println("This is Product A ");
    }
}
