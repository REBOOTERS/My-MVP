package huyifei.mymvp.mvp.presenter;

import android.os.Handler;

import huyifei.mymvp.mvp.bean.User;
import huyifei.mymvp.mvp.helper.LoginMethod;
import huyifei.mymvp.mvp.helper.OnLoginListener;
import huyifei.mymvp.mvp.view.IUserLoginView;

/**
 * Created by rookie on 2016/4/7.
 */
public class UserLoginPresenter {
    private LoginMethod userBiz;
    private IUserLoginView userLoginView;
    private Handler mHandler = new Handler();

    public UserLoginPresenter(IUserLoginView userLoginView) {
        this.userLoginView = userLoginView;
        this.userBiz = new LoginMethod();
    }

    public void login() {
        userLoginView.showLoading();
        userBiz.login(userLoginView.getUserName(), userLoginView.getPassword(), new OnLoginListener() {
            @Override
            public void onLoginSuccess(final User user) {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.toMainActivity(user);
                        userLoginView.hideLoading();
                    }
                });
            }

            @Override
            public void onLoginFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.showFailedError();
                        userLoginView.hideLoading();
                    }
                });
            }


        });
    }

    public void clear() {
        userLoginView.clearUserName();
        userLoginView.clearPassword();
    }
}
