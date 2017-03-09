package com.zlw.lifequan.ui.vcircle;

import com.google.common.collect.Lists;
import com.zlw.lifequan.bean.VCircleBean;
import com.zlw.lifequan.test.MyTestData;
import com.zlw.lifequan.utils.Logger;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
    public void onRefreshData(int size) {
        if (view == null) {
            Logger.e(TAG, "view is null");
        }

        List<VCircleBean> list = MyTestData.getVCircleList();
        view.setRefreshData(list);
    }

    @Override
    public void onLoadMore(int offset, int size) {
        if (view == null) {
            Logger.e(TAG, "view is null");
        }
        initSub();
        subscription = Observable
                .create(new Observable.OnSubscribe<List<VCircleBean>>() {
                    @Override
                    public void call(Subscriber<? super List<VCircleBean>> subscriber) {
                        List<VCircleBean> list = Lists.newArrayList();
                        list.add(MyTestData.getVCircle());
                        list.add(MyTestData.getVCircle());
                        list.add(MyTestData.getVCircle());
                        list.add(MyTestData.getVCircle());
                        list.add(MyTestData.getVCircle());
                        subscriber.onNext(list);
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<VCircleBean>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e, TAG, e.getMessage());
                    }

                    @Override
                    public void onNext(List<VCircleBean> list) {
                        view.setLoadMoreData(list);
                    }
                });

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
