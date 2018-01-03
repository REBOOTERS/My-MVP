package com.avaj.proxy;

/**
 * author : engineer
 * e-mail : yingkongshi11@gmail.com
 * time   : 2018/1/2
 * desc   :
 * version: 1.0
 */
public class Client {
    public static void main(String[] args) {
        //创建委托类
        Subject mRealSubject=new RealSubject();
        //创建代理类
        ProxySubject mProxy = new ProxySubject(mRealSubject);
        //由代理类去做具体的操作
        mProxy.doSomething();
    }
}
