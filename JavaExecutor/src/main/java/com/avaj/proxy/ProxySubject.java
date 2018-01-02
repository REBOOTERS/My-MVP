package com.avaj.proxy;

/**
 * author : engineer
 * e-mail : yingkongshi11@gmail.com
 * time   : 2018/1/2
 * desc   :
 * version: 1.0
 */
public class ProxySubject implements Subject {

    private RealSubject mRealSubject;

    public ProxySubject(RealSubject realSubject) {
        mRealSubject = realSubject;
    }

    @Override
    public void doSomething() {
        mRealSubject.doSomething();
    }
}
