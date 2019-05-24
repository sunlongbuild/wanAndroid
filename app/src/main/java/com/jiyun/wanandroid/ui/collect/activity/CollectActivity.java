package com.jiyun.wanandroid.ui.collect.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;

import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.api.collect.OnClickListener;
import com.jiyun.wanandroid.base.BaseActivity;
import com.jiyun.wanandroid.entity.collect.CollectListBean;
import com.jiyun.wanandroid.presenter.collect.CollectPersenter;
import com.jiyun.wanandroid.ui.collect.adapter.Collect_Adapter;
import com.jiyun.wanandroid.ui.loginactivity.LoginActivity;
import com.jiyun.wanandroid.utils.SpUtil;
import com.jiyun.wanandroid.utils.ToastUtil;
import com.jiyun.wanandroid.view.collect.CollectView;

import java.util.ArrayList;

import butterknife.BindView;

public class CollectActivity extends BaseActivity<CollectView, CollectPersenter> implements CollectView {
    @BindView(R.id.collect_rv)
    RecyclerView mCollectRv;
    private int page = 0;
    private ArrayList<CollectListBean.DataBean.DatasBean> mBeans;
    private Collect_Adapter mCollectAdapter;

    @Override
    protected CollectPersenter initPresenter() {
        return new CollectPersenter();
    }

    @Override
    protected int getLayoutId() {
        String name = (String) SpUtil.getParam("name", "");
        Log.e("name",name);
        if (TextUtils.isEmpty(name)) {
           startActivity(new Intent(CollectActivity.this, LoginActivity.class));
           finish();
        } else {

        }
        return R.layout.activity_collect;
    }


    @Override
    protected void initView() {
        super.initView();
        mBeans = new ArrayList<>();
        mCollectRv.setLayoutManager(new LinearLayoutManager(this));
        mCollectAdapter = new Collect_Adapter(this, mBeans);
        mCollectRv.setAdapter(mCollectAdapter);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.CollectList(page,"loginUserName=1663527894","loginPassWord=qiaoruncheng");
    }

    @Override
    protected void initListener() {
        super.initListener();
        mCollectAdapter.setOnClickListener(new OnClickListener() {
            @Override
            public void OnClick(int postion) {
                mBeans.remove(postion);
                mCollectAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onSuccess(CollectListBean bean) {
        mBeans.addAll(bean.getData().getDatas());
        mCollectAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String msg) {
        ToastUtil.showShort(msg);
    }
}
