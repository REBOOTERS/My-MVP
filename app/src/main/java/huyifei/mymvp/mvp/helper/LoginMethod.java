package huyifei.mymvp.mvp.helper;

import huyifei.mymvp.mvp.bean.User;

/**
 * Created by rookie on 2016/4/7.
 */
public class LoginMethod implements LoginInterface {
    @Override
    public void login(final String username, final String password, final OnLoginListener loginListener) {
        //模拟子线程耗时操作
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //模拟登录成功
                if ("zhy".equals(username) && "123".equals(password)) {
                    User user = new User();
                    user.setUserName(username);
                    user.setPassWord(password);
                    loginListener.onLoginSuccess(user);
                } else {
                    loginListener.onLoginFailed();
                }
            }
        }.start();
    }
}
