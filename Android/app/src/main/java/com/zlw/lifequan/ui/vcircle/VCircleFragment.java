package com.zlw.lifequan.ui.vcircle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.common.collect.Lists;
import com.zlw.lifequan.R;
import com.zlw.lifequan.bean.VCircleBean;
import com.zlw.lifequan.test.MyTestData;
import com.zlw.lifequan.ui.vcircle.details.VCircleDetailsActivity;
import com.zlw.lifequan.utils.CollectionUtils;
import com.zlw.lifequan.utils.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;

/**
 * Created by zlw on 2017/2/5.
 */

public class VCircleFragment extends Fragment implements VCircleContract.View {
    private static final String TAG = VCircleFragment.class.getSimpleName();
    private static final int PAGE_SIZE = 6;
    @BindView(R.id.vcircle_recyclerview)
    RecyclerView vcircleRecyclerview;
    @BindView(R.id.vcircle_refreshlayout)
    BGARefreshLayout refreshLayout;

    private MyVcircleAdapter adapter;
    VCircleContract.Presenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vcircle, container, false);
        ButterKnife.bind(this, view);
        initRV();
        initRefreshLayout();
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setPresenter(new VCirclePresenter());
        presenter.onRefreshData(PAGE_SIZE);
    }

    private void initRV() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        vcircleRecyclerview.setLayoutManager(linearLayoutManager);
        adapter = new MyVcircleAdapter();
        vcircleRecyclerview.setAdapter(adapter);
        adapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                VCircleBean vCircleBean = adapter.getData().get(i);
                VCircleDetailsActivity.startMe(getActivity(), vCircleBean);
            }
        });
        adapter.openLoadMore(PAGE_SIZE, true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (presenter != null) {
                    List<VCircleBean> data = adapter.getData();
                    if (!CollectionUtils.isEmpty(data)) {
                        int offset = data.size();
                        Logger.i(TAG, "onLoadMoreRequested offset:" + offset);
                        presenter.onLoadMore(offset, PAGE_SIZE);
                    }
                }
            }
        });


    }

    private void initRefreshLayout() {
        refreshLayout.setDelegate(new BGARefreshLayout.BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
                refreshLayout.endRefreshing();
                adapter.setNewData(MyTestData.getVCircleList());
            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
                return false;
            }
        });
        refreshLayout.setIsShowLoadingMoreView(false);
        BGARefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(getActivity(), false);
        refreshLayout.setRefreshViewHolder(refreshViewHolder);

    }

    @Override
    public void setRefreshData(List<VCircleBean> list) {
        adapter.setNewData(list);
    }

    @Override
    public void setLoadMoreData(List<VCircleBean> list) {
        if (list == null) {
            return;
        }
        adapter.notifyDataChangedAfterLoadMore(list, true);
    }

    @Override
    public void setPresenter(VCircleContract.Presenter presenter) {
        this.presenter = presenter;
        if (presenter != null) {
            presenter.addView(this);
        }
    }

    class MyVcircleAdapter extends BaseQuickAdapter<VCircleBean> {

        public MyVcircleAdapter() {
            super(R.layout.item_vcircle_rv, Lists.<VCircleBean>newArrayList());
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, VCircleBean vCircleBean) {
            if (vCircleBean == null) {
                Logger.e(TAG, "item data is null !!");
                return;
            }
            if (vCircleBean.getUserInfo() == null) {
                Logger.e(TAG, "UserInfo is null !!");
                return;
            }
            baseViewHolder.setText(R.id.item_user_name, vCircleBean.getUserInfo().getUsername())
                    .setText(R.id.item_content, vCircleBean.getContent());

            //头像
            Glide.with(mContext)
                    .load(vCircleBean.getUserInfo().getHeadphoto())
                    .placeholder(R.drawable.test_userphoto)
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into((ImageView) baseViewHolder.getView(R.id.item_user_photo));
            //内容图片
            int length = vCircleBean.getRaws().length;
            if (length == 1) {
                baseViewHolder.setVisible(R.id.item_img_main, true)
                        .setVisible(R.id.item_img1, false)
                        .setVisible(R.id.item_img2, false)
                        .setVisible(R.id.item_img3, false);
                Glide.with(mContext)
                        .load(vCircleBean.getRaws()[0])
                        .placeholder(R.drawable.test_userphoto)
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into((ImageView) baseViewHolder.getView(R.id.item_img_main));
            } else if (length == 2) {
                baseViewHolder.setVisible(R.id.item_img_main, false)
                        .setVisible(R.id.item_img1, true)
                        .setVisible(R.id.item_img2, true)
                        .setVisible(R.id.item_img3, false);
                Glide.with(mContext)
                        .load(vCircleBean.getRaws()[0])
                        .placeholder(R.drawable.test_userphoto)
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into((ImageView) baseViewHolder.getView(R.id.item_img1));
                Glide.with(mContext)
                        .load(vCircleBean.getRaws()[1])
                        .placeholder(R.drawable.test_userphoto)
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into((ImageView) baseViewHolder.getView(R.id.item_img2));
            } else if (length > 2) {
                baseViewHolder.setVisible(R.id.item_img_main, false)
                        .setVisible(R.id.item_img1, true)
                        .setVisible(R.id.item_img2, true)
                        .setVisible(R.id.item_img3, true);

                Glide.with(mContext)
                        .load(vCircleBean.getRaws()[0])
                        .placeholder(R.drawable.test_userphoto)
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into((ImageView) baseViewHolder.getView(R.id.item_img1));
                Glide.with(mContext)
                        .load(vCircleBean.getRaws()[1])
                        .placeholder(R.drawable.test_userphoto)
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into((ImageView) baseViewHolder.getView(R.id.item_img2));
                Glide.with(mContext)
                        .load(vCircleBean.getRaws()[2])
                        .placeholder(R.drawable.test_userphoto)
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into((ImageView) baseViewHolder.getView(R.id.item_img3));
            } else {//0
                baseViewHolder.setVisible(R.id.item_img_main, false)
                        .setVisible(R.id.item_img1, false)
                        .setVisible(R.id.item_img2, false)
                        .setVisible(R.id.item_img3, false);
            }


        }
    }
}
