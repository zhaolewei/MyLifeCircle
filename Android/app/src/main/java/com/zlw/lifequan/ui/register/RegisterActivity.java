package com.zlw.lifequan.ui.register;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zlw.lifequan.R;
import com.zlw.lifequan.bean.UserInfo;
import com.zlw.lifequan.ui.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View {

    private static final String TAG = RegisterActivity.class.getSimpleName();

    @BindView(R.id.ac_register_et_user)
    EditText acRegisterEtUser;
    @BindView(R.id.ac_register_et_password)
    EditText acRegisterEtPassword;
    @BindView(R.id.ac_register_et_password2)
    EditText acRegisterEtPassword2;
    @BindView(R.id.ac_register_bt_register)
    Button acRegisterBtRegister;
    @BindView(R.id.ac_register_bt_login)
    Button acRegisterBtLogin;

    private RegisterContract.Presenter presenter;

    public static void startMe(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        setPresenter(new RegisterPersenter());
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void toLogin(UserInfo userInfo) {
        LoginActivity.startMe(this, userInfo);
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
        this.finish();
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        this.presenter = presenter;
        if (presenter != null) {
            presenter.addView(this);
        }
    }

    @OnClick({R.id.ac_register_bt_register, R.id.ac_register_bt_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ac_register_bt_register:
                break;
            case R.id.ac_register_bt_login:
                toLogin(null);
                break;
        }
    }
}
