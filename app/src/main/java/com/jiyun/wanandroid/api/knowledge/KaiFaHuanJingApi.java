package com.jiyun.wanandroid.api.knowledge;

import com.jiyun.wanandroid.entity.knowledge.KaiFaHuanJingBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface KaiFaHuanJingApi {


    String KaiFaHuanJingUrl="https://www.wanandroid.com/";

    @GET("article/list/{page}/json?")
    Observable<KaiFaHuanJingBean> getkaifahuanjing(@Path("page")int page, @Query("cid")int cid);
}
