package com.jiyun.wanandroid.model.project;

import com.jiyun.wanandroid.api.project.ProjectApi;
import com.jiyun.wanandroid.base.BaseModel;
import com.jiyun.wanandroid.base.Constants;
import com.jiyun.wanandroid.entity.collect.CollectBean;
import com.jiyun.wanandroid.entity.project.ListDataBean;
import com.jiyun.wanandroid.net.BaseObserver;
import com.jiyun.wanandroid.net.HttpUtils;
import com.jiyun.wanandroid.net.ResultCallBack;
import com.jiyun.wanandroid.net.RxUtils;
import com.jiyun.wanandroid.utils.SpUtil;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;


public class DataModel extends BaseModel{
    public void getData(int page, int cid, final ResultCallBack<ListDataBean> resultCallBack) {
        ProjectApi apiserver = HttpUtils.getInstance().getApiserver(ProjectApi.PROJECT_URL, ProjectApi.class);
        apiserver.getData(page,cid).compose(RxUtils.<ListDataBean>rxObserableSchedulerHelper()).subscribe(
                new BaseObserver<ListDataBean>() {
                    @Override
                    public void onNext(ListDataBean listDataBean) {
                        if (listDataBean!=null){
                            resultCallBack.onSuccess(listDataBean);
                        }
                    }

                    @Override
                    public void error(String msg) {

                    }

                    @Override
                    protected void subscribe(Disposable d) {

                    }
                }
        );
    }
    public void uncollect(final ResultCallBack<CollectBean> resultCallBack , int id) {
        ProjectApi apiserver = HttpUtils.getInstance().getApiserver(ProjectApi.PROJECT_URL, ProjectApi.class);
        String name = (String) SpUtil.getParam(Constants.NAME, "");
        String psw = (String) SpUtil.getParam(Constants.PSW, "");
        final Observable<CollectBean> uncollect = apiserver.uncollect("loginUserName="+name, "loginPassWord="+psw,id);
        uncollect.compose(RxUtils.<CollectBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<CollectBean>() {
                    @Override
                    public void onNext(CollectBean collectBean) {
                        resultCallBack.onSuccess(collectBean);
                    }

                    @Override
                    public void error(String msg) {
                        resultCallBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }
                });
    }
    public void collect(final ResultCallBack<CollectBean> resultCallBack,int id) {
        ProjectApi apiserver = HttpUtils.getInstance().getApiserver(ProjectApi.PROJECT_URL, ProjectApi.class);
        String name = (String) SpUtil.getParam(Constants.NAME, "");
        String psw = (String) SpUtil.getParam(Constants.PSW, "");
        final Observable<CollectBean> collect = apiserver.collect("loginUserName="+name, "loginUserPassword="+psw,id);
        collect.compose(RxUtils.<CollectBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<CollectBean>() {
                    @Override
                    public void onNext(CollectBean collectBean) {
                        resultCallBack.onSuccess(collectBean);
                    }

                    @Override
                    public void error(String msg) {
                        resultCallBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }
                });
    }
}
