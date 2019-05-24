package com.jiyun.wanandroid.model.knowledge;

import com.jiyun.wanandroid.api.knowledge.KaiFaHuanJingApi;
import com.jiyun.wanandroid.base.BaseModel;
import com.jiyun.wanandroid.entity.knowledge.KaiFaHuanJingBean;
import com.jiyun.wanandroid.net.BaseObserver;
import com.jiyun.wanandroid.net.HttpUtils;
import com.jiyun.wanandroid.net.ResultCallBack;
import com.jiyun.wanandroid.net.RxUtils;

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
}
