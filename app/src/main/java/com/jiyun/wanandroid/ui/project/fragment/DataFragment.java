package com.jiyun.wanandroid.ui.project.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.base.BaseFragment;
import com.jiyun.wanandroid.entity.collect.CollectBean;
import com.jiyun.wanandroid.entity.project.ListDataBean;
import com.jiyun.wanandroid.presenter.project.DataPresenter;
import com.jiyun.wanandroid.ui.project.activity.WebActivity;
import com.jiyun.wanandroid.ui.project.adapter.MyAdapter;
import com.jiyun.wanandroid.utils.ToastUtil;
import com.jiyun.wanandroid.view.project.DataView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DataFragment extends BaseFragment<DataView, DataPresenter> implements DataView {


    @BindView(R.id.lv)
    RecyclerView mLv;
    @BindView(R.id.smart)
    SmartRefreshLayout mSmart;
    @BindView(R.id.btn_main)
    FloatingActionButton mBtnMain;

    private ArrayList<ListDataBean.DataBean.DatasBean> datasBeans;
    private MyAdapter adapter;
    private ImageView image;

    public DataFragment() {
        // Required empty public constructor
    }

    private int cid;
    private int page;

    @SuppressLint("ValidFragment")
    public DataFragment(int cid) {
        this.cid = cid;
    }
/*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_data, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }*/

    @Override
    protected DataPresenter initPresenter() {
        return new DataPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_data;
    }

    @Override
    protected void initView() {
        datasBeans = new ArrayList<>();
        adapter = new MyAdapter(getActivity(), datasBeans);
        mLv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mLv.setAdapter(adapter);

        //点击悬浮按钮回到顶部并显示隐藏的toolbar与底部导航栏
        mBtnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLv.smoothScrollToPosition(0);
                getActivity().findViewById(R.id.toolbar).setVisibility(View.VISIBLE);
                getActivity().findViewById(R.id.rg).setVisibility(View.VISIBLE);
            }
        });
        initRecy();


        mSmart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initData();
                mSmart.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                datasBeans.clear();
                ;
                initData();
                mSmart.finishRefresh();
            }
        });

        adapter.setOnClicklistener(new MyAdapter.OnClicklistener() {
            @Override
            public void OnClick(int position, ListDataBean.DataBean.DatasBean datasBean) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url", datasBeans.get(position).getLink());
                intent.putExtra("title", datasBeans.get(position).getTitle());
                intent.putExtra("url",datasBeans.get(position).getLink());
                //intent.putExtra("title",datasBeans.get(position).getTitle());
                getActivity().startActivity(intent);
            }
        });
        adapter.setMyImageOnClickListener(new MyAdapter.MyImageOnClickListener() {
            @Override
            public void setImgOnClick(int position, ImageView view) {
                image = view;
                boolean collect = datasBeans.get(position).isCollect();
                if (collect) {
                    ToastUtil.showShort("1");
                    mPresenter.unCollect(datasBeans.get(position).getId());
                }else {
                    mPresenter.collect(datasBeans.get(position).getId());
                    ToastUtil.showShort("2");
                }
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
                            mBtnMain.setVisibility(View.VISIBLE);
                        } else if (v1 < 0) {
                            getActivity().findViewById(R.id.rg).setVisibility(View.GONE);
                            getActivity().findViewById(R.id.toolbar).setVisibility(View.GONE);
                            mBtnMain.setVisibility(View.GONE);
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
        //数据回来之前加载动画
        showLoading();
        mPresenter.getData(page, cid);
    }

    @Override
    public void setData(ListDataBean bean) {
        datasBeans.addAll(bean.getData().getDatas());
        adapter.notifyDataSetChanged();
        //数据加载完毕隐藏加载动画
        hideLoading();
    }

    @Override
    public void collectSeccess(CollectBean collectBean) {
        ToastUtil.showShort("收藏成功");
        image.setImageResource(R.mipmap.icon_xin);
//        adapter.notifyDataSetChanged();
    }

    @Override
    public void collectFail(String string) {
        ToastUtil.showShort(string);

    }

    @Override
    public void unCollectSeccess(CollectBean collectBean) {
        ToastUtil.showShort("取消收藏");
        image.setImageResource(R.mipmap.icon_uxin);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void unCollectFail(String string) {
        ToastUtil.showShort(string);
    }


}
