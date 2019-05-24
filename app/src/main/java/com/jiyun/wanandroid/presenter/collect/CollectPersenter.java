package com.jiyun.wanandroid.presenter.collect;

import com.jiyun.wanandroid.base.BasePresenter;
import com.jiyun.wanandroid.entity.collect.CollectBean;
import com.jiyun.wanandroid.entity.collect.CollectListBean;
import com.jiyun.wanandroid.model.collect.CollectModel;
import com.jiyun.wanandroid.net.ResultCallBack;
import com.jiyun.wanandroid.utils.ToastUtil;
import com.jiyun.wanandroid.view.collect.CollectView;

public class CollectPersenter extends BasePresenter<CollectView> implements ResultCallBack<CollectListBean> {

    private CollectModel model;

    @Override
    protected void initModel() {
        model = new CollectModel();
    }
    public void CollectList(int page){
        model.collectList(this);
    }
    public void unCollect(int id){
        model.uncollect(new ResultCallBack<CollectBean>() {
            @Override
            public void onSuccess(CollectBean bean) {
                if (bean!=null) {
                    if (bean.getErrorCode()==0) {
                        mBaseView.unCollectSuccess(bean);
                    }
                }
            }
            @Override
            public void onFail(String msg) {
                ToastUtil.showShort(msg);
            }
        },id);
    }
    public void unCollect_Two(int id){
        model.uncollect(new ResultCallBack<CollectBean>() {
            @Override
            public void onSuccess(CollectBean bean) {
                if (bean!=null) {
                    if (bean.getErrorCode()==0) {
                        mBaseView.unCollectSuccess(bean);
                    }
                }
            }
            @Override
            public void onFail(String msg) {
                ToastUtil.showShort(msg);
            }
        },id);
    }


    @Override
    public void onSuccess(CollectListBean bean) {
        if (bean!=null) {
            if (bean.getErrorCode()==0) {
                mBaseView.onSuccess(bean);
            }
        }
    }

    @Override
    public void onFail(String msg) {
            mBaseView.onFail(msg);
    }
}
