package home.smart.fly.proxy.impl;

import java.util.Map;

import home.smart.fly.proxy.interfaces.IRequest;

/**
 * @author: zhuyongging
 * @date: 2018-09-10
 * @desc
 */
public class Request implements IRequest {

    String url;
    Map<String, Object> params;
    Class<?> responseCls;

    public Request(String url, Map<String, Object> params, Class<?> responseCls) {
        this.url = url;
        this.params = params;
        this.responseCls = responseCls;
    }

    @Override
    public String url() {
        return url;
    }

    @Override
    public Map<String, Object> params() {
        return params;
    }

    @Override
    public Class<?> responseCls() {
        return responseCls;
    }
}
