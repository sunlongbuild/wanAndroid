package com.jiyun.wanandroid.model.login;

import com.jiyun.wanandroid.api.login.LoginMyServer;
import com.jiyun.wanandroid.base.BaseModel;
import com.jiyun.wanandroid.entity.login.LoginBean;
import com.jiyun.wanandroid.entity.login.RegisterBean;
import com.jiyun.wanandroid.net.BaseObserver;
import com.jiyun.wanandroid.net.HttpUtils;
import com.jiyun.wanandroid.net.ResultCallBack;
import com.jiyun.wanandroid.net.RxUtils;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Lenovo on 2019/5/21.
 */

public class LoginM extends BaseModel{

    public void getData(final ResultCallBack<LoginBean> resultCallBack, String username, String password) {
        LoginMyServer apiserver = HttpUtils.getInstance().getApiserver(LoginMyServer.LoginUrl, LoginMyServer.class);
        Observable<LoginBean> loginData = apiserver.getLoginData(username, password);
        Observable<LoginBean> compose = loginData.compose(RxUtils.<LoginBean>rxObserableSchedulerHelper());
        compose.subscribe(new BaseObserver<LoginBean>() {
            @Override
            public void error(String msg) {

            }

            @Override
            protected void subscribe(Disposable d) {

            }

            @Override
            public void onNext(LoginBean loginBean) {
                if(loginBean!=null){
                    resultCallBack.onSuccess(loginBean);
                }
            }
        });
    }
}
