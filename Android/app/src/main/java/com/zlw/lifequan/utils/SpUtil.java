package com.zlw.lifequan.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.zlw.lifequan.base.MyApp;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by xiaoming on 16/7/23.
 */
public class SpUtil {
    private static final String SP_NAME = "Vinci";
    private static final String KEY_UID = "UID";
    private static final String KEY_IMEI_GEN = "IMEI_GEN";
    private static final String KEY_USE_MOBILE_NET = "USE_MOBILE_NET";

    private volatile SharedPreferences sp;

    private SpUtil(Context context) {
        sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    private static SpUtil ins;

    public static SpUtil getIns() {
        if (ins == null) {
            synchronized (SpUtil.class) {
                if (ins == null) {
                    ins = new SpUtil(MyApp.getIns());
                }
            }
        }
        return ins;
    }

    public boolean setUid(String uid) {
        return sp.edit().putString(KEY_UID, uid).commit();
    }

    public String getUid() {
        return sp.getString(KEY_UID, "");
    }

    public String getIMEI() {
        return sp.getString(KEY_IMEI_GEN, "");
    }

    public boolean saveIMEI(String imei) {
        return sp.edit().putString(KEY_IMEI_GEN, imei).commit();
    }

    public boolean isUseMobileNet() {
        return sp.getBoolean(KEY_USE_MOBILE_NET, false);
    }

    public Observable<Boolean> isUseMobileNetRx() {
        return Observable
                .create(new Observable.OnSubscribe<Boolean>() {
                    @Override
                    public void call(Subscriber<? super Boolean> subscriber) {
                        boolean aBoolean = isUseMobileNet();
                        subscriber.onNext(aBoolean);
                        subscriber.onCompleted();
                    }
                });
    }

    public void setIsUseMobileNet(boolean isUseMobileNet) {
        sp.edit().putBoolean(KEY_USE_MOBILE_NET, isUseMobileNet).apply();
    }

}
