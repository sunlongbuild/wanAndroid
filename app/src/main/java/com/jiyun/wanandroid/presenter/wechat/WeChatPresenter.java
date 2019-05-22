package com.jiyun.wanandroid.presenter.wechat;

import com.jiyun.wanandroid.base.BasePresenter;
import com.jiyun.wanandroid.entity.wechat.WeChatTabBean;
import com.jiyun.wanandroid.model.wechat.WeChatModel;
import com.jiyun.wanandroid.net.ResultCallBack;
import com.jiyun.wanandroid.utils.ToastUtil;
import com.jiyun.wanandroid.view.wechat.WeChatView;

public class WeChatPresenter extends BasePresenter<WeChatView> {

    private WeChatModel weChatModel;

    @Override
    protected void initModel() {

        weChatModel = new WeChatModel();
        mModels.add(weChatModel);
    }

    public void getTabData() {
        weChatModel.getTabData(new ResultCallBack<WeChatTabBean>() {
            @Override
            public void onSuccess(WeChatTabBean bean) {
                if (bean!=null){
                    if (mBaseView!=null){
                        mBaseView.setTab(bean);
                    }else {
                        ToastUtil.showShort("全球失败");
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
