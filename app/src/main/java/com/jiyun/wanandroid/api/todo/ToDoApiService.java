package com.jiyun.wanandroid.api.todo;

import com.jiyun.wanandroid.entity.todo.AddToDoBean;
import com.jiyun.wanandroid.entity.todo.ToDoDeleteBean;
import com.jiyun.wanandroid.entity.todo.ToDoListBean;
import com.jiyun.wanandroid.entity.todo.ToDoUpdataBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by $sl on 2019/5/23 10:26.
 */
public interface ToDoApiService {
    String baseUrl = "https://www.wanandroid.com/";

    //todo新增页面
    @POST("lg/todo/add/json")
    @FormUrlEncoded
    Observable<AddToDoBean> getAddToDo(@Field("title") String todoName, @Field("content") String
            todoDes, @Field("date") String todoDate, @Header("Cookie") String userName, @Header
            ("Cookie") String psw);

    //todo待办列表
    @GET("lg/todo/v2/list/1/json")
    Observable<ToDoListBean> getToDoList(@Query("status") int status,@Header("Cookie") String userName, @Header("Cookie")
            String psw);

    //todo删除列表
    @POST("lg/todo/delete/{id}/json")
    Observable<ToDoDeleteBean> deleteList(@Path("id") int id, @Header("Cookie") String userName,
                                          @Header("Cookie") String psw);

    //todo更新
    @POST("lg/todo/update/{id}/json")
    @FormUrlEncoded
    Observable<ToDoUpdataBean> updataList(@Path("id") int id, @Field("title") String todoName,
                                          @Field("content") String todoDes, @Field("date") String
                                                  todoDate, @Header("Cookie") String userName,
                                          @Header("Cookie") String psw);

    //todo完成
    @POST("lg/todo/done/{id}/json")
    @FormUrlEncoded
    Observable<ToDoUpdataBean> finishList(@Path("id") int id,
                                          @Field("status") int status,
                                          @Header("Cookie") String userName,
                                          @Header("Cookie") String psw);

}
