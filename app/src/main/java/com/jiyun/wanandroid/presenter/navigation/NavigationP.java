package com.jiyun.wanandroid.presenter.navigation;

import com.jiyun.wanandroid.base.BasePresenter;
import com.jiyun.wanandroid.entity.navigation.NavigationBean;
import com.jiyun.wanandroid.model.navigation.NavigationM;
import com.jiyun.wanandroid.net.ResultCallBack;
import com.jiyun.wanandroid.view.navigation.NavigationV;

public class NavigationP extends BasePresenter<NavigationV>{

    private NavigationM navigationM;

    @Override
    protected void initModel() {
        navigationM = new NavigationM();
        mModels.add(navigationM);
    }
    public void getNavigation(){
        navigationM.getData(new ResultCallBack<NavigationBean>() {
            @Override
            public void onSuccess(NavigationBean bean) {
                if (bean!=null){
                    if (mBaseView!=null){
                        mBaseView.getData(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
