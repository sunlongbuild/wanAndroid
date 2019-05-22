package com.jiyun.wanandroid.model.home;

import com.jiyun.wanandroid.api.home.HomeApi;
import com.jiyun.wanandroid.base.BaseModel;
import com.jiyun.wanandroid.entity.home.HomeBannerBean;
import com.jiyun.wanandroid.entity.home.HomeRevBean;
import com.jiyun.wanandroid.net.BaseObserver;
import com.jiyun.wanandroid.net.HttpUtils;
import com.jiyun.wanandroid.net.ResultCallBack;
import com.jiyun.wanandroid.net.RxUtils;

import io.reactivex.disposables.Disposable;

public class HomeModel extends BaseModel {
    public void getBanner(final ResultCallBack<HomeBannerBean> resultCallBack) {
        HomeApi apiserver = HttpUtils.getInstance().getApiserver(HomeApi.RvUrl, HomeApi.class);
        apiserver.getBanner().compose(RxUtils.<HomeBannerBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<HomeBannerBean>() {
                    @Override
                    public void onNext(HomeBannerBean homeBannerBean) {

                        if (homeBannerBean != null) {
                            resultCallBack.onSuccess(homeBannerBean);
                        } else {
                            resultCallBack.onFail("错误");
                        }
                    }

                    @Override
                    public void error(String msg) {
                        resultCallBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }
                });
    }

    public void getRv(int page, final ResultCallBack<HomeRevBean> resultCallBack) {
        HomeApi apiserver = HttpUtils.getInstance().getApiserver(HomeApi.RvUrl, HomeApi.class);
        apiserver.getRv(page).compose(RxUtils.<HomeRevBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<HomeRevBean>() {
                    @Override
                    public void onNext(HomeRevBean homeRevBean) {

                        if (homeRevBean != null) {
                            resultCallBack.onSuccess(homeRevBean);
                        } else {
                            resultCallBack.onFail("错误");
                        }
                    }

                    @Override
                    public void error(String msg) {
                        resultCallBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }
                });
    }
}
