package com.jiyun.wanandroid.presenter.wechat;

import com.jiyun.wanandroid.base.BasePresenter;
import com.jiyun.wanandroid.entity.collect.CollectBean;
import com.jiyun.wanandroid.entity.wechat.WeChatBean;
import com.jiyun.wanandroid.model.wechat.ChildWeChatModel;
import com.jiyun.wanandroid.net.ResultCallBack;
import com.jiyun.wanandroid.utils.ToastUtil;
import com.jiyun.wanandroid.view.wechat.ChildWeChatView;

public class ChildWeChatPresenter extends BasePresenter<ChildWeChatView> {

    private ChildWeChatModel childWeChatModel;

    @Override
    protected void initModel() {

        childWeChatModel = new ChildWeChatModel();
        mModels.add(childWeChatModel);
    }

    public void getData(int id, int page) {
        childWeChatModel.getData(id, page, new ResultCallBack<WeChatBean>() {
            @Override
            public void onSuccess(WeChatBean bean) {
                if (bean != null) {
                    if (mBaseView != null) {
                        mBaseView.setData(bean);
                    } else {
                        ToastUtil.showShort("请求失败");
                    }
                }
            }

            @Override
            public void onFail(String msg) {
                ToastUtil.showShort(msg);
            }
        });
    }
    public void collect(int id){
        childWeChatModel.collect(new ResultCallBack<CollectBean>() {
            @Override
            public void onSuccess(CollectBean bean) {
                if (bean!=null) {
                    if (bean.getErrorCode()==0) {
                        mBaseView.collectSuccess(bean);
                    }
                }else {
                    ToastUtil.showShort("错了");
                }
            }
            @Override
            public void onFail(String msg) {
                ToastUtil.showShort("我错了");
            }
        }, id);
    }
    public void unCollect(int id){
        childWeChatModel.uncollect(new ResultCallBack<CollectBean>() {
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
}
