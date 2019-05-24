package com.jiyun.wanandroid.ui.todo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.base.BaseActivity;
import com.jiyun.wanandroid.presenter.EmptyPresenter;
import com.jiyun.wanandroid.ui.todo.fragment.FinishFragment;
import com.jiyun.wanandroid.ui.todo.fragment.ToDoFragment;
import com.jiyun.wanandroid.view.EmptyView;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    @BindView(R.id.rb_todo)
    RadioButton mRbTodo;
    @BindView(R.id.rb_finish)
    RadioButton mRbFinish;
    @BindView(R.id.rg_tab)
    RadioGroup mRgTab;
    private ToDoFragment mToDoFragment;
    private FinishFragment mFinishFragment;
    private FragmentTransaction mTransaction;

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

    @Override
    protected void initData() {
        mToDoFragment = new ToDoFragment();
        mFinishFragment = new FinishFragment();

        FragmentManager manager = getSupportFragmentManager();
        mTransaction = manager.beginTransaction();
        mTransaction.add(R.id.fl_container, mToDoFragment);
        mTransaction.add(R.id.fl_container, mFinishFragment);

        mTransaction.show(mToDoFragment).hide(mFinishFragment).commit();
    }

    @OnClick({R.id.iv_change, R.id.iv_back, R.id.btn_float, R.id.rb_todo, R.id.rb_finish})
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
            case R.id.rb_todo:
                getSupportFragmentManager().beginTransaction().show(mToDoFragment).hide(mFinishFragment).commit();
                break;
            case R.id.rb_finish:
                getSupportFragmentManager().beginTransaction().show(mFinishFragment).hide(mToDoFragment).commit();
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
