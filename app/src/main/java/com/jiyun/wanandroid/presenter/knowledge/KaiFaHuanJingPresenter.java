package com.jiyun.wanandroid.presenter.knowledge;

import com.jiyun.wanandroid.base.BasePresenter;
import com.jiyun.wanandroid.entity.knowledge.KnowledgeBean;
import com.jiyun.wanandroid.model.knowledge.KaiFaHuanJingModel;
import com.jiyun.wanandroid.net.ResultCallBack;
import com.jiyun.wanandroid.utils.ToastUtil;
import com.jiyun.wanandroid.view.knowledge.KaiFaHuanJingView;

public class KaiFaHuanJingPresenter extends BasePresenter<KaiFaHuanJingView> {

    private KaiFaHuanJingModel kaiFaHuanJingModel;

    @Override
    protected void initModel() {

        kaiFaHuanJingModel = new KaiFaHuanJingModel();
        mModels.add(kaiFaHuanJingModel);
    }

    public void getHierarchy() {
           kaiFaHuanJingModel.getHierarchy(new ResultCallBack<KnowledgeBean>() {
               @Override
               public void onSuccess(KnowledgeBean bean) {
                   if (bean!=null){
                       if (mBaseView!=null){
                           mBaseView.androidHierarchy(bean.getData());
                       }else {
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
}
