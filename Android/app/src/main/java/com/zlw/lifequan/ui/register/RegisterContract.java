package com.zlw.lifequan.ui.register;

import com.zlw.lifequan.base.BasePresenter;
import com.zlw.lifequan.base.BaseView;
import com.zlw.lifequan.bean.UserInfo;

/**
 * Created by zlw on 2017/2/24.
 */

public class RegisterContract {
    interface View extends BaseView<RegisterContract.Presenter> {

        void showLoading();

        void hideLoading();

        void toLogin(UserInfo userInfo);

        void showMsg(String msg);
    }

    interface Presenter extends BasePresenter {
        void addView(RegisterContract.View view);

        void register(UserInfo userInfo);
    }
}
