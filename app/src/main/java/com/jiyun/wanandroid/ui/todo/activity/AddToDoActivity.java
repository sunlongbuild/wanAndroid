package com.jiyun.wanandroid.ui.todo.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.base.BaseActivity;
import com.jiyun.wanandroid.base.Constants;
import com.jiyun.wanandroid.entity.todo.AddToDoBean;
import com.jiyun.wanandroid.presenter.EmptyPresenter;
import com.jiyun.wanandroid.presenter.todo.AddToDoPresenter;
import com.jiyun.wanandroid.ui.login.LoginActivity;
import com.jiyun.wanandroid.utils.DateUtil;
import com.jiyun.wanandroid.utils.SpUtil;
import com.jiyun.wanandroid.utils.ToastUtil;
import com.jiyun.wanandroid.view.EmptyView;
import com.jiyun.wanandroid.view.todo.AddToDoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddToDoActivity extends BaseActivity<AddToDoView, AddToDoPresenter> implements
        AddToDoView {

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
    private String mUserName;
    private String mPsw;

    @Override
    protected AddToDoPresenter initPresenter() {
        return new AddToDoPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_todo;
    }

    @Override
    protected void initData() {
        String name = mTodoName.getText().toString();
        String des = mTodoDes.getText().toString();
        String date = mTvData.getText().toString();
        //获取登录的用户名和密码
        mUserName = (String) SpUtil.getParam(Constants.NAME, "");
        mPsw = (String) SpUtil.getParam(Constants.PSW, "");

        mPresenter.getAddToDo(name, des, date, "loginUserName=" + mUserName,
                "loginUserPassword="+mPsw);
    }

    @OnClick({R.id.ll_calendar, R.id.iv_back, R.id.save_todo})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.ll_calendar://日历
                DateUtil.getDateTime(AddToDoActivity.this, mTvData);
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.save_todo://点击保存 把信息提交到接口
                if (TextUtils.isEmpty(mUserName) && TextUtils.isEmpty(mPsw)) {
                    new AlertDialog.Builder(AddToDoActivity.this).setMessage(getResources()
                            .getString(R.string.please_login)).setPositiveButton(getResources()
                            .getString(R.string.login), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(AddToDoActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    }).setNegativeButton(getResources().getString(R.string.cancel), null).show();
                } else {
                    initData();
                    ToastUtil.showShort(getResources().getString(R.string.save_success));
                    finish();
                }
                break;
        }
    }

    @Override
    public void setData(AddToDoBean addToDoBean) {

    }
}
