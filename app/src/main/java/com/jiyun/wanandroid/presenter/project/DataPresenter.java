package com.jiyun.wanandroid.presenter.project;

import com.jiyun.wanandroid.base.BasePresenter;
import com.jiyun.wanandroid.entity.collect.CollectBean;
import com.jiyun.wanandroid.entity.project.ListDataBean;
import com.jiyun.wanandroid.model.project.DataModel;
import com.jiyun.wanandroid.net.ResultCallBack;
import com.jiyun.wanandroid.utils.ToastUtil;
import com.jiyun.wanandroid.view.project.DataView;

public class DataPresenter extends BasePresenter<DataView>{

    private DataModel dataModel;

    @Override
    protected void initModel() {
        dataModel = new DataModel();
        mModels.add(dataModel);
    }
    public void getData(int page, int cid){
        dataModel.getData(page,cid,new ResultCallBack<ListDataBean>() {
            @Override
            public void onSuccess(ListDataBean bean) {
                if (bean!=null){
                    if (mBaseView!=null){
                        mBaseView.setData(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
    public void collect(int id){
        dataModel.collect(new ResultCallBack<CollectBean>() {
            @Override
            public void onSuccess(CollectBean bean) {
                if (bean!=null) {
                    if (bean.getErrorCode()==0) {
                        mBaseView.collectSeccess(bean);
                    }
                }else {
                   mBaseView.collectFail("收藏失败");
                }
            }
            @Override
            public void onFail(String msg) {
                ToastUtil.showShort(msg);
            }
        }, id);
    }
    public void unCollect(int id){
        dataModel.uncollect(new ResultCallBack<CollectBean>() {
            @Override
            public void onSuccess(CollectBean bean) {
                if (bean!=null) {
                    if (bean.getErrorCode()==0) {
                        mBaseView.unCollectSeccess(bean);
                    }
                }else {
                    mBaseView.unCollectFail("收藏失败");
                }
            }
            @Override
            public void onFail(String msg) {
                mBaseView.unCollectFail(msg);
            }
        },id);
    }

}
