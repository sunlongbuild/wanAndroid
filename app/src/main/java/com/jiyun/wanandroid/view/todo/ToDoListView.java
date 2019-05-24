package com.jiyun.wanandroid.view.todo;

import com.jiyun.wanandroid.base.BaseView;
import com.jiyun.wanandroid.entity.todo.ToDoListBean;

/**
 * Created by $sl on 2019/5/24 10:30.
 */
public interface ToDoListView extends BaseView {
    void setToDoList(ToDoListBean toDoList);
}
