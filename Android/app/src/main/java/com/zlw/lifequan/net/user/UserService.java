package com.zlw.lifequan.net.user;

import com.zlw.lifequan.net.Repository;

import java.util.concurrent.TimeUnit;

import retrofit2.Retrofit;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by xiaoming on 16/8/6.
 */
public class UserService {
    private static final String TAG = UserService.class.getSimpleName();

    public static final int TIMEOUT = Repository.TIMEOUT;
    private final Retrofit retrofit;
    private final IUser iUser;

    public UserService(Retrofit retrofit) {
        this.retrofit = retrofit;
        this.iUser = retrofit.create(IUser.class);
    }

    public Observable<String> login(String userInfoJson) {
        return iUser.login(userInfoJson)
                .timeout(TIMEOUT, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String result) {

                        return Observable.just(result);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation());
    }

    public Observable<String> test(String userInfoJson) {
        return iUser.test(userInfoJson)
                .timeout(TIMEOUT, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String result) {

                        return Observable.just(result);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation());
    }


}