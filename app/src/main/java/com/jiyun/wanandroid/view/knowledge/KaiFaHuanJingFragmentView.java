package com.jiyun.wanandroid.view.knowledge;

import com.jiyun.wanandroid.base.BaseView;
import com.jiyun.wanandroid.entity.collect.CollectBean;
import com.jiyun.wanandroid.entity.knowledge.KaiFaHuanJingBean;

public interface KaiFaHuanJingFragmentView extends BaseView {


    void setData(KaiFaHuanJingBean bean);


    void collectSuccess(CollectBean collectBean);

    void unCollectSuccess(CollectBean collectBean);


}
