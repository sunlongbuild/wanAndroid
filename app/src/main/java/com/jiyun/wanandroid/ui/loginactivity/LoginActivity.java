package com.jiyun.wanandroid.ui.loginactivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.base.BaseActivity;
import com.jiyun.wanandroid.base.Constants;
import com.jiyun.wanandroid.entity.login.LoginBean;
import com.jiyun.wanandroid.presenter.login.LoginP;
import com.jiyun.wanandroid.ui.MainActivity;
import com.jiyun.wanandroid.utils.SpUtil;
import com.jiyun.wanandroid.view.login.LoginV;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginV, LoginP> implements LoginV {

    private static final String TAG = "LoginActivity";
    @BindView(R.id.login_yhm)
    EditText mLoginYhm;
    @BindView(R.id.login_mm)
    EditText mLoginMm;
    @BindView(R.id.login_btn)
    Button mLoginBtn;
    @BindView(R.id.login_zhzc)
    TextView mLoginZhzc;

    private String mYhm;
    private String mMm;
    private String mName1;
    private String mPwd1;
    private LoginP mLoginP;
    private String mName;
    private String mPwd;
    private ArrayList<String> mDataBeans;

    @Override
    protected LoginP initPresenter() {
        if(mLoginP==null){
            mLoginP = new LoginP();
        }
        return mLoginP;
    }

    protected void initView() {
        mDataBeans = new ArrayList<String>();

        mName = (String) SpUtil.getParam(Constants.NAME, "");
        mLoginYhm.setText(mName);
        mPwd = (String) SpUtil.getParam(Constants.PSW, "");
        mLoginMm.setText(mPwd);

    }

    @Override
    protected void initData() {
        mLoginP.getData(mName,mPwd);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @OnClick({R.id.login_btn, R.id.login_zhzc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                mYhm = mLoginYhm.getText().toString();
                mMm = mLoginMm.getText().toString();
                if (!mYhm.isEmpty() && !mMm.isEmpty()) {
                    //保存用户信息，并将登录状态标记为已登录
                    SpUtil.setParam(Constants.NAME, mYhm);
                    SpUtil.setParam(Constants.LOGIN, true);
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                } else {
                    Toast.makeText(this, "未知错误，可能抛锚了吧~", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.login_zhzc:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
        }
    }

    @Override
    public void getData(LoginBean bean) {
        if (bean!=null && bean.getData()!=null){
            String username = bean.getData().getUsername();
            mLoginYhm.setText(username);
            if(username.equals(mName)){
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

}