package com.zlw.lifequan.ui.login;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.zlw.lifequan.R;
import com.zlw.lifequan.bean.UserInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {


    @BindView(R.id.ac_login_et_user)
    EditText et_username;
    @BindView(R.id.ac_login_et_password)
    EditText et_password;

    private LoginContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setPresenter(new LoginPersenter());
        et_username.setText("123");
    }


    @OnClick(R.id.ac_login_bt_login)
    public void toLogin() {
        String username = et_username.getText().toString();
        String password = et_password.getText().toString();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            showMsg("用户名或密码不能为空");
            return;
        }
        presenter.toLogin(new UserInfo(username, password));
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void toMain() {
        showMsg("登陆成功");
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
        if (presenter != null) {
            presenter.addView(this);
        }
    }
}
