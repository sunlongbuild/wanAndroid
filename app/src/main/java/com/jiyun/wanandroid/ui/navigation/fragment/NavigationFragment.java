package com.jiyun.wanandroid.ui.navigation.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.base.BaseFragment;
import com.jiyun.wanandroid.entity.navigation.NavigationBean;
import com.jiyun.wanandroid.presenter.navigation.NavigationP;
import com.jiyun.wanandroid.view.navigation.NavigationV;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationFragment extends BaseFragment<NavigationV, NavigationP> implements NavigationV {


    @BindView(R.id.tab)
    VerticalTabLayout mTab;
    @BindView(R.id.lv)
    RecyclerView mLv;
    private ArrayList<NavigationBean.DataBean> list;
    private View view;
    private LinearLayoutManager manager;

    public NavigationFragment() {
        // Required empty public constructor
    }

/*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_navigation, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }*/

    @Override
    protected NavigationP initPresenter() {
        return new NavigationP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation;
    }

    @Override
    protected void initView() {
        manager = new LinearLayoutManager(getActivity());
        mLv.setLayoutManager(manager);
    }

    @Override
    protected void initData() {
        mPresenter.getNavigation();
    }

    @Override
    public void getData(NavigationBean bean) {
        list = new ArrayList<>();
        final List<NavigationBean.DataBean> data = bean.getData();
        mTab.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return data.size();
            }

            @Override
            public ITabView.TabBadge getBadge(int position) {
                return null;
            }

            @Override
            public ITabView.TabIcon getIcon(int position) {
                return null;
            }

            @Override
            public ITabView.TabTitle getTitle(int position) {
                return new ITabView.TabTitle.Builder()
                        .setContent(data.get(position).getName())
                        .setTextColor(Color.RED,Color.GRAY)
                        .build();
            }

            @Override
            public int getBackground(int position) {
                return 0;
            }
        });
     /*   //RecyclerView和tab栏联动
        mLv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mTab.setTabSelected(manager.findFirstVisibleItemPosition());
            }
        });
        //tab栏和RecyclerView联动
        mTab.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                manager.scrollToPositionWithOffset(position,0);
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        });*/
    }

}
