package home.smart.fly.proxy;

import com.google.gson.Gson;

import java.util.Map;

import home.smart.fly.proxy.model.User;

/**
 * @author: Rookie
 * @date: 2018-09-10
 * @desc
 */
public class FakeHelper {
    private static final Gson sGson = new Gson();

    public static String request(String url, Map<String, Object> params) {
        if (params != null) {
            if ("123".equals(params.get("username"))
                    && "456".equals(params.get("password"))) {
                User user = new User();
                user.address = "杭州";
                user.sex = "男";
                user.uId = "Id";
                user.username = "啊啊";
                return sGson.toJson(user);
            }
        }
        return null;
    }
}
