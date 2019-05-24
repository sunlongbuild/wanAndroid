package com.jiyun.wanandroid.api.collect;

import com.jiyun.wanandroid.entity.collect.CollectListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface CollectApi {
    String URL="https://www.wanandroid.com/";
    @GET("lg/collect/list/0/json")
    Observable<CollectListBean> collectList( @Header("Cookie") String username, @Header("Cookie") String password);
}
