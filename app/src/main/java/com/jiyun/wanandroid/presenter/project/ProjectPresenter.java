package com.jiyun.wanandroid.presenter.project;

import com.jiyun.wanandroid.base.BasePresenter;
import com.jiyun.wanandroid.entity.project.TabBean;
import com.jiyun.wanandroid.model.project.ProjectModel;
import com.jiyun.wanandroid.net.ResultCallBack;
import com.jiyun.wanandroid.view.project.ProjectView;

public class ProjectPresenter extends BasePresenter<ProjectView>{

    private ProjectModel projectModel;

    @Override
    protected void initModel() {
        projectModel = new ProjectModel();
        mModels.add(projectModel);
    }
    public void getProject(){
        projectModel.getData(new ResultCallBack<TabBean>() {
            @Override
            public void onSuccess(TabBean bean) {
                if (bean!=null){
                    if (mBaseView!=null){
                        mBaseView.getData(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
