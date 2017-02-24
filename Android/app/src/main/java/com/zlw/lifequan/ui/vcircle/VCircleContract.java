package com.zlw.lifequan.ui.vcircle;

import com.zlw.lifequan.base.BasePresenter;
import com.zlw.lifequan.base.BaseView;
import com.zlw.lifequan.bean.VCircleBean;

import java.util.List;

/**
 * Created by zlw on 2017/2/7.
 */

public class VCircleContract {

    interface View extends BaseView<Presenter> {
        void setRefreshData(List<VCircleBean> list);
    }

    interface Presenter extends BasePresenter {
        void addView(View view);

        void getRefreshData();
    }
}
