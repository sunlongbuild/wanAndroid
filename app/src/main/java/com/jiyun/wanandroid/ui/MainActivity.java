package com.jiyun.wanandroid.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.ui.home.fragment.HomeFragment;
import com.jiyun.wanandroid.ui.knowledge.fragment.KnowledgeFragment;
import com.jiyun.wanandroid.ui.navigation.fragment.NavigationFragment;
import com.jiyun.wanandroid.ui.project.fragment.ProjectFragment;
import com.jiyun.wanandroid.ui.wechat.fragment.The_publicFragment;

public class MainActivity extends AppCompatActivity {

    @butterknife.BindView(R.id.toolbar_text)
    android.widget.TextView mToolbarText;
    @butterknife.BindView(R.id.toolbar)
    Toolbar mToolbar;
    @butterknife.BindView(R.id.rb)
    android.widget.RadioButton mRb;
    @butterknife.BindView(R.id.rb2)
    android.widget.RadioButton mRb2;
    @butterknife.BindView(R.id.rb3)
    android.widget.RadioButton mRb3;
    @butterknife.BindView(R.id.rb4)
    android.widget.RadioButton mRb4;
    @butterknife.BindView(R.id.rb5)
    android.widget.RadioButton mRb5;
    @butterknife.BindView(R.id.rg)
    android.widget.RadioGroup mRg;
    @butterknife.BindView(R.id.nav)
    android.support.design.widget.NavigationView mNav;
    @butterknife.BindView(R.id.dl)
    android.support.v4.widget.DrawerLayout mDl;

    private HomeFragment homeFragment;
    private KnowledgeFragment knowledgeFragment;
    private NavigationFragment navigationFragment;
    private ProjectFragment projectFragment;
    private The_publicFragment the_publicFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        butterknife.ButterKnife.bind(this);


        mToolbar.setTitle("");
        mToolbarText.setText("首页");
        setSupportActionBar(mToolbar);
        android.support.v7.app.ActionBarDrawerToggle actionBarDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this, mDl, mToolbar, R.string.open, R.string.close);
        mDl.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        homeFragment = new HomeFragment();
        knowledgeFragment = new KnowledgeFragment();
        navigationFragment = new NavigationFragment();
        projectFragment = new ProjectFragment();
        the_publicFragment = new The_publicFragment();


        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frame, homeFragment);
        transaction.add(R.id.frame, knowledgeFragment);
        transaction.add(R.id.frame, navigationFragment);
        transaction.add(R.id.frame, projectFragment);
        transaction.add(R.id.frame, the_publicFragment);

        transaction.show(homeFragment).hide(knowledgeFragment).hide(navigationFragment).hide(projectFragment)
                .hide(the_publicFragment).commit();

    }


    @butterknife.OnClick({R.id.rb, R.id.rb2, R.id.rb3, R.id.rb4, R.id.rb5})
    public void onClick(android.view.View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.rb:
                mToolbarText.setText("首页");

                getSupportFragmentManager().beginTransaction().show(homeFragment).hide(knowledgeFragment).hide(navigationFragment).hide(projectFragment)
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
                        .show(navigationFragment)
                        .hide(homeFragment).hide(knowledgeFragment)
                        .hide(projectFragment).hide(the_publicFragment).commit();

                break;
            case R.id.rb4:
                mToolbarText.setText("导航");

                getSupportFragmentManager().beginTransaction()
                        .show(projectFragment)
                        .hide(homeFragment).hide(knowledgeFragment)
                        .hide(navigationFragment).hide(the_publicFragment).commit();

                break;
            case R.id.rb5:

                mToolbarText.setText("项目");

                getSupportFragmentManager().beginTransaction()
                        .show(the_publicFragment)
                        .hide(homeFragment)
                        .hide(knowledgeFragment)
                        .hide(navigationFragment)
                        .hide(projectFragment).commit();
                break;

        }
    }

}
