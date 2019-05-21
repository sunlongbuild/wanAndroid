package com.jiyun.wanandroid.model.project;

import com.jiyun.wanandroid.api.project.ProjectApi;
import com.jiyun.wanandroid.base.BaseModel;
import com.jiyun.wanandroid.entity.project.ListDataBean;
import com.jiyun.wanandroid.net.BaseObserver;
import com.jiyun.wanandroid.net.HttpUtils;
import com.jiyun.wanandroid.net.ResultCallBack;
import com.jiyun.wanandroid.net.RxUtils;

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
}
