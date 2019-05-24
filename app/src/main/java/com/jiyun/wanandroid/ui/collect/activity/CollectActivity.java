package com.jiyun.wanandroid.ui.collect.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.api.collect.OnClickListener;
import com.jiyun.wanandroid.base.BaseActivity;
import com.jiyun.wanandroid.entity.collect.CollectBean;
import com.jiyun.wanandroid.entity.collect.CollectListBean;
import com.jiyun.wanandroid.presenter.collect.CollectPersenter;
import com.jiyun.wanandroid.ui.collect.adapter.Collect_Adapter;
import com.jiyun.wanandroid.ui.login.LoginActivity;
import com.jiyun.wanandroid.utils.SpUtil;
import com.jiyun.wanandroid.utils.ToastUtil;
import com.jiyun.wanandroid.view.collect.CollectView;
import com.just.agentweb.LogUtils;

import java.util.ArrayList;

import butterknife.BindView;

public class CollectActivity extends BaseActivity<CollectView, CollectPersenter> implements CollectView {
    @BindView(R.id.collect_rv)
    RecyclerView mCollectRv;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.txt_toolbar)
    TextView mTxtToolbar;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
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
        Log.e("name", name);
        if (TextUtils.isEmpty(name)) {
            startActivity(new Intent(CollectActivity.this, LoginActivity.class));
            finish();
        } else {
        }
        return R.layout.activity_collect;
    }


    @Override
    protected void initView() {
        StatusBarUtil.setLightMode(this);
        mBeans = new ArrayList<>();
        mCollectRv.setLayoutManager(new LinearLayoutManager(this));
        mCollectAdapter = new Collect_Adapter(this, mBeans);
        mCollectRv.setAdapter(mCollectAdapter);

    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.CollectList(page);
    }

    @Override
    protected void initListener() {
        super.initListener();
        mCollectAdapter.setOnClickListener(new OnClickListener() {
            @Override
            public void OnClick(int postion) {
                CollectListBean.DataBean.DatasBean datasBean = mCollectAdapter.mDatasBeans.get(postion);
                int originId = datasBean.getOriginId();
                int id = datasBean.getId();
                if (originId == -1) {
                    mPresenter.unCollect_Two(id);
                } else {
                    mPresenter.unCollect(originId);
                }
                mBeans.remove(postion);
                mCollectAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onSuccess(CollectListBean bean) {
        if (mBeans.size() == 0) {
            ToastUtil.showShort("暂无收藏数据");
        }
        mCollectAdapter.setData(bean);
        LogUtils.e("collectlist", bean.toString());
    }

    @Override
    public void onFail(String msg) {
        ToastUtil.showShort(msg);
    }

    @Override
    public void unCollectSuccess(CollectBean collectBean) {
        ToastUtil.showShort("取消收藏");
    }
}
