package com.zlw.lifequan.net;

import com.zlw.lifequan.net.user.UserService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * Created by zlw on 2017/1/3.
 */

public class Repository {
    public static final String TAG = Repository.class.getSimpleName();
    public static final int TIMEOUT = 7; //s
    public static final String HOST = "https://stage.getvinci.com";
    public static final String HOST_Test = "http://route.showapi.com";

    private static volatile Repository sInst = null;

    private Retrofit retrofit;
    private UserService userService;

    public static Repository getInstance() {
        Repository inst = sInst;
        if (inst == null) {
            synchronized (Repository.class) {
                inst = sInst;
                if (inst == null) {
                    inst = new Repository();
                    sInst = inst;
                }
            }
        }
        return inst;
    }

    private Repository() {
        retrofit = new Retrofit.Builder()
                .baseUrl(HOST_Test)
                .client(OkHttpManager.getSingleton().getOkHttpClient())
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        userService = new UserService(retrofit);
    }

    public UserService getUserService() {
        return userService;
    }
}
