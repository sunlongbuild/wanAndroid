package com.jiyun.wanandroid.model.login;

import com.jiyun.wanandroid.api.login.LoginMyServer;
import com.jiyun.wanandroid.base.BaseModel;
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

public class RegisterM extends BaseModel{
    private ResultCallBack<RegisterBean> mData;

    public void setData(final ResultCallBack<RegisterBean> data,String username,String password,String repassword) {
        LoginMyServer apiserver = HttpUtils.getInstance().getApiserver(LoginMyServer.RegisterUrl, LoginMyServer.class);
        Observable<RegisterBean> registerData = apiserver.getRegisterData(username,password,repassword);
        Observable<RegisterBean> compose = registerData.compose(RxUtils.<RegisterBean>rxObserableSchedulerHelper());
        compose.subscribe(new BaseObserver<RegisterBean>() {
            @Override
            public void error(String msg) {

            }

            @Override
            protected void subscribe(Disposable d) {

            }

            @Override
            public void onNext(RegisterBean registerBean) {
                if(registerBean!=null){
                    data.onSuccess(registerBean);
                }
            }
        });
    }
}
