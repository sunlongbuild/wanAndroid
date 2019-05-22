package com.jiyun.wanandroid.ui.loginactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.base.BaseActivity;
import com.jiyun.wanandroid.base.Constants;
import com.jiyun.wanandroid.entity.login.RegisterBean;
import com.jiyun.wanandroid.presenter.login.RegisterP;
import com.jiyun.wanandroid.utils.SpUtil;
import com.jiyun.wanandroid.view.login.RegisterV;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<RegisterV, RegisterP> implements RegisterV {

    private static final String TAG = "RegisterActivity";
    @BindView(R.id.register_yhm)
    EditText mRegisterYhm;
    @BindView(R.id.register_mm)
    EditText mRegisterMm;
    @BindView(R.id.register_qrmm)
    EditText mRegisterQrmm;
    @BindView(R.id.register_btn)
    Button mRegisterBtn;
    @BindView(R.id.register_zhzc)
    TextView mRegisterZhzc;
    private ArrayList<RegisterBean.DataBean> mDataBeans;
    private String mName;
    private String mPwd;
    private String mPwd2;
    private RegisterP mLoginP;
    private String mYhm;
    private String mMm;
    private String mQrmm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected RegisterP initPresenter() {
        if(mLoginP==null){
        mLoginP = new RegisterP();
        }
        return mLoginP;
    }

    protected void initView() {
        mDataBeans = new ArrayList<>();
        mName = mRegisterYhm.getText().toString();
        mPwd = mRegisterMm.getText().toString();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void setData(RegisterBean bean) {

    }

    @Override
    protected void initData() {
        mLoginP.setData(mName,mPwd,mPwd2);
    }

    @OnClick({ R.id.register_btn, R.id.register_zhzc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register_btn:
                mYhm = mRegisterYhm.getText().toString();
                mMm = mRegisterMm.getText().toString();
                mQrmm = mRegisterQrmm.getText().toString();
                if (!mMm.isEmpty() && !mQrmm.isEmpty() && !mYhm.isEmpty()) {
                    if (mMm.equals(mQrmm)) {
                        SpUtil.setParam(Constants.NAME, mYhm);
                        SpUtil.setParam(Constants.PSW, mMm);

                        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    }
                } else {
                    Toast.makeText(this, "用户名或者密码不能为空", Toast.LENGTH_SHORT).show();
                }



                break;
            case R.id.register_zhzc:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                break;
        }
    }
}
