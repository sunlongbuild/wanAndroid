package com.jiyun.wanandroid.ui.todo.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.base.BaseActivity;
import com.jiyun.wanandroid.presenter.EmptyPresenter;
import com.jiyun.wanandroid.utils.DateUtil;
import com.jiyun.wanandroid.view.EmptyView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddToDoActivity extends BaseActivity<EmptyView, EmptyPresenter> implements EmptyView {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.todo_title)
    TextView mTodoTitle;
    @BindView(R.id.todo_name)
    TextInputEditText mTodoName;
    @BindView(R.id.todo_des)
    TextInputEditText mTodoDes;
    @BindView(R.id.ll_calendar)
    LinearLayout mLlCalendar;
    @BindView(R.id.save_todo)
    Button mSaveTodo;
    @BindView(R.id.rb_common)
    RadioButton mRbCommon;
    @BindView(R.id.important)
    RadioButton mImportant;
    @BindView(R.id.tv_data)
    TextView mTvData;
    @BindView(R.id.iv_right)
    ImageView mIvRight;

    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_todo;
    }

    @OnClick(R.id.ll_calendar)
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.ll_calendar:
                DateUtil.getDateTime(AddToDoActivity.this, mTvData);
                break;
        }
    }
}
