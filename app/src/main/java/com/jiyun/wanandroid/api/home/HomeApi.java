package com.jiyun.wanandroid.api.home;

import com.jiyun.wanandroid.entity.collect.CollectBean;
import com.jiyun.wanandroid.entity.home.HomeBannerBean;
import com.jiyun.wanandroid.entity.home.HomeRevBean;
import com.jiyun.wanandroid.entity.home.HomeTopBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface HomeApi {

    //列表 http://www.wanandroid.com/article/list/0/json
    String RvUrl="https://www.wanandroid.com/";
    //https://www.wanandroid.com/article/top/json

    @GET("article/list/{page}/json")
    Observable<HomeRevBean> getRv(@Path("page")int page);

    //首页banner http://www.wanandroid.com/banner/json
    @GET("banner/json")
    Observable<HomeBannerBean> getBanner();

    @GET("article/top/json")
    Observable<HomeTopBean> getHomeTop();

    @POST("lg/collect/add/json")
    @FormUrlEncoded
    Observable<CollectBean> collect(@Header("Cookie") String username,
                                    @Header("Cookie") String password, @Field("title") String title,
                                    @Field("author") String author, @Field("link") String link);

    @POST("lg/uncollect/{id}/json")
    @FormUrlEncoded
    Observable<CollectBean> uncollect(@Path("id") int id , @Header("Cookie") String username,@Header("Cookie") String password,@Field("originId") int originId);
}
