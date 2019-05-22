package com.jiyun.wanandroid.model;

import com.jiyun.wanandroid.api.home.knowledge.KnowledgeApi;
import com.jiyun.wanandroid.base.BaseModel;
import com.jiyun.wanandroid.entity.knowledge.KnowledgeBean;
import com.jiyun.wanandroid.net.BaseObserver;
import com.jiyun.wanandroid.net.HttpUtils;
import com.jiyun.wanandroid.net.ResultCallBack;
import com.jiyun.wanandroid.net.RxUtils;

import io.reactivex.disposables.Disposable;

public class KaiFaHuanJingModel extends BaseModel {
    public void getHierarchy(final ResultCallBack<KnowledgeBean> resultCallBack) {
        KnowledgeApi apiserver = HttpUtils.getInstance().getApiserver(KnowledgeApi.KnowledgeApi, KnowledgeApi.class);
        apiserver.getKnowledge().compose(RxUtils.<KnowledgeBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<KnowledgeBean>() {
                    @Override
                    public void onNext(KnowledgeBean knowledgeBean) {

                        if (knowledgeBean != null) {
                            resultCallBack.onSuccess(knowledgeBean);
                        } else {
                            resultCallBack.onFail("失败");
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
