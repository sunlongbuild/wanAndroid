package com.jiyun.wanandroid.presenter.login;

import com.jiyun.wanandroid.base.BasePresenter;
import com.jiyun.wanandroid.entity.login.LoginBean;
import com.jiyun.wanandroid.entity.login.RegisterBean;
import com.jiyun.wanandroid.model.login.LoginM;
import com.jiyun.wanandroid.model.login.RegisterM;
import com.jiyun.wanandroid.net.ResultCallBack;
import com.jiyun.wanandroid.view.login.LoginV;
import com.jiyun.wanandroid.view.login.RegisterV;

/**
 * Created by Lenovo on 2019/5/21.
 */

public class LoginP extends BasePresenter<LoginV> {

    private LoginM mLoginM;

    @Override
    protected void initModel() {
        mLoginM = new LoginM();
        mModels.add(mLoginM);
    }
    public void getData(String username,String password){
        mLoginM.getData(new ResultCallBack<LoginBean>() {
            @Override
            public void onSuccess(LoginBean bean) {
                if(bean!=null){
                    if(mBaseView!=null){
                        mBaseView.getData(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        },username,password);
    }
}
