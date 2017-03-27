package com.zlw.lifequan.ui.vcircle.image;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zlw.lifequan.R;
import com.zlw.lifequan.base.MyApp;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;


public class PhotoViewActivity extends AppCompatActivity {

    @BindView(R.id.iv_photo)
    PhotoView photoView;
    private static final String PAGE_DATA = "PAGE_DATA";

    String bitmapUrl;


    public static void startMe(Context context, String bitmapUrl) {

        Intent intent = new Intent(context, PhotoViewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(PAGE_DATA, bitmapUrl);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoview);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        Object o = bundle.get(PAGE_DATA);
        if (o instanceof String) {
            this.bitmapUrl = (String) o;
        } else {
            Toast.makeText(MyApp.getIns(), "图片出错", Toast.LENGTH_SHORT).show();
            this.finish();
        }
        photoView.setBackgroundColor(Color.BLACK);
        Glide.with(this)
                .load(bitmapUrl)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(photoView);
    }

}
