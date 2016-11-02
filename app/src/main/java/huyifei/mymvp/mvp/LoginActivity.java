package huyifei.mymvp.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import huyifei.mymvp.R;
import huyifei.mymvp.mvp.bean.User;
import huyifei.mymvp.mvp.presenter.UserLoginPresenter;
import huyifei.mymvp.mvp.view.IUserLoginView;
import huyifei.mymvp.util.V;

/**
 * Created by rookie on 2016/4/7.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener,IUserLoginView{
    private Context mContext;

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private Button login;
    private Button clear;

    private ProgressBar progressBar;

    private UserLoginPresenter mUserLoginPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        mEmailView = V.f(this, R.id.email);
        mPasswordView = V.f(this, R.id.password);

        login=V.f(this,R.id.sign_in_button);
        login.setOnClickListener(this);
        clear = V.f(this, R.id.clear_button);
        clear.setOnClickListener(this);

        progressBar = V.f(this, R.id.login_progress);

        mUserLoginPresenter = new UserLoginPresenter(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                mUserLoginPresenter.login();
                break;
            case R.id.clear_button:
                mUserLoginPresenter.clear();
                break;
            default:
                break;
        }
    }


    @Override
    public String getUserName() {
        return mEmailView.getText().toString();
    }

    @Override
    public String getPassword() {
        return mPasswordView.getText().toString();
    }

    @Override
    public void clearUserName() {
        mEmailView.setText("");
    }

    @Override
    public void clearPassword() {
        mPasswordView.setText("");
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void toMainActivity(User user) {
        Toast.makeText(this, user.getUserName() +
                " login success , to MainActivity", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this,
                "login failed", Toast.LENGTH_SHORT).show();
    }
}
