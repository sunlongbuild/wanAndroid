package com.jiyun.wanandroid.api.project;

import com.jiyun.wanandroid.entity.navigation.NavigationBean;
import com.jiyun.wanandroid.entity.project.ListDataBean;
import com.jiyun.wanandroid.entity.project.TabBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProjectApi {
    String PROJECT_URL = "https://www.wanandroid.com/";
    @GET("project/tree/json")
    Observable<TabBean> getTabData();

    @GET("project/list/{page}/json?")
    Observable<ListDataBean> getData(@Path("page") int page,@Query("cid") int cid);
}
