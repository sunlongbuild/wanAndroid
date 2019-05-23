package com.jiyun.wanandroid.model.todo;

import com.jiyun.wanandroid.api.todo.ToDoApiService;
import com.jiyun.wanandroid.base.BaseModel;
import com.jiyun.wanandroid.entity.todo.AddToDoBean;
import com.jiyun.wanandroid.net.BaseObserver;
import com.jiyun.wanandroid.net.HttpUtils;
import com.jiyun.wanandroid.net.ResultCallBack;
import com.jiyun.wanandroid.net.RxUtils;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import retrofit2.http.Header;

/**
 * Created by $sl on 2019/5/23 13:13.
 */
public class AddToDoModel extends BaseModel {
    public void getAddToDo(String todoName, String todoDes, String todoDate, String userName,
                           String psw,final ResultCallBack<AddToDoBean>callBack) {
        ToDoApiService apiserver = HttpUtils.getInstance().getApiserver(ToDoApiService.baseUrl,
                ToDoApiService.class);
        Observable<AddToDoBean> observable = apiserver.getAddToDo(todoName, todoDes, todoDate,
                userName, psw);
        observable.compose(RxUtils.<AddToDoBean>rxObserableSchedulerHelper()).subscribe(new BaseObserver<AddToDoBean>() {
            @Override
            public void onNext(AddToDoBean addToDoBean) {
                callBack.onSuccess(addToDoBean);
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
