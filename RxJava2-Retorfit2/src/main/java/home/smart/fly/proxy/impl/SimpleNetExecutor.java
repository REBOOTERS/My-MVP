package home.smart.fly.proxy.impl;

import com.google.gson.Gson;

import java.lang.reflect.Type;

import home.smart.fly.proxy.FakeHelper;
import home.smart.fly.proxy.interfaces.INetExecutor;
import home.smart.fly.proxy.interfaces.IRequest;

/**
 * @author: zhuyongging
 * @date: 2018-09-10
 * @desc
 */
public class SimpleNetExecutor implements INetExecutor {
    @Override
    public <T> T executor(IRequest request) {
        String response = FakeHelper.request(request.url(),request.params());
        return new Gson().fromJson(response, (Type) request.responseCls());
    }
}
