package com.zlw.lifequan.net;

import android.text.TextUtils;
import android.widget.Toast;

import com.zlw.lifequan.base.MyApp;
import com.zlw.lifequan.utils.JsonUtil;
import com.zlw.lifequan.utils.Logger;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;


/**
 */
public class SpecialStatus {

    private static final String TAG = SpecialStatus.class.getSimpleName();
    private static Subscription subscribeToast;

    public static void filter(String responseString) {
        if (TextUtils.isEmpty(responseString)) {
            Logger.i(TAG, "response is null");
            return;
        }
        ErrorBean errorBean = JsonUtil.parseObject(responseString, ErrorBean.class);
    }

    public static Observable filterError2Rx(Throwable throwable) {
        // do your filter
        if (throwable instanceof TimeoutException) {
            Logger.i(TAG, "TimeoutException()");
        }
        return Observable.just(throwable);
    }

    public static void filterError2Toast(Throwable throwable) {
        if (throwable == null) {
            Logger.e(TAG, "throwable have null!!!");
            return;
        }
        if (subscribeToast != null) {
            if (!subscribeToast.isUnsubscribed()) {
                subscribeToast.unsubscribe();
            }
            subscribeToast = null;
        }
        subscribeToast = Observable.just(throwable)
                .delay(200, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Throwable>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.printStackTrace(e);
                    }

                    @Override
                    public void onNext(Throwable throwable) {
                        if (throwable instanceof TimeoutException) {
                            Toast.makeText(MyApp.getIns(), "网络超时", Toast.LENGTH_SHORT).show();
                        } else if (throwable instanceof ConnectException) {
                            Toast.makeText(MyApp.getIns(), "无法连接到服务器", Toast.LENGTH_SHORT).show();
                        } else if (throwable instanceof UnknownHostException) {
                            Toast.makeText(MyApp.getIns(), "无法连接到服务器", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        Logger.i(TAG, "filterError2Toast:" + throwable.getMessage());

    }


    public static class ErrorBean {

        private int status;
        private String message_to_user;

        public ErrorBean() {
        }

        public ErrorBean(int status, String message_to_user) {
            this.status = status;
            this.message_to_user = message_to_user;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMessage_to_user() {
            return message_to_user;
        }

        public void setMessage_to_user(String message_to_user) {
            this.message_to_user = message_to_user;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("ErrorBean{");
            sb.append("status=").append(status);
            sb.append(", message_to_user='").append(message_to_user).append('\'');
            sb.append('}');
            return sb.toString();
        }

    }
}
