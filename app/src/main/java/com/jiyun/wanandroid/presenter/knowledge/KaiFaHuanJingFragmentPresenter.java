package com.jiyun.wanandroid.presenter.knowledge;

import com.jiyun.wanandroid.base.BasePresenter;
import com.jiyun.wanandroid.entity.collect.CollectBean;
import com.jiyun.wanandroid.entity.knowledge.KaiFaHuanJingBean;
import com.jiyun.wanandroid.model.knowledge.KaiFaHuanJingFragmentModel;
import com.jiyun.wanandroid.net.ResultCallBack;
import com.jiyun.wanandroid.utils.ToastUtil;
import com.jiyun.wanandroid.view.knowledge.KaiFaHuanJingFragmentView;

public class KaiFaHuanJingFragmentPresenter  extends BasePresenter<KaiFaHuanJingFragmentView> {

    private KaiFaHuanJingFragmentModel kaiFaHuanJingFragmentModel;

    @Override
    protected void initModel() {

        kaiFaHuanJingFragmentModel = new KaiFaHuanJingFragmentModel();
        mModels.add(kaiFaHuanJingFragmentModel);
    }

    public void getData(int page, int id) {
        kaiFaHuanJingFragmentModel.getData(page,id, new ResultCallBack<KaiFaHuanJingBean>() {
            @Override
            public void onSuccess(KaiFaHuanJingBean bean) {
                if (bean!=null){
                    if (mBaseView!=null){
                        mBaseView.setData(bean);
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
        kaiFaHuanJingFragmentModel.collect(new ResultCallBack<CollectBean>() {
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
        kaiFaHuanJingFragmentModel.uncollect(new ResultCallBack<CollectBean>() {
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
