package com.zlw.lifequan.ui.vcircle.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.common.collect.Lists;
import com.zlw.lifequan.R;
import com.zlw.lifequan.bean.VCircleBean;
import com.zlw.lifequan.bean.VCircleCommentBean;
import com.zlw.lifequan.ui.vcircle.image.PhotoViewActivity;
import com.zlw.lifequan.utils.Logger;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class VCircleCommentActivity extends AppCompatActivity implements VCircleCommentContract.View {
    private static final String KEY_BEAN = "KEY_BEAN";
    private static final String TAG = VCircleCommentActivity.class.getSimpleName();
    private static final int COMMENT_MAX_LENGTH = 120;

    @BindView(R.id.ac_vcircle_details_rv)
    RecyclerView mRecycleView;
    @BindView(R.id.vcircle_dynamic_info_et_comment_content)
    EditText vcircleDynamicInfoEtCommentContent;

    private VCircleCommentContract.Presenter presenter;

    private HeadViewHolder headViewHolder;
    private MyVcircleCommentAdapter adapter;
    public VCircleBean vCircleBean;


    public static void startMe(Context context, VCircleBean bean) {
        if (bean == null) {
            Logger.e(TAG, "bean is null");
            return;
        }
        Intent intent = new Intent(context, VCircleCommentActivity.class);
        intent.putExtra(KEY_BEAN, bean);
        context.startActivity(intent);
        Logger.d(TAG, "----------V圈详情界面-----------");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vcircle_details);
        ButterKnife.bind(this);
        this.setPresenter(new VCircleCommentPresenter());
        initData();
        initRv();
        vcircleDynamicInfoEtCommentContent.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                    onClickCommit();
                }
                return false;
            }
        });
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
        adapter = new MyVcircleCommentAdapter();
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

        View headerView = getLayoutInflater().inflate(R.layout.item_vcircle_rv_header, null);
        headViewHolder = new HeadViewHolder(headerView, this);
        headerView.setLayoutParams(new DrawerLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        adapter.addHeaderView(headerView);


        adapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
            }
        });

        presenter.toRefreshData(vCircleBean.getVcircle_id() + "", 10);

    }

    @Override
    public void setRefreshData(List<VCircleCommentBean> list) {
        if (list == null || list.size() == 0) {
            Logger.w(TAG, "list is null");
            return;
        }
        adapter.setNewData(list);
    }

    @Override
    public void setLoadMoreData(List<VCircleCommentBean> list) {

    }

    @Override
    public void setPresenter(VCircleCommentContract.Presenter presenter) {
        this.presenter = presenter;
        if (presenter != null) {
            presenter.addView(this);
        }
    }


    /**
     * 提交评论信息
     */
    public void onClickCommit() {
        Logger.i(TAG, "onClickCommit()");
        String content = vcircleDynamicInfoEtCommentContent.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            showMsg("请输入评论内容");
            return;
        }
        if (content.length() > COMMENT_MAX_LENGTH) {
            showMsg("最多输入" + COMMENT_MAX_LENGTH + "个字");
            return;
        }

        presenter.publishCommit(content);
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void commentSuccess() {
        vcircleDynamicInfoEtCommentContent.setText("");
        showMsg("评论成功");
    }

    class MyVcircleCommentAdapter extends BaseQuickAdapter<VCircleCommentBean> {

        public MyVcircleCommentAdapter() {
            super(R.layout.item_vcircle_comment, Lists.<VCircleCommentBean>newArrayList());
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, VCircleCommentBean item) {
            baseViewHolder
                    .setText(R.id.item_vcircle_comment_user_name, item.getUserInfo().getUsername())
                    .setText(R.id.item_vcircle_comment_content, item.getContent());

            String imageUrl = item.getUserInfo().getHeadphoto();
            if (imageUrl != null) {
                //头像
                Glide.with(VCircleCommentActivity.this)
                        .load(vCircleBean.getUserInfo().getHeadphoto())
                        .placeholder(R.drawable.test_userphoto)
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into((CircleImageView) baseViewHolder.getView(R.id.item_vcircle_comment_user_photo));
            }

        }
    }


    class HeadViewHolder {
        private AppCompatActivity activity;
        @BindView(R.id.item_user_photo)
        CircleImageView itemUserPhoto;
        @BindView(R.id.item_user_name)
        TextView itemUserName;
        @BindView(R.id.item_content)
        TextView itemContent;
        @BindView(R.id.item_img_main)
        ImageView itemImgMain;
        @BindView(R.id.item_img1)
        ImageView itemImg1;
        @BindView(R.id.item_img2)
        ImageView itemImg2;
        @BindView(R.id.item_img3)
        ImageView itemImg3;

        HeadViewHolder(View view, AppCompatActivity activity) {
            this.activity = activity;
            ButterKnife.bind(this, view);
            initData();

        }

        private void initData() {
            if (vCircleBean == null) {
                Logger.e(TAG, "item data is null !!");
                return;
            }
            if (vCircleBean.getUserInfo() == null) {
                Logger.e(TAG, "UserInfo is null !!");
                return;
            }
            itemUserName.setText(vCircleBean.getUserInfo().getUsername());
            itemContent.setText(vCircleBean.getContent());

            //头像
            Glide.with(activity)
                    .load(vCircleBean.getUserInfo().getHeadphoto())
                    .placeholder(R.drawable.test_userphoto)
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(itemUserPhoto);
            //内容图片
            int length = vCircleBean.getRaws().length;
            if (length == 1) {
                itemImgMain.setVisibility(View.VISIBLE);
                itemImg1.setVisibility(View.GONE);
                itemImg2.setVisibility(View.GONE);
                itemImg3.setVisibility(View.GONE);

                itemImgMain.setEnabled(true);
                itemImg1.setEnabled(false);
                itemImg2.setEnabled(false);
                itemImg3.setEnabled(false);

                Glide.with(activity)
                        .load(vCircleBean.getRaws()[0])
                        .placeholder(R.drawable.test_userphoto)
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(itemImgMain);

            } else if (length == 2) {
                itemImgMain.setVisibility(View.GONE);
                itemImg1.setVisibility(View.VISIBLE);
                itemImg2.setVisibility(View.VISIBLE);
                itemImg3.setVisibility(View.GONE);
                itemImgMain.setEnabled(false);
                itemImg1.setEnabled(true);
                itemImg2.setEnabled(true);
                itemImg3.setEnabled(false);
                Glide.with(activity)
                        .load(vCircleBean.getRaws()[0])
                        .placeholder(R.drawable.test_userphoto)
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(itemImg1);
                Glide.with(activity)
                        .load(vCircleBean.getRaws()[1])
                        .placeholder(R.drawable.test_userphoto)
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(itemImg2);
            } else if (length > 2) {
                itemImgMain.setVisibility(View.GONE);
                itemImg1.setVisibility(View.VISIBLE);
                itemImg2.setVisibility(View.VISIBLE);
                itemImg3.setVisibility(View.VISIBLE);
                itemImgMain.setEnabled(false);
                itemImg1.setEnabled(true);
                itemImg2.setEnabled(true);
                itemImg3.setEnabled(true);
                Glide.with(activity)
                        .load(vCircleBean.getRaws()[0])
                        .placeholder(R.drawable.test_userphoto)
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(itemImg1);
                Glide.with(activity)
                        .load(vCircleBean.getRaws()[1])
                        .placeholder(R.drawable.test_userphoto)
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(itemImg2);
                Glide.with(activity)
                        .load(vCircleBean.getRaws()[2])
                        .placeholder(R.drawable.test_userphoto)
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(itemImg3);
            } else {//0
                itemImgMain.setVisibility(View.GONE);
                itemImg1.setVisibility(View.GONE);
                itemImg2.setVisibility(View.GONE);
                itemImg3.setVisibility(View.GONE);
                itemImgMain.setEnabled(false);
                itemImg1.setEnabled(false);
                itemImg2.setEnabled(false);
                itemImg3.setEnabled(false);
            }
        }

        @OnClick({R.id.item_user_photo, R.id.item_user_name, R.id.item_img_main, R.id.item_img1, R.id.item_img2, R.id.item_img3})
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.item_user_photo:
                    break;
                case R.id.item_user_name:
                    break;
                case R.id.item_img_main:
                    PhotoViewActivity.startMe(activity, vCircleBean.getRaws()[0]);
                    break;
                case R.id.item_img1:
                    PhotoViewActivity.startMe(activity, vCircleBean.getRaws()[0]);
                    break;
                case R.id.item_img2:
                    PhotoViewActivity.startMe(activity, vCircleBean.getRaws()[1]);
                    break;
                case R.id.item_img3:
                    PhotoViewActivity.startMe(activity, vCircleBean.getRaws()[2]);
                    break;
            }
        }
    }


}