package com.avaj.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Rookie on 2017/9/28.
 */

public class ProxyHandler implements InvocationHandler {
    private Object mObject;


    public ProxyHandler(Object object) {
        mObject = object;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        return method.invoke(mObject, objects);
    }
}
