package com.design.factory;

/**
 * @author: zhuyongging
 * @since: 2019-04-25
 */
public class Client {
    public static void main(String[] args) {
        IProduct product;
        product = ProductSimpleStaticFactory.make(ProductA.class);
        if (product != null) {
            product.buildProduct();
        }

        product = ProductSimpleStaticFactory.make(ProductB.class);
        if (product != null) {
            product.buildProduct();
        }
    }
}
