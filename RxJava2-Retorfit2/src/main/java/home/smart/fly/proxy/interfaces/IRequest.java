package home.smart.fly.proxy.interfaces;

import java.util.Map;

/**
 * @author: Rookie
 * @date: 2018-09-10
 * @desc
 */
public interface IRequest {
    String url();

    Map<String, Object> params();

    Class<?> responseCls();
}
