package com.zlw.lifequan.ui.login;

import com.zlw.lifequan.net.user.UserInfo;
import com.zlw.lifequan.utils.Logger;

/**
 * Created by zlw on 2017/2/22.
 */
public class LoginPersenter implements LoginContract.Presenter {

    private static final String TAG = LoginPersenter.class.getSimpleName();
    private LoginContract.View view;

    @Override
    public void addView(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void toLogin(UserInfo userInfo) {

        if (view == null) {
            Logger.e(TAG, "view is null");
            return;
        }
        //TODO:信息校验

        view.toMain();
    }

    @Override
    public void removeView() {
        this.view = null;
    }
}
