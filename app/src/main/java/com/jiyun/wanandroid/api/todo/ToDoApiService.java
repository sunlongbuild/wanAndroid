package com.jiyun.wanandroid.api.todo;

import com.jiyun.wanandroid.entity.todo.AddToDoBean;

import io.reactivex.Observable;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by $sl on 2019/5/23 10:26.
 */
public interface ToDoApiService {
    String baseUrl = "https://www.wanandroid.com/";

    @POST("lg/todo/add/json")
    @Headers("Cookie:loginUserName=1663527894")
    Observable<AddToDoBean> getAddToDo(String todoName, String todoDes, String todoDate);
}
