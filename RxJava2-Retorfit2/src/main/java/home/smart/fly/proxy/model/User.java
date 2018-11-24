package home.smart.fly.proxy.model;

/**
 * @author: Rookie
 * @date: 2018-09-10
 * @desc
 */
public class User {
    public String url;
    public String username;
    public String uId;
    public String sex;
    public String address;

    @Override
    public String toString() {
        return "User{" +
                "url='" + url + '\'' +
                "username='" + username + '\'' +
                ", uId='" + uId + '\'' +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
