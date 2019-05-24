package com.jiyun.wanandroid.api.search;

import com.jiyun.wanandroid.entity.search.Bean;
import com.jiyun.wanandroid.entity.search.SearchBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Service_Api {
    String baseUrl="https://www.wanandroid.com/";
    @POST("article/query/{num}/json")
    @FormUrlEncoded
    Observable<Bean>getSearch(@FieldMap Map<String, Object> map, @Path("num") int num);

   // https://www.wanandroid.com/
    @GET("/hotkey/json")
    Observable<SearchBean>getData();
}
