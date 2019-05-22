package com.jiyun.wanandroid.model.wechat;

import com.jiyun.wanandroid.api.WeChat.WeChatApi;
import com.jiyun.wanandroid.base.BaseModel;
import com.jiyun.wanandroid.entity.wechat.WeChatBean;
import com.jiyun.wanandroid.net.BaseObserver;
import com.jiyun.wanandroid.net.HttpUtils;
import com.jiyun.wanandroid.net.ResultCallBack;
import com.jiyun.wanandroid.net.RxUtils;

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
}
