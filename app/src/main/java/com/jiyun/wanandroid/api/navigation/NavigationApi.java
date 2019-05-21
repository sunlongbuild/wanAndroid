package com.jiyun.wanandroid.api.navigation;

import com.jiyun.wanandroid.entity.navigation.NavigationBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface NavigationApi {
    String NAVIGATION_URL = "https://www.wanandroid.com/";
    @GET("navi/json")
    Observable<NavigationBean> getNavigation();
}
