package com.jiyun.wanandroid.ui.todo.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.base.BaseFragment;
import com.jiyun.wanandroid.presenter.EmptyPresenter;
import com.jiyun.wanandroid.view.EmptyView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ToDoFragment extends BaseFragment<EmptyView,EmptyPresenter> implements EmptyView {


    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_to_do;
    }

}
