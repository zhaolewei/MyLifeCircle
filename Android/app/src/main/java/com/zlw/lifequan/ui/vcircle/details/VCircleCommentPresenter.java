package com.zlw.lifequan.ui.vcircle.details;

import com.zlw.lifequan.bean.VCircleCommentBean;
import com.zlw.lifequan.test.MyTestData;
import com.zlw.lifequan.utils.Logger;

import java.util.List;

import rx.Subscription;

/**
 * Created by xiaoming on 16/8/13.
 */
public class VCircleCommentPresenter implements VCircleCommentContract.Presenter {
    private static final String TAG = VCircleCommentPresenter.class.getSimpleName();
    private VCircleCommentContract.View view;
    private Subscription subscription;

    @Override
    public void addView(VCircleCommentContract.View view) {
        this.view = view;
    }

    @Override
    public void toRefreshData(String vcircle_id, int size) {
        if (view == null) {
            Logger.e(TAG, "view is null");
        }

        List<VCircleCommentBean> list = MyTestData.getCommentList();
        view.setRefreshData(list);
    }

    @Override
    public void toLoadMore(int offset, int size) {
//        if (view == null) {
//            Logger.e(TAG, "view is null");
//        }
//        resetSub();
//        subscription = Observable
//                .create(new Observable.OnSubscribe<List<VCircleBean>>() {
//                    @Override
//                    public void call(Subscriber<? super List<VCircleBean>> subscriber) {
//                        List<VCircleBean> list = Lists.newArrayList();
//                        list.add(MyTestData.getVCircle());
//                        list.add(MyTestData.getVCircle());
//                        list.add(MyTestData.getVCircle());
//                        list.add(MyTestData.getVCircle());
//                        list.add(MyTestData.getVCircle());
//                        subscriber.onNext(list);
//                        subscriber.onCompleted();
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<List<VCircleBean>>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Logger.e(e, TAG, e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(List<VCircleBean> list) {
//                        view.setLoadMoreData(list);
//                    }
//                });
    }

    @Override
    public void publishCommit(String commentMsg) {
        if (view == null) {
            Logger.e(TAG, "view is null");
        }

        //TODO:网咯
        Logger.i(TAG, "评论内容:" + commentMsg);
        view.commentSuccess();
    }


    @Override
    public void removeView() {
        this.view = null;
    }

    private void resetSub() {
        if (null != subscription) {
            if (!subscription.isUnsubscribed()) {
                subscription.unsubscribe();
            }
            subscription = null;
        }
    }

}
