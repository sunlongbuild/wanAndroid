package com.jiyun.wanandroid.presenter;

import com.jiyun.wanandroid.base.BasePresenter;
import com.jiyun.wanandroid.entity.knowledge.KaiFaHuanJingBean;
import com.jiyun.wanandroid.model.KaiFaHuanJingFragmentModel;
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
}
