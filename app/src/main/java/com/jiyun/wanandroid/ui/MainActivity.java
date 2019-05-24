package com.jiyun.wanandroid.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;







import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jiyun.wanandroid.base.Constants;
import com.jiyun.wanandroid.ui.loginactivity.LoginActivity;
import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.base.BaseActivity;
import com.jiyun.wanandroid.presenter.EmptyPresenter;
import com.jiyun.wanandroid.ui.about.activity.AboutActivity;
import com.jiyun.wanandroid.ui.collect.activity.CollectActivity;
import com.jiyun.wanandroid.ui.home.fragment.HomeFragment;
import com.jiyun.wanandroid.ui.knowledge.fragment.KnowledgeFragment;
import com.jiyun.wanandroid.ui.logout.activity.LogoutActivity;
import com.jiyun.wanandroid.ui.navigation.fragment.NavigationFragment;
import com.jiyun.wanandroid.ui.project.fragment.ProjectFragment;
import com.jiyun.wanandroid.ui.setting.activity.SettingActivity;
import com.jiyun.wanandroid.ui.todo.activity.ToDoActivity;
import com.jiyun.wanandroid.ui.wechat.fragment.The_publicFragment;

import com.jiyun.wanandroid.utils.ToastUtil;

import com.jiyun.wanandroid.utils.SpUtil;
import com.jiyun.wanandroid.utils.UIModeUtil;
import com.jiyun.wanandroid.view.EmptyView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends BaseActivity<EmptyView, EmptyPresenter> implements EmptyView {


    @BindView(R.id.toolbar_text)
    TextView mToolbarText;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.frame)
    FrameLayout mFrame;
    @BindView(R.id.rb)
    RadioButton mRb;
    @BindView(R.id.rb2)
    RadioButton mRb2;
    @BindView(R.id.rb3)
    RadioButton mRb3;
    @BindView(R.id.rb4)
    RadioButton mRb4;
    @BindView(R.id.rb5)
    RadioButton mRb5;
    @BindView(R.id.rg)
    RadioGroup mRg;
    @BindView(R.id.nav)
    NavigationView mNav;
    @BindView(R.id.dl)
    DrawerLayout mDl;
    private HomeFragment homeFragment;
    private KnowledgeFragment knowledgeFragment;
    private NavigationFragment navigationFragment;
    private ProjectFragment projectFragment;
    private The_publicFragment the_publicFragment;


    private TextView mHander_login;
    private String mName;
    // 用来计算返回键的点击间隔时间
    private long exitTime = 0;


    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }



    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        mToolbar.setTitle("");
        mToolbarText.setText("首页");
        setSupportActionBar(mToolbar);

        View headerView = mNav.getHeaderView(0);
        mHander_login = headerView.findViewById(R.id.hander_login);
        mHander_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                 mName = (String) SpUtil.getParam(Constants.NAME, "");
                 mHander_login.setText(mName);
            }
        });

        //判断如果用户已经登陆过，直接显示用户名
        if ((boolean)SpUtil.getParam(Constants.LOGIN,false)){
            mHander_login.setText((String)SpUtil.getParam(Constants.USERNAME,"登录"));
        }
        initToolBar();

    }

    @Override
    protected void initData() {
        homeFragment = new HomeFragment();
        knowledgeFragment = new KnowledgeFragment();
        navigationFragment = new NavigationFragment();
        projectFragment = new ProjectFragment();
        the_publicFragment = new The_publicFragment();


        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frame, homeFragment);
        transaction.add(R.id.frame, knowledgeFragment);
        transaction.add(R.id.frame, the_publicFragment);
        transaction.add(R.id.frame, navigationFragment);
        transaction.add(R.id.frame, projectFragment);

        transaction.show(homeFragment).hide(knowledgeFragment).hide(the_publicFragment).hide(navigationFragment)
                .hide(projectFragment).commit();

    }

    private void initToolBar() {

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDl, mToolbar, R.string.open, R.string.close);
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.c_ffffff));
        mDl.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @OnClick({R.id.rb, R.id.rb2, R.id.rb3, R.id.rb4, R.id.rb5})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.rb:

                mToolbarText.setText("首页");

                getSupportFragmentManager().beginTransaction().show(homeFragment)
                        .hide(knowledgeFragment).hide(navigationFragment).hide(projectFragment)
                        .hide(the_publicFragment).commit();
                break;

            case R.id.rb2:
                mToolbarText.setText("知识体系");

                getSupportFragmentManager().beginTransaction().show(knowledgeFragment)
                        .hide(homeFragment).hide(navigationFragment).hide(projectFragment)
                        .hide(the_publicFragment).commit();

                break;
            case R.id.rb3:
                mToolbarText.setText("公众号");
                getSupportFragmentManager().beginTransaction()
                        .show(the_publicFragment)
                        .hide(homeFragment).hide(knowledgeFragment)
                        .hide(projectFragment).hide(navigationFragment).commit();

                break;
            case R.id.rb4:
                mToolbarText.setText("导航");

                getSupportFragmentManager().beginTransaction()
                        .show(navigationFragment)
                        .hide(homeFragment).hide(knowledgeFragment)
                        .hide(projectFragment).hide(the_publicFragment).commit();

                break;
            case R.id.rb5:

                mToolbarText.setText("项目");

                getSupportFragmentManager().beginTransaction()
                        .show(projectFragment)
                        .hide(homeFragment)
                        .hide(knowledgeFragment)
                        .hide(navigationFragment)
                        .hide(the_publicFragment).commit();
                break;

        }

    }
    @Override
    protected void initListener() {
        mNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.collect:
                        Intent intent = new Intent(MainActivity.this, CollectActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.todo:
                        Intent intent2 = new Intent(MainActivity.this, ToDoActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.night://切换夜间模式
                        UIModeUtil.changeModeUI(MainActivity.this);
                        break;
                    case R.id.setting:
                        Intent intent4 = new Intent(MainActivity.this, SettingActivity.class);
                        startActivity(intent4);
                        break;
                    case R.id.about:
                        Intent intent5 = new Intent(MainActivity.this, AboutActivity.class);
                        startActivity(intent5);
                        break;
                    case R.id.log_out:
                        Intent intent6 = new Intent(MainActivity.this, LogoutActivity.class);
                        startActivity(intent6);
                        break;
                }
                return false;
            }
        });
    }



    @Override

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME && event.getRepeatCount() ==0 ){

            dialog_Exit();

        }

        return false;

    }

    private void dialog_Exit() {

        new AlertDialog.Builder(this)
                .setTitle("确定退出wanAndroid吗")
                .setPositiveButton("确定",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which) {
                        android.os.Process.killProcess(android.os.Process
                                .myPid());
                    }
                })

                .setNegativeButton("取消",null)
                .create()
                .show();

    }
}
