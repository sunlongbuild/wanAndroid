package com.jiyun.wanandroid.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by $sl on 2019/5/20 16:13.
 */
public abstract class BaseFragment<V extends BaseView, P extends BasePresenter> extends Fragment implements BaseView {
    protected P mPresenter;
    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), null);
        mUnbinder = ButterKnife.bind(this, view);
        mPresenter = initPresenter();
        if (mPresenter != null) {
            mPresenter.bind((V) this);
        }
        initView();
        initListenter();
        initData();
        return view;
    }

    protected abstract P initPresenter();

    protected void initData() {

    }

    protected void initListenter() {

    }

    protected void initView() {

    }

    protected abstract int getLayoutId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
        mPresenter.onDestory();
        mPresenter = null;
    }

}
