package com.avaj.proxy;

import java.io.FileOutputStream;
import java.lang.reflect.Proxy;

import sun.misc.ProxyGenerator;

/**
 * Created by Rookie on 2017/9/28.
 */

public class MainClass {
    public static void main(String[] args) {
        // 委托类
        Subject mRealSubject = new RealSubject();
        // 委托类classLoader
        ClassLoader mClassLoader = mRealSubject.getClass().getClassLoader();
        // 委托类对应的ProxyHandler
        DynamicProxyHandler mProxyHandler = new DynamicProxyHandler(mRealSubject);
        Class[] mClasses = new Class[]{Subject.class};
        // 代理类
        Subject proxySubject = (Subject) Proxy.newProxyInstance(mClassLoader, mClasses, mProxyHandler);
        // 代理类调用方法
        proxySubject.doSomething();

    }


    private static void createProxyClassFile() {
        String name = "ProxySubject";
        byte[] data = ProxyGenerator.generateProxyClass(name, new Class[]{Subject.class});
        try {
            FileOutputStream out = new FileOutputStream(name + ".class");
            out.write(data);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
