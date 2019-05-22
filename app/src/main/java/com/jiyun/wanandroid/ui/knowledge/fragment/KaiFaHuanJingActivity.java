package com.jiyun.wanandroid.ui.knowledge.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.base.BaseActivity;
import com.jiyun.wanandroid.entity.knowledge.KnowledgeBean;
import com.jiyun.wanandroid.presenter.knowledge.KaiFaHuanJingPresenter;
import com.jiyun.wanandroid.ui.knowledge.fragment.adapter.VpKaiFaXuanXiangAdapter;
import com.jiyun.wanandroid.ui.knowledge.fragment.fragment.KaiFaHuanJingFragment;
import com.jiyun.wanandroid.view.knowledge.KaiFaHuanJingView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class KaiFaHuanJingActivity extends BaseActivity<KaiFaHuanJingView, KaiFaHuanJingPresenter> implements KaiFaHuanJingView {

    @BindView(R.id.txt_toolbar)
    TextView mTxtToolbar;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.vp)
    ViewPager mVp;
    private ArrayList<Fragment> fragments;
    private int mPosition;

    @Override
    protected KaiFaHuanJingPresenter initPresenter() {
        return new KaiFaHuanJingPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_kai_fa_huan_jing;
    }

    @Override
    protected void initView() {
        StatusBarUtil.setLightMode(this);
        mPosition = getIntent().getIntExtra("position", 0);

    }

    @Override
    protected void initData() {
        mPresenter.getHierarchy();
    }

    @Override
    public void androidHierarchy(List<KnowledgeBean.DataBean> data) {
        fragments = new ArrayList<>();
        KnowledgeBean.DataBean dataBean = data.get(mPosition);
        String name = data.get(mPosition).getName();
        mTxtToolbar.setText(name);
        List<KnowledgeBean.DataBean.ChildrenBean> children = dataBean.getChildren();
        for (int i = 0; i <children.size() ; i++) {
            mTab.addTab(mTab.newTab().setText(children.get(i).getName()));
            fragments.add(new KaiFaHuanJingFragment(children.get(i).getId()));

            VpKaiFaXuanXiangAdapter vpKaiFaXuanXiangAdapter = new VpKaiFaXuanXiangAdapter(getSupportFragmentManager(), fragments);

            mVp.setAdapter(vpKaiFaXuanXiangAdapter);

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
}
