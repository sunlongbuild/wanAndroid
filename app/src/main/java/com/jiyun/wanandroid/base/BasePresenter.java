package com.jiyun.wanandroid.base;

import java.util.ArrayList;

/**
 * Created by $sl on 2019/5/20 16:13.
 */
public abstract class BasePresenter<V extends BaseView> {
    protected V mBaseView;
    protected ArrayList<BaseModel> mModels = new ArrayList<>();

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();

    public void bind(V baseView) {
        this.mBaseView = baseView;
    }

    public void onDestory() {
        //打断P层和V层的联系,
        mBaseView = null;
        //掐断网络请求
        if (mModels.size()>0){
            for (BaseModel model :mModels) {
                model.onDestory();
            }
            mModels.clear();
        }
    }
}
