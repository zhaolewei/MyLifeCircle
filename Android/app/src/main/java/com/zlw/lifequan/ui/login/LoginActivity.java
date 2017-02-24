package com.zlw.lifequan.ui.login;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.zlw.lifequan.MainActivity;
import com.zlw.lifequan.R;
import com.zlw.lifequan.base.MyTestData;
import com.zlw.lifequan.bean.UserInfo;
import com.zlw.lifequan.net.Repository;
import com.zlw.lifequan.ui.register.RegisterActivity;
import com.zlw.lifequan.utils.Logger;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final String KEY_BEAN = "KEY_BEAN";

    @BindView(R.id.ac_login_et_user)
    EditText et_username;
    @BindView(R.id.ac_login_et_password)
    EditText et_password;

    private LoginContract.Presenter presenter;

    private UserInfo userInfo;


    public static void startMe(Context context, UserInfo bean) {

        Intent intent = new Intent(context, LoginActivity.class);
        if (bean != null) {
            intent.putExtra(KEY_BEAN, bean);
        }
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setPresenter(new LoginPersenter());

        if (Logger.sIsDebug) {
            et_username.setText(MyTestData.username);
            et_password.setText(MyTestData.password);
        }


    }

    private void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            Serializable serializable = intent.getSerializableExtra(KEY_BEAN);
            if (serializable == null) {
                return;
            }
            if (serializable instanceof UserInfo) {
                userInfo = (UserInfo) serializable;
                if (userInfo == null) {
                    return;
                }
            } else {
                return;

            }
        }
    }

    @OnClick(R.id.ac_login_bt_login)
    public void toLogin() {
        String username = et_username.getText().toString();
        String password = et_password.getText().toString();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            showMsg("用户名或密码不能为空");
            return;
        }
        presenter.login(new UserInfo(username, password));
    }

    @OnClick(R.id.ac_login_bt_register)
    public void toRegister() {
        Repository.getInstance().getUserService()
                .test("123")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Logger.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(TAG, "onError:" + e);
                        Logger.printStackTrace(e);
                    }

                    @Override
                    public void onNext(String s) {
                        Logger.d(TAG, "s:" + s);
                        et_username.setText("s:" + s);
                    }
                });
        RegisterActivity.startMe(this);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
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
        MainActivity.startMe(this);
        this.finish();
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
