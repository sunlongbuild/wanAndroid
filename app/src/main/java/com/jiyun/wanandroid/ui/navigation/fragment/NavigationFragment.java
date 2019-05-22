package com.jiyun.wanandroid.ui.navigation.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.base.BaseFragment;
import com.jiyun.wanandroid.entity.navigation.NavigationBean;
import com.jiyun.wanandroid.presenter.navigation.NavigationP;
import com.jiyun.wanandroid.ui.MainActivity;
import com.jiyun.wanandroid.ui.navigation.activity.FlowWebActivity;
import com.jiyun.wanandroid.ui.navigation.adapter.MyNavigationAdapter;
import com.jiyun.wanandroid.utils.ToastUtil;
import com.jiyun.wanandroid.view.navigation.NavigationV;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;
import retrofit2.http.HEAD;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationFragment extends BaseFragment<NavigationV, NavigationP> implements NavigationV {


    @BindView(R.id.tab)
    VerticalTabLayout mTab;
    @BindView(R.id.lv)
    RecyclerView mLv;
    @BindView(R.id.btn_main)
    FloatingActionButton btnMain;

    private ArrayList<NavigationBean.DataBean> list;

    private LinearLayoutManager manager;
    private ArrayList<NavigationBean.DataBean> dataBeans;
    private MyNavigationAdapter adapter;

    public NavigationFragment() {
        // Required empty public constructor
    }
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
        dataBeans = new ArrayList<>();
        manager = new LinearLayoutManager(getActivity());
        mLv.setLayoutManager(manager);

        //点击悬浮按钮回到顶部并显示隐藏的toolbar与底部导航栏
        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLv.smoothScrollToPosition(0);
                getActivity().findViewById(R.id.toolbar).setVisibility(View.VISIBLE);
                getActivity().findViewById(R.id.rg).setVisibility(View.VISIBLE);
            }
        });
        initRecy();
        adapter = new MyNavigationAdapter(getActivity(), dataBeans);
        mLv.setAdapter(adapter);

        adapter.setOnClickListener(new MyNavigationAdapter.OnClickListener() {
            @Override
            public void onClick(int position, int newPosition) {
                Intent intent = new Intent(getActivity(),FlowWebActivity.class);
                intent.putExtra("web",dataBeans.get(position).getArticles().get(newPosition).getLink());
                getActivity().startActivity(intent);
            }
        });
    }

    //下拉隐藏底部导航栏
    @SuppressLint("ClickableViewAccessibility")
    private void initRecy() {
        mLv.setOnTouchListener(new View.OnTouchListener() {
            public float mEndY;
            public float mStartY;

            @SuppressLint("RestrictedApi")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mStartY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mEndY = event.getY();
                        float v1 = mEndY - mStartY;
                        if (v1 > 0) {

                            //我这个是在fragment中的操作 这个是获取activity中的布局
                            getActivity().findViewById(R.id.toolbar).setVisibility(View.VISIBLE);
                            getActivity().findViewById(R.id.rg).setVisibility(View.VISIBLE);
                            btnMain.setVisibility(View.VISIBLE);
                        } else if (v1 < 0) {
                            getActivity().findViewById(R.id.rg).setVisibility(View.GONE);
                            getActivity().findViewById(R.id.toolbar).setVisibility(View.GONE);
                            btnMain.setVisibility(View.GONE);
                        }
                        break;
                }
                return gestureDetector.onTouchEvent(event);
            }
        });
    }
    GestureDetector gestureDetector = new GestureDetector(getContext(),
            new GestureDetector.OnGestureListener() {
                @Override
                public boolean onDown(MotionEvent e) {
                    return false;
                }
                @Override
                public void onShowPress(MotionEvent e) {
                }
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    // do something
                    return true;
                }
                @Override
                public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                    return false;
                }
                @Override
                public void onLongPress(MotionEvent e) {
                }
                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                    return false;
                }
            });
    @Override
    protected void initData() {
        mPresenter.getNavigation();
    }

    @Override
    protected void initListenter() {
            //RecyclerView和tab栏联动
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
        });


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
                        .setTextColor(Color.RED, Color.GRAY)
                        .build();
            }

            @Override
            public int getBackground(int position) {
                return 0;
            }
        });
        dataBeans.addAll(bean.getData());
        adapter.notifyDataSetChanged();
    }


}
