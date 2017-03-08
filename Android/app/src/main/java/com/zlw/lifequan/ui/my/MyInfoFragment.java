package com.zlw.lifequan.ui.my;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zlw.lifequan.R;
import com.zlw.lifequan.utils.BitmapUtil;
import com.zlw.lifequan.utils.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.blurry.Blurry;

/**
 * Created by zlw on 2017/3/7.
 */

public class MyInfoFragment extends Fragment {

    private static final String TAG = MyInfoFragment.class.getSimpleName();
    @BindView(R.id.bar_back)
    ImageView barBack;
    @BindView(R.id.myinfo_user_headphone)
    CircleImageView myinfoUserHeadphone;
    @BindView(R.id.myinfo_user_title)
    TextView myinfoUserTitle;
    @BindView(R.id.myinfo_topbar_background)
    ImageView myinfoTopbarBackground;
    @BindView(R.id.myinfo_tv_user_name)
    TextView myinfoTvUserName;
    @BindView(R.id.myinfo_rl_user_name)
    RelativeLayout myinfoRlUserName;
    @BindView(R.id.myinfo_tv_user_sex)
    TextView myinfoTvUserSex;
    @BindView(R.id.myinfo_rl_user_sex)
    RelativeLayout myinfoRlUserSex;
    @BindView(R.id.myinfo_tv_user_birthday)
    TextView myinfoTvUserBirthday;
    @BindView(R.id.myinfo_rl_user_birthday)
    RelativeLayout myinfoRlUserBirthday;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myinfo, container, false);
        ButterKnife.bind(this, view);

        applyUserInfo();

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.bar_back, R.id.myinfo_user_headphone, R.id.myinfo_rl_user_name, R.id.myinfo_rl_user_sex, R.id.myinfo_rl_user_birthday})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bar_back:
                break;
            case R.id.myinfo_user_headphone:
                break;
            case R.id.myinfo_rl_user_name:
                break;
            case R.id.myinfo_rl_user_sex:
                break;
            case R.id.myinfo_rl_user_birthday:
                break;
        }
    }

    private void applyUserInfo() {
        String test_img = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489544349&di=644e6e0e23daacf7e69903a46bffddc9&imgtype=jpg&er=1&src=http%3A%2F%2Fimg01.taopic.com%2F150822%2F234928-150R215404322.jpg";

        Logger.d(TAG, "applyUserInfo");
        Glide.with(this)
                .load(test_img)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.test_userphoto)
                .into(new SimpleTarget<GlideDrawable>() {  //Glide 加载完成回调

                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        Logger.d(TAG, "onResourceReady");

                        if (resource == null) {
                            return;
                        }

                        myinfoUserHeadphone.setImageDrawable(resource.getCurrent());
                        Bitmap bitmap = Bitmap.createScaledBitmap(BitmapUtil.drawable2Bitmap(resource.getCurrent()), 90, 160, false);//有损缩放

                        Blurry.with(getActivity())
                                .radius(36)
                                .sampling(1)
                                .color(Color.parseColor("#66FFAA25"))
                                .async()
                                .from(bitmap)
                                .into(myinfoTopbarBackground);
                    }
                });


    }
}
