package com.jiyun.wanandroid.api.collect;

import com.jiyun.wanandroid.entity.collect.CollectListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface CollectApi {
    String URL="https://www.wanandroid.com/";
    @GET("lg/collect/list/{page}/json")
    Observable<CollectListBean> collectList(@Path("page") int page, @Header("Cookie") String username, @Header("Cookie") String password);
}
