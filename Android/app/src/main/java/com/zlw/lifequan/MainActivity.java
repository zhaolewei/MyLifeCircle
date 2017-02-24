package com.zlw.lifequan;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.zlw.lifequan.test.TestFragment;
import com.zlw.lifequan.ui.vcircle.VCircleFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.main_vp)
    ViewPager mViewPager;
    @BindView(R.id.main_tabLayout)
    TabLayout mTabLayout;

    // TAB控件集
    private List<ImageView> ivs; // Tab中的Imageview集（用于修改透明度达到变色效果）
    private List<TextView> tvs; // Tab中的TextView集
    private int[] tab_icon_back = new int[]{R.drawable.img_back, R.drawable.img_back, R.drawable.img_back};
    private int[] tab_icon = new int[]{R.drawable.img, R.drawable.img, R.drawable.img};
    String[] tabData = new String[]{"V圈", "发现", "我"};


    public static void startMe(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));

    }

    protected void initTab() {
        List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new VCircleFragment());
        fragments.add(new TestFragment());
        fragments.add(new TestFragment());

        mViewPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager(), fragments));
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                ImageView imgView = ivs.get(position);
                int offset = (int) (positionOffset * 255);

                // 左右两图渐变处理 255：白 0：红
                if (position < ivs.size() - 1) {
                    ImageView imgView2 = ivs.get(position + 1);
                    imgView.setImageAlpha(255 - offset);
                    imgView2.setImageAlpha(offset);
                }
            }

            @Override
            public void onPageSelected(int position) {
                selectTabImage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mTabLayout.setupWithViewPager(mViewPager);
        // 设置TabLayout中的Tab样式
        ivs = new ArrayList<ImageView>();
        tvs = new ArrayList<TextView>();
        for (int i = 0; i < fragments.size(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            tab.setCustomView(R.layout.item_main_tab); // 设置Tab item

            // Tab的Text
            TextView tv = (TextView) tab.getCustomView().findViewById(R.id.tab_text);
            tv.setText(tabData[i]);
            tv.setTextSize(10f);
            tv.setTextColor(Color.parseColor("#00A9ED"));
            tvs.add(tv);

            // 提取ImageView
            ImageView img = (ImageView) tab.getCustomView().findViewById(R.id.tab_icon);
            ImageView img_back = (ImageView) tab.getCustomView().findViewById(R.id.tab_icon_back);
            img.setImageResource(tab_icon[i]);
            img_back.setImageResource(tab_icon_back[i]);
            ivs.add(img);
            if (i != 0) {
                img.setImageAlpha(0); // 设置为白色
                tv.setTextColor(Color.parseColor("#616E74"));
            }
        }
        // tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);//适合很多tab
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);// tab均分,适合少的tab


    }

    /**
     * 选中
     *
     * @param position
     */
    private void selectTabImage(int position) {
        ImageView img = null;
        TextView tv = null;
        for (int i = 0; i < ivs.size(); i++) {
            img = ivs.get(i);
            tv = tvs.get(i);
            if (i == position) {
                img.setImageAlpha(255);
                tv.setTextColor(Color.parseColor("#00A9ED"));
            } else {
                img.setImageAlpha(0);
                tv.setTextColor(Color.parseColor("#616E74"));
            }
        }
    }

    /**
     * 主页ViewPager的适配器
     */
    public class MainViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments;

        public MainViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initTab();
    }
}
