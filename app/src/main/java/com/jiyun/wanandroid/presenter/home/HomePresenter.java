package com.jiyun.wanandroid.presenter.home;

import com.jiyun.wanandroid.base.BasePresenter;
import com.jiyun.wanandroid.entity.home.HomeBannerBean;
import com.jiyun.wanandroid.entity.home.HomeRevBean;
import com.jiyun.wanandroid.entity.home.HomeTopBean;
import com.jiyun.wanandroid.model.home.HomeModel;
import com.jiyun.wanandroid.net.ResultCallBack;
import com.jiyun.wanandroid.utils.ToastUtil;
import com.jiyun.wanandroid.view.home.HomeView;

public class HomePresenter extends BasePresenter<HomeView> {

    private HomeModel homeModel;

    @Override
    protected void initModel() {
        homeModel = new HomeModel();
        mModels.add(homeModel);
    }

    public void getBanner() {
        homeModel.getBanner(new ResultCallBack<HomeBannerBean>() {
            @Override
            public void onSuccess(HomeBannerBean bean) {
                if (bean!=null){
                    if (mBaseView!=null){
                        mBaseView.setBanner(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    public void getRv(int page) {
        homeModel.getRv(page, new ResultCallBack<HomeRevBean>() {
            @Override
            public void onSuccess(HomeRevBean bean) {
                if (bean!=null){
                    if (mBaseView!=null){
                        mBaseView.setRv(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {

                ToastUtil.showLong(msg);
            }
        });
    }

    public void gettop() {
        homeModel.gettop(new ResultCallBack<HomeTopBean>() {
            @Override
            public void onSuccess(HomeTopBean bean) {
                if (bean!=null){
                    if (mBaseView!=null){
                        mBaseView.setTop(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
