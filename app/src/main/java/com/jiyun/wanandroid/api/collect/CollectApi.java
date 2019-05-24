package com.jiyun.wanandroid.api.collect;

import com.jiyun.wanandroid.entity.collect.CollectBean;
import com.jiyun.wanandroid.entity.collect.CollectListBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface CollectApi {
    String URL="https://www.wanandroid.com/";
    @GET("lg/collect/list/0/json")
    Observable<CollectListBean> collectList( @Header("Cookie") String username, @Header("Cookie") String password);

    @POST("lg/uncollect_originId/{originId}/json")
    @FormUrlEncoded
    Observable<CollectBean> uncollect(@Header("Cookie") String username, @Header("Cookie") String password, @Field("originId") int originId);

    @POST("lg/uncollect/{id}/json")
    @FormUrlEncoded
    Observable<CollectBean> uncollect_two(@Header("Cookie") String username, @Header("Cookie") String password, @Field("id") int originId);
}
