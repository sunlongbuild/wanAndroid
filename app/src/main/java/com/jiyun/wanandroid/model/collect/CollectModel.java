package com.jiyun.wanandroid.model.collect;

import com.jiyun.wanandroid.api.collect.CollectApi;
import com.jiyun.wanandroid.base.BaseModel;
import com.jiyun.wanandroid.entity.collect.CollectListBean;
import com.jiyun.wanandroid.net.BaseObserver;
import com.jiyun.wanandroid.net.HttpUtils;
import com.jiyun.wanandroid.net.ResultCallBack;
import com.jiyun.wanandroid.net.RxUtils;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class CollectModel extends BaseModel {
    public void collectList(final ResultCallBack<CollectListBean> resultCallBack, int page,String username,String password){
        CollectApi apiserver = HttpUtils.getInstance().getApiserver(CollectApi.URL, CollectApi.class);
        Observable<CollectListBean> collectList = apiserver.collectList(page,username,password);
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
}
