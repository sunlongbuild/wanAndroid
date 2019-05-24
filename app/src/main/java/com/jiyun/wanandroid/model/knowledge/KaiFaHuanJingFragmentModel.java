package com.jiyun.wanandroid.model.knowledge;

import com.jiyun.wanandroid.api.knowledge.KaiFaHuanJingApi;
import com.jiyun.wanandroid.base.BaseModel;
import com.jiyun.wanandroid.base.Constants;
import com.jiyun.wanandroid.entity.collect.CollectBean;
import com.jiyun.wanandroid.entity.knowledge.KaiFaHuanJingBean;
import com.jiyun.wanandroid.net.BaseObserver;
import com.jiyun.wanandroid.net.HttpUtils;
import com.jiyun.wanandroid.net.ResultCallBack;
import com.jiyun.wanandroid.net.RxUtils;
import com.jiyun.wanandroid.utils.SpUtil;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class KaiFaHuanJingFragmentModel extends BaseModel {
    public void getData(int page, int id, final ResultCallBack<KaiFaHuanJingBean> resultCallBack) {
        KaiFaHuanJingApi apiserver = HttpUtils.getInstance().getApiserver(KaiFaHuanJingApi.KaiFaHuanJingUrl, KaiFaHuanJingApi.class);
        apiserver.getkaifahuanjing(page, id).compose(RxUtils.<KaiFaHuanJingBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<KaiFaHuanJingBean>() {
                    @Override
                    public void onNext(KaiFaHuanJingBean kaiFaHuanJingBean) {

                        if (kaiFaHuanJingBean != null) {
                            resultCallBack.onSuccess(kaiFaHuanJingBean);
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
        KaiFaHuanJingApi apiserver = HttpUtils.getInstance().getApiserver(KaiFaHuanJingApi.KaiFaHuanJingUrl, KaiFaHuanJingApi.class);
        String name = (String) SpUtil.getParam(Constants.NAME, "");
        String psw = (String) SpUtil.getParam(Constants.PSW, "");
        final Observable<CollectBean> uncollect = apiserver.uncollect(id, "loginUserName" + name, "loginUserPassWord" + psw,-1);
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
        String name = (String) SpUtil.getParam(Constants.NAME, "");
        String psw = (String) SpUtil.getParam(Constants.PSW, "");
        KaiFaHuanJingApi apiserver = HttpUtils.getInstance().getApiserver(KaiFaHuanJingApi.KaiFaHuanJingUrl, KaiFaHuanJingApi.class);
        final Observable<CollectBean> collect = apiserver.collect("loginUserName=" + name, "loginUserPassWord=" +psw,id);
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
