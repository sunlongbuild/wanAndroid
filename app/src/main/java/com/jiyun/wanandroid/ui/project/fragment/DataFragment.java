package com.jiyun.wanandroid.ui.project.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.jiyun.wanandroid.entity.project.ListDataBean;
import com.jiyun.wanandroid.presenter.project.DataPresenter;
import com.jiyun.wanandroid.ui.project.activity.WebActivity;
import com.jiyun.wanandroid.ui.project.adapter.MyAdapter;
import com.jiyun.wanandroid.view.project.DataView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class DataFragment extends BaseFragment<DataView, DataPresenter> implements DataView {


    @BindView(R.id.lv)
    RecyclerView mLv;
    @BindView(R.id.smart)
    SmartRefreshLayout mSmart;
    private View view;
    private Unbinder unbinder;
    private ArrayList<ListDataBean.DataBean.DatasBean> datasBeans;
    private MyAdapter adapter;

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
        mSmart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initData();
                mSmart.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page=0;
                datasBeans.clear();;
                initData();
                mSmart.finishRefresh();
            }
        });

        adapter.setOnClicklistener(new MyAdapter.OnClicklistener() {
            @Override
            public void OnClick(int position, ListDataBean.DataBean.DatasBean datasBean) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url",datasBeans.get(position).getLink());
                intent.putExtra("title",datasBeans.get(position).getTitle());
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getData(page, cid);
    }

    @Override
    public void setData(ListDataBean bean) {
        datasBeans.addAll(bean.getData().getDatas());
        adapter.notifyDataSetChanged();
    }
}
