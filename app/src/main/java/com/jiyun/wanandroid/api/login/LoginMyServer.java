package com.jiyun.wanandroid.api.login;

import com.jiyun.wanandroid.entity.login.LoginBean;
import com.jiyun.wanandroid.entity.login.RegisterBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Lenovo on 2019/5/21.
 */

public interface LoginMyServer {
    String RegisterUrl = "https://www.wanandroid.com/";
    @POST("user/register")
    @FormUrlEncoded
    Observable<RegisterBean>getRegisterData(@Field("username") String username,
                                            @Field("password") String password,
                                            @Field("repassword") String repassword);

    String LoginUrl = "https://www.wanandroid.com/";
    @POST("user/login")
    @FormUrlEncoded
    Observable<LoginBean> getLoginData(@Field("username") String username,
                                       @Field("password") String password);


}
