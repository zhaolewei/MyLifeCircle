package com.zlw.lifequan.base;

import android.app.Application;
import android.content.Context;

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
    }

}
