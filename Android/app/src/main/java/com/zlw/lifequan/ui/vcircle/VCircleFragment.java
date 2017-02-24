package com.zlw.lifequan.ui.vcircle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.common.collect.Lists;
import com.zlw.lifequan.R;
import com.zlw.lifequan.bean.VCircleBean;
import com.zlw.lifequan.ui.vcircle.details.VCircleDetailsActivity;

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
        initRefreshLayout();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setPresenter(new VCirclePresenter());
        initRV();
        presenter.getRefreshData();
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
    }

    private void initRefreshLayout() {
        refreshLayout.setDelegate(new BGARefreshLayout.BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
                refreshLayout.endRefreshing();
                adapter.getData().add(new VCircleBean());
                adapter.notifyDataSetChanged();
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
        adapter.notifyDataSetChanged();
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

        }
    }
}
