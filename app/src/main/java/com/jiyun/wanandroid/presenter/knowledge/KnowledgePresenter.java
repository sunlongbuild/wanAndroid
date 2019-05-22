package com.jiyun.wanandroid.presenter.knowledge;

import com.jiyun.wanandroid.base.BasePresenter;
import com.jiyun.wanandroid.entity.knowledge.KnowledgeBean;
import com.jiyun.wanandroid.model.knowledge.KnowledgeModel;
import com.jiyun.wanandroid.net.ResultCallBack;
import com.jiyun.wanandroid.utils.ToastUtil;
import com.jiyun.wanandroid.view.knowledge.KnowledgeView;

public class KnowledgePresenter extends BasePresenter<KnowledgeView> {

    private KnowledgeModel knowledgeModel;

    @Override
    protected void initModel() {

        knowledgeModel = new KnowledgeModel();
        mModels.add(knowledgeModel);
    }

    public void getData() {
        knowledgeModel.getData(new ResultCallBack<KnowledgeBean>() {
            @Override
            public void onSuccess(KnowledgeBean bean) {
                if (bean != null) {
                    if (mBaseView != null) {
                     mBaseView.setData(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {

                ToastUtil.showLong(msg);
            }
        });
    }
}
