package com.zlw.lifequan.ui.vcircle.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.common.collect.Lists;
import com.zlw.lifequan.R;
import com.zlw.lifequan.bean.VCircleBean;
import com.zlw.lifequan.utils.Logger;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VCircleDetailsActivity extends AppCompatActivity {
    private static final String KEY_BEAN = "KEY_BEAN";
    private static final String TAG = VCircleDetailsActivity.class.getSimpleName();

    @BindView(R.id.ac_vcircle_details_rv)
    RecyclerView mRecycleView;

    private MyVcircleDetailsAdapter adapter;
    private VCircleBean vCircleBean;


    public static void startMe(Context context, VCircleBean bean) {
        if (bean == null) {
            Logger.e(TAG, "bean is null");
            return;
        }
        Intent intent = new Intent(context, VCircleDetailsActivity.class);
        intent.putExtra(KEY_BEAN, bean);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vcircle_details);
        ButterKnife.bind(this);
        initData();
        initRv();
    }

    private void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            Serializable serializable = intent.getSerializableExtra(KEY_BEAN);
            if (serializable instanceof VCircleBean) {
                vCircleBean = (VCircleBean) serializable;
                if (vCircleBean == null) {
                    Logger.e(TAG, "rankingFeed is null,will back");
                    this.finish();
                }
            } else {
                Logger.e(TAG, "data type error!!");
                this.finish();
            }
        }
    }

    private void initRv() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(linearLayoutManager);
        adapter = new MyVcircleDetailsAdapter();
        mRecycleView.setAdapter(adapter);

        View emptyView = LayoutInflater.from(this).inflate(R.layout.view_empty_list, null);
        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        adapter.setEmptyView(emptyView);
        emptyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "开始刷新...", Toast.LENGTH_SHORT).show();
            }
        });

        adapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                VCircleBean vCircleBean = adapter.getData().get(i);
            }
        });


    }

    class MyVcircleDetailsAdapter extends BaseQuickAdapter<VCircleBean> {

        public MyVcircleDetailsAdapter() {
            super(R.layout.item_vcircle_rv, Lists.<VCircleBean>newArrayList());
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, VCircleBean vCircleBean) {

        }
    }

}
