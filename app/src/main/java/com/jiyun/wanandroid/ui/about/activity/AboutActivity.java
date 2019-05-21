package com.jiyun.wanandroid.ui.about.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.base.BaseActivity;
import com.jiyun.wanandroid.presenter.EmptyPresenter;
import com.jiyun.wanandroid.view.EmptyView;

public class AboutActivity extends BaseActivity<EmptyView, EmptyPresenter> implements EmptyView {

    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }
}
