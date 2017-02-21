package com.zlw.lifequan.vcircle;

import com.zlw.lifequan.bean.VCircleBean;
import com.zlw.lifequan.test.MyTestData;
import com.zlw.lifequan.utils.Logger;

import java.util.List;

import rx.Subscription;

/**
 * Created by xiaoming on 16/8/13.
 */
public class VCirclePresenter implements VCircleContract.Presenter {
    private static final String TAG = VCirclePresenter.class.getSimpleName();
    private VCircleContract.View view;
    private Subscription subscription;

    @Override
    public void addView(VCircleContract.View view) {
        this.view = view;
    }

    @Override
    public void getRefreshData() {
//        initSub();
        if (view == null) {
            Logger.e(TAG, "view is null");
        }

        List<VCircleBean> list = MyTestData.getVCircleList();
        view.setRefreshData(list);
    }


    @Override
    public void removeView() {
        this.view = null;
    }

    private void initSub() {
        if (null != subscription) {
            if (!subscription.isUnsubscribed()) {
                subscription.unsubscribe();
            }
            subscription = null;
        }
    }

}
