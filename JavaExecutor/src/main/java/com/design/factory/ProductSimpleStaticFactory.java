package com.design.factory;

/**
 * @author: zhuyongging
 * @since: 2019-04-25
 */
public class ProductSimpleStaticFactory {
    public static <T extends IProduct> T make(Class<T> t) {
        IProduct product = null;
        try {
            Class className = Class.forName(t.getName());
            product = (IProduct) className.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        if (product != null) {
            return (T) product;
        } else {
            return null;
        }
    }
}
