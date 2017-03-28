package huyifei.mymvp.mvp.view;

import huyifei.mymvp.mvp.bean.User;

/**
 * Created by rookie on 2016/4/7.
 */
public interface IUserLoginView {
    String getUserName();

    String getPassword();

    void clearUserName();

    void clearPassword();

    void showLoading();

    void hideLoading();

    void toMainActivity(User user);

    void showFailedError();
}
