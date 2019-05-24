package com.jiyun.wanandroid.presenter.collect;

import com.jiyun.wanandroid.base.BasePresenter;
import com.jiyun.wanandroid.entity.collect.CollectListBean;
import com.jiyun.wanandroid.model.collect.CollectModel;
import com.jiyun.wanandroid.net.ResultCallBack;
import com.jiyun.wanandroid.view.collect.CollectView;

public class CollectPersenter extends BasePresenter<CollectView> implements ResultCallBack<CollectListBean> {

    private CollectModel model;

    @Override
    protected void initModel() {
        model = new CollectModel();
    }
    public void CollectList(int page,String username,String password){
        model.collectList(this,page,username,password);
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
