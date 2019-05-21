package com.jiyun.wanandroid.ui.project.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.base.BaseFragment;
import com.jiyun.wanandroid.entity.project.TabBean;
import com.jiyun.wanandroid.presenter.project.ProjectPresenter;
import com.jiyun.wanandroid.ui.project.adapter.MyPageAdapter;
import com.jiyun.wanandroid.view.project.ProjectView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectFragment extends BaseFragment<ProjectView, ProjectPresenter> implements ProjectView {


    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.vp)
    ViewPager mVp;
    private ArrayList<Fragment> list;
    String page;
    public ProjectFragment() {
        // Required empty public constructor
    }


 /*   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        unbinder = ButterKnife.bind(this, inflater.inflate(R.layout.fragment_project, container, false));
        return inflater.inflate(R.layout.fragment_project, container, false);
    }*/

    @Override
    protected ProjectPresenter initPresenter() {
        return new ProjectPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();

    }

    @Override
    protected void initData() {
        mPresenter.getProject();
    }

    @Override
    public void getData(TabBean bean) {
        List<TabBean.DataBean> data = bean.getData();
        for (int i = 0; i <data.size() ; i++) {
            list.add(new DataFragment(data.get(i).getId()));
            mTab.addTab(mTab.newTab().setText(data.get(i).getName()));
        }
        MyPageAdapter adapter = new MyPageAdapter(getChildFragmentManager(),list);
        mVp.setAdapter(adapter);
        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mVp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mVp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTab));
    }
}
