package com.jiyun.wanandroid.presenter.todo;

import android.text.TextUtils;

import com.jiyun.wanandroid.base.BasePresenter;
import com.jiyun.wanandroid.base.BaseView;
import com.jiyun.wanandroid.entity.todo.AddToDoBean;
import com.jiyun.wanandroid.entity.todo.ToDoListBean;
import com.jiyun.wanandroid.model.todo.AddToDoModel;
import com.jiyun.wanandroid.net.ResultCallBack;
import com.jiyun.wanandroid.view.todo.AddToDoView;

/**
 * Created by $sl on 2019/5/23 13:11.
 */
public class AddToDoPresenter extends BasePresenter<AddToDoView> implements
        ResultCallBack<AddToDoBean> {

    private AddToDoModel mAddToDoModel;

    @Override
    protected void initModel() {
        mAddToDoModel = new AddToDoModel();
        mModels.add(mAddToDoModel);
    }

    public void getAddToDo(String todoName, String todoDes, String todoDate, String userName,
                           String psw) {
        mAddToDoModel.getAddToDo(todoName, todoDes, todoDate, userName, psw, this);
    }


    @Override
    public void onSuccess(AddToDoBean bean) {
        if (mBaseView != null) {
            if (bean != null && bean.getData() != null) {
                mBaseView.setData(bean);
            }
        }
    }

    @Override
    public void onFail(String msg) {

    }
}
