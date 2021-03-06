package com.zlw.lifequan.ui.login;

import com.zlw.lifequan.base.BasePresenter;
import com.zlw.lifequan.base.BaseView;
import com.zlw.lifequan.bean.UserInfo;

/**
 * Created by zlw on 2017/2/22.
 */
public class LoginContract {
    interface View extends BaseView<LoginContract.Presenter> {

        void showLoading();

        void hideLoading();

        void toMain();

        void showMsg(String msg);
    }

    interface Presenter extends BasePresenter {
        void addView(LoginContract.View view);

        void login(UserInfo userInfo);
    }
}
