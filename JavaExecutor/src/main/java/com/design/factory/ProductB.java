package com.design.factory;

/**
 * @author: zhuyongging
 * @since: 2019-04-25
 */
public class ProductB implements IProduct {
    @Override
    public void buildProduct() {
        System.out.println("this is product B");
    }
}
