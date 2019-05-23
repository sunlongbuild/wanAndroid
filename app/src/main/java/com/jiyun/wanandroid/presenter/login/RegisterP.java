package com.jiyun.wanandroid.presenter.login;

import com.jiyun.wanandroid.base.BasePresenter;
import com.jiyun.wanandroid.entity.login.RegisterBean;
import com.jiyun.wanandroid.model.login.RegisterM;
import com.jiyun.wanandroid.net.ResultCallBack;
import com.jiyun.wanandroid.view.login.RegisterV;

/**
 * Created by Lenovo on 2019/5/21.
 */

public class RegisterP extends BasePresenter<RegisterV> {

    private RegisterM mRegisterM;

    @Override
    protected void initModel() {
        mRegisterM = new RegisterM();
        mModels.add(mRegisterM);
    }
    public void setData(String username,String password,String repassword){
        mRegisterM.setData(new ResultCallBack<RegisterBean>() {
            @Override
            public void onSuccess(RegisterBean bean) {
                if(bean!=null){
                    if(mBaseView!=null){
                        mBaseView.setData(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        },username,password,repassword);
    }
}
