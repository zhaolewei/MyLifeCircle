package com.zlw.lifequan.ui.vcircle.details;

import com.zlw.lifequan.base.BasePresenter;
import com.zlw.lifequan.base.BaseView;
import com.zlw.lifequan.bean.VCircleCommentBean;

import java.util.List;

/**
 * Created by zlw on 2017/2/7.
 */

public class VCircleCommentContract {

    interface View extends BaseView<Presenter> {
        void setRefreshData(List<VCircleCommentBean> list);

        void setLoadMoreData(List<VCircleCommentBean> list);

        void showMsg(String msg);

        void commentSuccess();
    }

    interface Presenter extends BasePresenter {
        void addView(View view);

        void toRefreshData(String vcircle_id, int size);

        void toLoadMore(int offset, int size);

        void publishCommit(String commentMsg);

    }
}
