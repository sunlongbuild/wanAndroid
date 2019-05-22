package com.jiyun.wanandroid.model.project;

import com.jiyun.wanandroid.api.project.ProjectApi;
import com.jiyun.wanandroid.base.BaseModel;
import com.jiyun.wanandroid.entity.project.TabBean;
import com.jiyun.wanandroid.net.BaseObserver;
import com.jiyun.wanandroid.net.HttpUtils;
import com.jiyun.wanandroid.net.ResultCallBack;
import com.jiyun.wanandroid.net.RxUtils;

import io.reactivex.disposables.Disposable;

public class ProjectModel extends BaseModel{
    public void getData(final ResultCallBack<TabBean> resultCallBack) {
        ProjectApi apiserver = HttpUtils.getInstance().getApiserver(ProjectApi.PROJECT_URL, ProjectApi.class);
        apiserver.getTabData().compose(RxUtils.<TabBean>rxObserableSchedulerHelper()).subscribe(
                new BaseObserver<TabBean>() {
                    @Override
                    public void onNext(TabBean tabBean) {
                        if (tabBean!=null){
                            resultCallBack.onSuccess(tabBean);
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
}
