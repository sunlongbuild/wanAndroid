package com.jiyun.wanandroid.view.wechat;

import com.jiyun.wanandroid.base.BaseView;
import com.jiyun.wanandroid.entity.collect.CollectBean;
import com.jiyun.wanandroid.entity.wechat.WeChatBean;

public interface ChildWeChatView extends BaseView {
    void setData(WeChatBean bean);
    void collectSuccess(CollectBean collectBean);

    void unCollectSuccess(CollectBean collectBean);

}
