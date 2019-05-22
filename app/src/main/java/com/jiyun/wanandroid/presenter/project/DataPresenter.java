package com.jiyun.wanandroid.presenter.project;

import com.jiyun.wanandroid.base.BasePresenter;
import com.jiyun.wanandroid.entity.project.ListDataBean;
import com.jiyun.wanandroid.model.project.DataModel;
import com.jiyun.wanandroid.net.ResultCallBack;
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

}
