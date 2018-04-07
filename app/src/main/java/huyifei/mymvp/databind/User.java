package huyifei.mymvp.databind;

/**
 * author : engineer
 * e-mail : yingkongshi11@gmail.com
 * time   : 2018/4/7
 * desc   :
 * version: 1.0
 */
public class User {
    private String name;
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
