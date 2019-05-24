package com.jiyun.wanandroid.presenter.home;

import com.jiyun.wanandroid.base.BasePresenter;
import com.jiyun.wanandroid.entity.collect.CollectBean;
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
    public void collect(String title,String author,String link){
        homeModel.collect(new ResultCallBack<CollectBean>() {
            @Override
            public void onSuccess(CollectBean bean) {
                if (bean!=null) {
                    mBaseView.collectSuccess(bean);
                }else {
                    ToastUtil.showShort("错了");
                }
            }
            @Override
            public void onFail(String msg) {
                ToastUtil.showShort("我错了");
            }
        }, title, author, link);
    }
    public void unCollect(int id){
        homeModel.uncollect(new ResultCallBack<CollectBean>() {
            @Override
            public void onSuccess(CollectBean bean) {
                if (bean!=null) {
                    mBaseView.unCollectSuccess(bean);
                }

            }

            @Override
            public void onFail(String msg) {
                ToastUtil.showShort(msg);
            }
        },id);
    }
}
