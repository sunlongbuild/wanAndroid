package com.jiyun.wanandroid.view.home;

import com.jiyun.wanandroid.base.BaseView;
import com.jiyun.wanandroid.entity.home.HomeBannerBean;
import com.jiyun.wanandroid.entity.home.HomeRevBean;

public interface HomeView extends BaseView {
    void setBanner(HomeBannerBean bean);

    void setRv(HomeRevBean bean);
}
