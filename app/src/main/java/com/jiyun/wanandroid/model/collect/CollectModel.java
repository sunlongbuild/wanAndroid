package com.jiyun.wanandroid.model.collect;

import com.jiyun.wanandroid.api.collect.CollectApi;
import com.jiyun.wanandroid.base.BaseModel;
import com.jiyun.wanandroid.base.Constants;
import com.jiyun.wanandroid.entity.collect.CollectBean;
import com.jiyun.wanandroid.entity.collect.CollectListBean;
import com.jiyun.wanandroid.net.BaseObserver;
import com.jiyun.wanandroid.net.HttpUtils;
import com.jiyun.wanandroid.net.ResultCallBack;
import com.jiyun.wanandroid.net.RxUtils;
import com.jiyun.wanandroid.utils.SpUtil;
import com.just.agentweb.LogUtils;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class CollectModel extends BaseModel {
    public void collectList(final ResultCallBack<CollectListBean> resultCallBack){
        CollectApi apiserver = HttpUtils.getInstance().getApiserver(CollectApi.URL, CollectApi.class);
        String name = (String) SpUtil.getParam(Constants.NAME, "");
        String psw = (String) SpUtil.getParam(Constants.PSW, "");
        LogUtils.e("NP","name"+name+"psw:"+psw);
        Observable<CollectListBean> collectList = apiserver.collectList("loginUserName="+name ,"loginUserPassword="+psw);
        collectList.compose(RxUtils.<CollectListBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<CollectListBean>() {
                    @Override
                    public void onNext(CollectListBean collectListBean) {
                        resultCallBack.onSuccess(collectListBean);
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
        CollectApi apiserver = HttpUtils.getInstance().getApiserver(CollectApi.URL, CollectApi.class);
        String name = (String) SpUtil.getParam(Constants.NAME, "");
        String psw = (String) SpUtil.getParam(Constants.PSW, "");
        final Observable<CollectBean> uncollect = apiserver.uncollect( "loginUserName" + name, "loginPassWord" + psw,id);
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
    public void unCollectTwo(final ResultCallBack<CollectBean> resultCallBack , int id) {
        CollectApi apiserver = HttpUtils.getInstance().getApiserver(CollectApi.URL, CollectApi.class);
        String name = (String) SpUtil.getParam(Constants.NAME, "");
        String psw = (String) SpUtil.getParam(Constants.PSW, "");
        final Observable<CollectBean> uncollect = apiserver.uncollect_two( "loginUserName" + name, "loginPassWord" + psw,id);
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
}
