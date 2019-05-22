package com.jiyun.wanandroid.model.navigation;

import com.jiyun.wanandroid.api.navigation.NavigationApi;
import com.jiyun.wanandroid.base.BaseModel;
import com.jiyun.wanandroid.entity.navigation.NavigationBean;
import com.jiyun.wanandroid.net.BaseObserver;
import com.jiyun.wanandroid.net.HttpUtils;
import com.jiyun.wanandroid.net.ResultCallBack;
import com.jiyun.wanandroid.net.RxUtils;

import io.reactivex.disposables.Disposable;

public class NavigationM extends BaseModel{

    public void getData(final ResultCallBack<NavigationBean> resultCallBack) {
        NavigationApi navigationApi = HttpUtils.getInstance().getApiserver(NavigationApi.NAVIGATION_URL, NavigationApi.class);
        navigationApi.getNavigation().compose(RxUtils.<NavigationBean>rxObserableSchedulerHelper()).subscribe(
                new BaseObserver<NavigationBean>() {
                    @Override
                    public void onNext(NavigationBean navigationBean) {
                        if (navigationBean!=null){
                            resultCallBack.onSuccess(navigationBean);
                        }
                    }

                    @Override
                    public void error(String msg) {

                    }

                    @Override
                    protected void subscribe(Disposable d) {

                    }
                }
        );
    }
}
