package com.jiyun.wanandroid.api.todo;

import com.jiyun.wanandroid.entity.todo.AddToDoBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by $sl on 2019/5/23 10:26.
 */
public interface ToDoApiService {
    String baseUrl = "https://www.wanandroid.com/";

    //todo新增页面
    @POST("lg/todo/add/json")
    @FormUrlEncoded
    Observable<AddToDoBean> getAddToDo(@Field("todoName") String todoName, @Field("todoDes") String todoDes, @Field("todoDate") String todoDate, @Header
            ("Cookie") String userName,@Header("Cookie") String psw);

}
