package com.jiyun.wanandroid.api.project;

import com.jiyun.wanandroid.entity.collect.CollectBean;
import com.jiyun.wanandroid.entity.project.ListDataBean;
import com.jiyun.wanandroid.entity.project.TabBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProjectApi {
    String PROJECT_URL = "https://www.wanandroid.com/";
    @GET("project/tree/json")
    Observable<TabBean> getTabData();

    @GET("project/list/{page}/json?")
    Observable<ListDataBean> getData(@Path("page") int page,@Query("cid") int cid);

    @POST("lg/collect/{id}/json")
    Observable<CollectBean> collect(@Header("Cookie") String username,
                                    @Header("Cookie") String password, @Path("id") int id);

    @POST("lg/uncollect_originId/{originId}/json")
    @FormUrlEncoded
    Observable<CollectBean> uncollect(@Header("Cookie") String username,@Header("Cookie") String password,@Field("originId") int originId);
}
