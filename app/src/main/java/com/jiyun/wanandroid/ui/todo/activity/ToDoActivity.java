package com.jiyun.wanandroid.ui.todo.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.base.BaseActivity;
import com.jiyun.wanandroid.presenter.EmptyPresenter;
import com.jiyun.wanandroid.view.EmptyView;

import butterknife.BindView;
import butterknife.OnClick;

public class ToDoActivity extends BaseActivity<EmptyView, EmptyPresenter> implements EmptyView {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv_change)
    ImageView mIvChange;
    @BindView(R.id.fl_container)
    FrameLayout mFlContainer;
    @BindView(R.id.btn_float)
    FloatingActionButton mBtnFloat;

    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_to_do;
    }

    @Override
    protected void initView() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

    }

    @OnClick({R.id.iv_change, R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_change:
                popwindow();//弹出popupwindow
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_float:
                startActivity(new Intent(ToDoActivity.this, AddToDoActivity.class));
                break;
        }
    }

    private void popwindow() {
        final PopupWindow popupWindow = new PopupWindow();
        View inflate = LayoutInflater.from(ToDoActivity.this).inflate(R.layout.layout_todo_pop, null);
        popupWindow.setContentView(inflate);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(null);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(mToolbar, 800, 0);
    }

}
