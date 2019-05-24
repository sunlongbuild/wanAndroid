package com.jiyun.wanandroid.ui.home.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.base.BaseFragment;
import com.jiyun.wanandroid.entity.home.HomeBannerBean;
import com.jiyun.wanandroid.entity.home.HomeRevBean;
import com.jiyun.wanandroid.entity.home.HomeTopBean;
import com.jiyun.wanandroid.presenter.home.HomePresenter;
import com.jiyun.wanandroid.ui.home.BannerShowActivity;
import com.jiyun.wanandroid.ui.home.activity.HomeShowActivity;
import com.jiyun.wanandroid.ui.home.adapter.RvHomeAdapter;
import com.jiyun.wanandroid.view.home.HomeView;
import com.scwang.smartrefresh.header.DropBoxHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<HomeView, HomePresenter> implements HomeView {


    @BindView(R.id.rv_home)
    RecyclerView mRvHome;
    @BindView(R.id.btn_main)
    FloatingActionButton mBtnMain;
    @BindView(R.id.srl)
    SmartRefreshLayout mSrl;


    private int page = 0;
    private ArrayList<HomeRevBean.DataBean.DatasBean> mlist;
    private ArrayList<HomeBannerBean.DataBean> bannerlsit;
    private RvHomeAdapter rvHomeAdapter;



    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    protected HomePresenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

        StatusBarUtil.setLightMode(getActivity());


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRvHome.setLayoutManager(linearLayoutManager);

        mlist = new ArrayList<>();
        bannerlsit = new ArrayList<>();

        rvHomeAdapter = new RvHomeAdapter(getContext(), mlist, bannerlsit);
        mRvHome.setAdapter(rvHomeAdapter);


        mSrl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initData();
                mSrl.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                bannerlsit.clear();
                mlist.clear();
                initData();

                mSrl.finishRefresh();
            }
        });
        mSrl.setRefreshHeader(new DropBoxHeader(getContext()));
        mSrl.setRefreshFooter(new ClassicsFooter(getContext()));

        //点击悬浮按钮回到顶部并显示隐藏的toolbar与底部导航栏
        mBtnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRvHome.smoothScrollToPosition(0);
                getActivity().findViewById(R.id.toolbar).setVisibility(View.VISIBLE);
                getActivity().findViewById(R.id.rg).setVisibility(View.VISIBLE);
            }
        });
        initRecy();

    }

    //下拉隐藏底部导航栏
    @SuppressLint("ClickableViewAccessibility")
    private void initRecy() {
        mRvHome.setOnTouchListener(new View.OnTouchListener() {
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
                            mBtnMain.setVisibility(View.VISIBLE);
                        } else if (v1 < 0) {
                            getActivity().findViewById(R.id.rg).setVisibility(View.GONE);
                            getActivity().findViewById(R.id.toolbar).setVisibility(View.GONE);
                            mBtnMain.setVisibility(View.GONE);
                        }
                        break;
                    case MotionEvent.ACTION_UP:

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
        //数据回来之前加载动画
        showLoading();
        mPresenter.getBanner();
        mPresenter.getRv(page);
        mPresenter.gettop();
    }

    @OnClick(R.id.btn_main)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_main:
                break;
        }
    }

    @Override
    public void setBanner(final HomeBannerBean bean) {

        List<HomeBannerBean.DataBean> data = bean.getData();
        bannerlsit.addAll(data);
        rvHomeAdapter.notifyDataSetChanged();
        //数据加载完毕隐藏
        hideLoading();
        rvHomeAdapter.setMyBannerOnClickListener(new RvHomeAdapter.MyBannerOnClickListener() {
            @Override
            public void setMyBannerOnClickListener(int position) {
                String url = bannerlsit.get(position).getUrl();
                Intent intent = new Intent(getContext(), BannerShowActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);

            }
        });

    }

    @Override
    public void setRv(HomeRevBean bean) {
        List<HomeRevBean.DataBean.DatasBean> datas = bean.getData().getDatas();
        mlist.addAll(datas);
        rvHomeAdapter.notifyDataSetChanged();
        rvHomeAdapter.setMyItemOnClickListener(new RvHomeAdapter.MyItemOnClickListener() {

            @Override
            public void setMyItemOnClickListener(int position) {
                String link = mlist.get(position - 1).getLink();
                Intent intent = new Intent(getContext(), HomeShowActivity.class);
                intent.putExtra("link", link);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setTop(HomeTopBean bean) {



    }


}
