package com.example;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by rookie on 2017/2/24.
 */

public class ProxyTest {
    public static void main(String[] args) {
        String action = "printlog";
        Class classname = String.class.getClass();
        String name = "name";
        ProxyMethod(action, name);
    }

    private static void ProxyMethod(String action, String name) {
        try {
            Method method = ProxyTest.class.getDeclaredMethod(action, String.class);
            method.invoke(ProxyTest.class.newInstance(), name);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    public void printlog(String x) {
        System.out.println("the param is " + x);
    }
}
