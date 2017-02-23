package com.zlw.lifequan.net.user;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by zlw on 2017/2/23.
 */
public interface IUser {


    @FormUrlEncoded
    @POST("/login")
    Observable<String> login(@Field("jsondata") String userinfo);



    @FormUrlEncoded
    @POST("/9-9")
    Observable<String> test(@Field("jsondata") String userinfo);
}
