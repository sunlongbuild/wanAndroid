package com.jiyun.wanandroid.model.todo;

import com.jiyun.wanandroid.api.todo.ToDoApiService;
import com.jiyun.wanandroid.base.BaseModel;
import com.jiyun.wanandroid.entity.todo.ToDoListBean;
import com.jiyun.wanandroid.net.BaseObserver;
import com.jiyun.wanandroid.net.HttpUtils;
import com.jiyun.wanandroid.net.ResultCallBack;
import com.jiyun.wanandroid.net.RxUtils;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by $sl on 2019/5/24 10:29.
 */
public class ToDoListModel extends BaseModel {
    public void getTodoList( String userName, String psw, final
    ResultCallBack<ToDoListBean> callBack) {
        ToDoApiService apiserver = HttpUtils.getInstance().getApiserver(ToDoApiService.baseUrl,
                ToDoApiService.class);
        Observable<ToDoListBean> observable = apiserver.getToDoList(userName, psw);
        observable.compose(RxUtils.<ToDoListBean>rxObserableSchedulerHelper()).subscribe(new BaseObserver<ToDoListBean>() {
            @Override
            public void onNext(ToDoListBean toDoListBean) {
                callBack.onSuccess(toDoListBean);
            }

            @Override
            public void error(String msg) {
                callBack.onFail(msg);
            }

            @Override
            protected void subscribe(Disposable d) {
                addDisposable(d);
            }
        });
    }
}
