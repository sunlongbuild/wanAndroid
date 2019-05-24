package com.jiyun.wanandroid.view.collect;

import com.jiyun.wanandroid.base.BaseView;
import com.jiyun.wanandroid.entity.collect.CollectListBean;

public interface CollectView extends BaseView {
    void onSuccess(CollectListBean bean);
    void onFail(String msg);
}
