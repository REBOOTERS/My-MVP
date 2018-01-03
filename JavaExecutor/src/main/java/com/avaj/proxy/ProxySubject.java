package com.avaj.proxy;

/**
 * author : engineer
 * e-mail : yingkongshi11@gmail.com
 * time   : 2018/1/2
 * desc   :
 * version: 1.0
 */
public class ProxySubject implements Subject {

    private Subject mSubject;
    // 代理类持有委托类的引用
    public ProxySubject(Subject realSubject) {
        mSubject = realSubject;
    }

    @Override
    public void doSomething() {
        mSubject.doSomething();
    }
}
