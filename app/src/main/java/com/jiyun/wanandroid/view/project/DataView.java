package com.jiyun.wanandroid.view.project;

import com.jiyun.wanandroid.base.BaseView;
import com.jiyun.wanandroid.entity.collect.CollectBean;
import com.jiyun.wanandroid.entity.project.ListDataBean;

public interface DataView extends BaseView{
    void setData(ListDataBean bean);
    void collectSeccess(CollectBean collectBean);
    void collectFail(String string);
    void unCollectSeccess(CollectBean collectBean);
    void unCollectFail(String string);
}
