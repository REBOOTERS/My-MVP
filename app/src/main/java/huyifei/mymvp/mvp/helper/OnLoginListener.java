package huyifei.mymvp.mvp.helper;

import huyifei.mymvp.mvp.bean.User;

/**
 * Created by rookie on 2016/4/7.
 */
public interface OnLoginListener {
    public void onLoginSuccess(User resutl);
    public void onLoginFailed();
}
