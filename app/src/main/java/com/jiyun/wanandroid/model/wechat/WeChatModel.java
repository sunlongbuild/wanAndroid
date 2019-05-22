package com.jiyun.wanandroid.model.wechat;

import com.jiyun.wanandroid.api.WeChat.WeChatApi;
import com.jiyun.wanandroid.base.BaseModel;
import com.jiyun.wanandroid.entity.wechat.WeChatTabBean;
import com.jiyun.wanandroid.net.BaseObserver;
import com.jiyun.wanandroid.net.HttpUtils;
import com.jiyun.wanandroid.net.ResultCallBack;
import com.jiyun.wanandroid.net.RxUtils;

import io.reactivex.disposables.Disposable;

public class WeChatModel extends BaseModel {
    public void getTabData(final ResultCallBack<WeChatTabBean> resultCallBack) {
        WeChatApi apiserver = HttpUtils.getInstance().getApiserver(WeChatApi.WeChatUrl, WeChatApi.class);
        apiserver.getTab().compose(RxUtils.<WeChatTabBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<WeChatTabBean>() {
                    @Override
                    public void onNext(WeChatTabBean weChatTabBean) {

                        if (weChatTabBean != null) {
                            resultCallBack.onSuccess(weChatTabBean);
                        } else {
                            resultCallBack.onFail("全球失败");
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
