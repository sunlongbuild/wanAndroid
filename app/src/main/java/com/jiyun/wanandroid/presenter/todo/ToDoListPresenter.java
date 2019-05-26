package com.jiyun.wanandroid.presenter.todo;

import com.jiyun.wanandroid.base.BasePresenter;
import com.jiyun.wanandroid.base.BaseView;
import com.jiyun.wanandroid.entity.todo.ToDoListBean;
import com.jiyun.wanandroid.model.todo.ToDoListModel;
import com.jiyun.wanandroid.net.ResultCallBack;
import com.jiyun.wanandroid.view.todo.ToDoListView;

/**
 * Created by $sl on 2019/5/24 10:30.
 */
public class ToDoListPresenter extends BasePresenter<ToDoListView> {

    private ToDoListModel mToDoListModel;

    @Override
    protected void initModel() {
        mToDoListModel = new ToDoListModel();
        mModels.add(mToDoListModel);
    }

    public void getToDoList(int status,String userName, String psw) {
        mToDoListModel.getTodoList(status,userName, psw, new ResultCallBack<ToDoListBean>() {
            @Override
            public void onSuccess(ToDoListBean bean) {
                if (mBaseView != null) {
                    if (bean != null && bean.getData() != null) {
                        mBaseView.setToDoList(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {
            }
        });
    }
}
