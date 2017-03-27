package com.zlw.lifequan.base;

import android.app.Application;
import android.content.Context;

import com.umeng.socialize.PlatformConfig;

/**
 * Created by zlw on 2017/2/23.
 */

public class MyApp extends Application {
    private static Context instance;

    public static Context getIns() {
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }

}
