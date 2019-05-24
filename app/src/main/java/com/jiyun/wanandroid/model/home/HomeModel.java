package com.jiyun.wanandroid.model.home;

import com.jiyun.wanandroid.api.home.HomeApi;
import com.jiyun.wanandroid.base.BaseModel;
import com.jiyun.wanandroid.base.Constants;
import com.jiyun.wanandroid.entity.collect.CollectBean;
import com.jiyun.wanandroid.entity.home.HomeBannerBean;
import com.jiyun.wanandroid.entity.home.HomeRevBean;
import com.jiyun.wanandroid.entity.home.HomeTopBean;
import com.jiyun.wanandroid.net.BaseObserver;
import com.jiyun.wanandroid.net.HttpUtils;
import com.jiyun.wanandroid.net.ResultCallBack;
import com.jiyun.wanandroid.net.RxUtils;
import com.jiyun.wanandroid.utils.SpUtil;

import io.reactivex.Observable;
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

    public void gettop(final ResultCallBack<HomeTopBean> resultCallBack) {
        HomeApi apiserver = HttpUtils.getInstance().getApiserver(HomeApi.RvUrl, HomeApi.class);
        apiserver.getHomeTop().compose(RxUtils.<HomeTopBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<HomeTopBean>() {
                    @Override
                    public void onNext(HomeTopBean homeTopBean) {

                        if (homeTopBean != null) {
                            resultCallBack.onSuccess(homeTopBean);
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
    public void uncollect(final ResultCallBack<CollectBean> resultCallBack , int id) {
        HomeApi apiserver = HttpUtils.getInstance().getApiserver(HomeApi.RvUrl, HomeApi.class);
        String name = (String) SpUtil.getParam(Constants.NAME, "");
        String psw = (String) SpUtil.getParam(Constants.PSW, "");
        final Observable<CollectBean> uncollect = apiserver.uncollect(id, "loginUserName" +name, "loginUserPassWord" + psw,-1);
        uncollect.compose(RxUtils.<CollectBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<CollectBean>() {
                    @Override
                    public void onNext(CollectBean collectBean) {
                        resultCallBack.onSuccess(collectBean);
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
    public void collect(final ResultCallBack<CollectBean> resultCallBack,int id) {
        HomeApi apiserver = HttpUtils.getInstance().getApiserver(HomeApi.RvUrl, HomeApi.class);
        String name = (String) SpUtil.getParam(Constants.NAME, "");
        String psw = (String) SpUtil.getParam(Constants.PSW, "");
        final Observable<CollectBean> collect = apiserver.collect("loginUserName=" + name, "loginUserPassWord=" + psw,id);
        collect.compose(RxUtils.<CollectBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<CollectBean>() {
                    @Override
                    public void onNext(CollectBean collectBean) {
                        resultCallBack.onSuccess(collectBean);
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
