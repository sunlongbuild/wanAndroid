package com.jiyun.wanandroid.api.WeChat;

import com.jiyun.wanandroid.entity.collect.CollectBean;
import com.jiyun.wanandroid.entity.wechat.WeChatBean;
import com.jiyun.wanandroid.entity.wechat.WeChatTabBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WeChatApi  {

    String WeChatUrl="https://wanandroid.com/wxarticle/";
//http://wanandroid.com/wxarticle/list/408/0/json
    @GET("chapters/json")
    Observable<WeChatTabBean> getTab();

    @GET("list/{chapterId}/{page}/json")
    Observable<WeChatBean> getData(@Path("chapterId")int chapterId,@Path("page")int page);

    @POST("lg/collect/{id}/json")
    Observable<CollectBean> collect(@Header("Cookie") String username,
                                    @Header("Cookie") String password, @Path("id") int id);

    @POST("lg/uncollect/{id}/json")
    @FormUrlEncoded
    Observable<CollectBean> uncollect(@Path("id") int id , @Header("Cookie") String username,@Header("Cookie") String password,@Field("originId") int originId);
}
