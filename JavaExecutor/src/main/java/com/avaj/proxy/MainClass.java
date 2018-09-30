package com.avaj.proxy;

import java.lang.reflect.Proxy;

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


}
