package com.jiyun.wanandroid.api.knowledge;

import com.jiyun.wanandroid.entity.collect.CollectBean;
import com.jiyun.wanandroid.entity.knowledge.KaiFaHuanJingBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface KaiFaHuanJingApi {


    String KaiFaHuanJingUrl="https://www.wanandroid.com/";

    @GET("article/list/{page}/json?")
    Observable<KaiFaHuanJingBean> getkaifahuanjing(@Path("page")int page, @Query("cid")int cid);

    @POST("lg/collect/{id}/json")
    Observable<CollectBean> collect(@Header("Cookie") String username,
                                    @Header("Cookie") String password, @Path("id") int id);

    @POST("lg/uncollect/{id}/json")
    @FormUrlEncoded
    Observable<CollectBean> uncollect(@Path("id") int id , @Header("Cookie") String username,@Header("Cookie") String password,@Field("originId") int originId);
}
