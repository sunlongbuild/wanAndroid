package com.jiyun.wanandroid.model.wechat;

import com.jiyun.wanandroid.api.WeChat.WeChatApi;
import com.jiyun.wanandroid.base.BaseModel;
import com.jiyun.wanandroid.base.Constants;
import com.jiyun.wanandroid.entity.collect.CollectBean;
import com.jiyun.wanandroid.entity.wechat.WeChatBean;
import com.jiyun.wanandroid.net.BaseObserver;
import com.jiyun.wanandroid.net.HttpUtils;
import com.jiyun.wanandroid.net.ResultCallBack;
import com.jiyun.wanandroid.net.RxUtils;
import com.jiyun.wanandroid.utils.SpUtil;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class ChildWeChatModel extends BaseModel {
    public void getData(int id, int page, final ResultCallBack<WeChatBean> resultCallBack) {

        WeChatApi apiserver = HttpUtils.getInstance().getApiserver(WeChatApi.WeChatUrl, WeChatApi.class);

        apiserver.getData(id,page).compose(RxUtils.<WeChatBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<WeChatBean>() {
                    @Override
                    public void onNext(WeChatBean weChatBean) {

                        if (weChatBean!=null){
                            resultCallBack.onSuccess(weChatBean);
                        }else {
                            resultCallBack.onFail("请求失败");
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
        WeChatApi apiserver = HttpUtils.getInstance().getApiserver(WeChatApi.WeChatUrl, WeChatApi.class);
        final Observable<CollectBean> uncollect = apiserver.uncollect(id, "loginUserName" + SpUtil.getParam(Constants.NAME,null), "loginPassWord" + SpUtil.getParam(Constants.PSW,null),-1);
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
    public void collect(final ResultCallBack<CollectBean> resultCallBack, int id) {
        WeChatApi apiserver = HttpUtils.getInstance().getApiserver(WeChatApi.WeChatUrl, WeChatApi.class);
        final Observable<CollectBean> collect = apiserver.collect("loginUserName=" + Constants.USERNAME, "loginPassWord=" + Constants.PSW,id);
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
