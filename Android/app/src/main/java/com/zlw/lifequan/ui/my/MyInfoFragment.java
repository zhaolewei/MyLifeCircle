package com.zlw.lifequan.ui.my;

import android.app.DatePickerDialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zlw.lifequan.R;
import com.zlw.lifequan.utils.BitmapUtil;
import com.zlw.lifequan.utils.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.blurry.Blurry;

/**
 * Created by zlw on 2017/3/7.
 */

public class MyInfoFragment extends Fragment {

    private static final String TAG = MyInfoFragment.class.getSimpleName();
    @BindView(R.id.bar_back)
    ImageView barBack;
    @BindView(R.id.myinfo_topbar_background)
    ImageView myinfoTopbarBackground;
    @BindView(R.id.myinfo_rl_user_name)
    RelativeLayout myinfoRlUserName;
    @BindView(R.id.myinfo_rl_user_sex)
    RelativeLayout myinfoRlUserSex;
    @BindView(R.id.myinfo_rl_user_birthday)
    RelativeLayout myinfoRlUserBirthday;
    @BindView(R.id.myinfo_user_headphone)
    CircleImageView myinfoUserHeadphone;
    @BindView(R.id.myinfo_user_title)
    TextView myinfoUserTitle;
    @BindView(R.id.myinfo_tv_user_name)
    TextView myinfoTvUserName;
    @BindView(R.id.myinfo_tv_user_sex)
    TextView myinfoTvUserSex;
    @BindView(R.id.myinfo_tv_user_birthday)
    TextView myinfoTvUserBirthday;

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
                new MaterialDialog.Builder(getActivity())
                        .title("修改信息")
                        .content("请输入新的用户名")
                        .widgetColor(Color.parseColor("#ffa200"))//输入框光标的颜色
                        .positiveColor(Color.parseColor("#ffa200"))
                        .backgroundColor(Color.parseColor("#cfffffff"))//dialog的背景颜色
                        .contentColor(Color.BLACK)//内容字体的颜色
                        //.inputType(InputType.TYPE_CLASS_PHONE)//可以输入的类型-电话号码
                        .input("", null, new MaterialDialog.InputCallback() {

                            @Override
                            public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                                updataUserInfo(myinfoTvUserName, input.toString());
                            }
                        })

                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                if (dialog.getInputEditText().length() <= 10) {
                                    dialog.getActionButton(DialogAction.POSITIVE).setEnabled(false);
                                } else {
                                    dialog.getActionButton(DialogAction.POSITIVE).setEnabled(true);
                                }
                            }
                        })
                        .show();
                break;
            case R.id.myinfo_rl_user_sex:
                String[] sex = new String[]{"男", "女"};
                MaterialDialog.Builder builder = new MaterialDialog.Builder(getActivity());
                builder
                        .title("性别")
                        .items(sex)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                myinfoTvUserSex.setText(text);
                                updataUserInfo(null, null);
                            }
                        })
                        .show();
                break;
            case R.id.myinfo_rl_user_birthday:
                /**
                 * 实例化一个DatePickerDialog的对象
                 * 第二个参数是一个DatePickerDialog.OnDateSetListener匿名内部类，当用户选择好日期点击done会调用里面的onDateSet方法
                 */
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        String date = year + "年" + monthOfYear + "月" + dayOfMonth + "日";
                        myinfoTvUserBirthday.setText(date);
                        updataUserInfo(null, null);

                    }
                }, 1995, 2, 20);
                datePickerDialog.show();
                break;
        }
    }

    private void applyUserInfo() {
        Logger.d(TAG, "applyUserInfo");
        String test_img = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489544349&di=644e6e0e23daacf7e69903a46bffddc9&imgtype=jpg&er=1&src=http%3A%2F%2Fimg01.taopic.com%2F150822%2F234928-150R215404322.jpg";

        Glide.with(this)
                .load(test_img)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.test_userphoto)
                .into(new SimpleTarget<GlideDrawable>() {  //Glide 加载完成回调

                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {

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

    //TODO: 提交用户信息
    private void updataUserInfo(final TextView textView, final String msg) {
        //loading
        final SweetAlertDialog loadingDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
        loadingDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        loadingDialog.setTitleText("Loading");
        loadingDialog.setCancelable(false);
        loadingDialog.show();

        //ok
        final SweetAlertDialog okDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE);
        okDialog.setTitleText("修改成功");
        //.setContentText("You clicked the button!");
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    sleep(700);//模拟网络请求
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            loadingDialog.cancel();
                            okDialog.show();
                            //   textView.setText(msg);
                        }
                    });

                    sleep(1200);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            okDialog.cancel();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


}
