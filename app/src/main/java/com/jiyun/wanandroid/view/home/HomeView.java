package com.jiyun.wanandroid.view.home;

import com.jiyun.wanandroid.base.BaseView;
import com.jiyun.wanandroid.entity.collect.CollectBean;
import com.jiyun.wanandroid.entity.home.HomeBannerBean;
import com.jiyun.wanandroid.entity.home.HomeRevBean;
import com.jiyun.wanandroid.entity.home.HomeTopBean;

public interface HomeView extends BaseView {
    void setBanner(HomeBannerBean bean);

    void setRv(HomeRevBean bean);

    void setTop(HomeTopBean bean);

    void collectSuccess(CollectBean collectBean);

    void unCollectSuccess(CollectBean collectBean);
}
