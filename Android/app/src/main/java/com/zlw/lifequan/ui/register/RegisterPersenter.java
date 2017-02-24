package com.zlw.lifequan.ui.register;

import com.zlw.lifequan.bean.UserInfo;
import com.zlw.lifequan.utils.Logger;

/**
 * Created by zlw on 2017/2/24.
 */

public class RegisterPersenter implements RegisterContract.Presenter {

    private static final String TAG = RegisterPersenter.class.getSimpleName();
    private RegisterContract.View view;

    @Override
    public void addView(RegisterContract.View view) {
        this.view = view;
    }

    @Override
    public void register(UserInfo userInfo) {
        if (view == null) {
            Logger.e(TAG, "view is null !!");
            return;
        }
    }

    @Override
    public void removeView() {
        view = null;
    }

}
