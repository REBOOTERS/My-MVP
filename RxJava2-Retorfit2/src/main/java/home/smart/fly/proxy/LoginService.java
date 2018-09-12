package home.smart.fly.proxy;

import home.smart.fly.proxy.annotations.Param;
import home.smart.fly.proxy.annotations.URL;
import home.smart.fly.proxy.model.User;

/**
 * @author: zhuyongging
 * @date: 2018-09-10
 * @desc
 */
public interface LoginService {

    @URL("https://www.mock.com")
    User login(@Param("username") String username,@Param("password") String password);
}
