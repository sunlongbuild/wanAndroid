package com.jiyun.wanandroid.ui.wechat.fragment;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.base.BaseFragment;
import com.jiyun.wanandroid.entity.wechat.WeChatTabBean;
import com.jiyun.wanandroid.presenter.wechat.WeChatPresenter;
import com.jiyun.wanandroid.ui.knowledge.adapter.VpKaiFaXuanXiangAdapter;
import com.jiyun.wanandroid.ui.wechat.fragment.fragment.ChildWeChatFragment;
import com.jiyun.wanandroid.view.wechat.WeChatView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * A simple {@link Fragment} subclass.
 */
public class The_publicFragment extends BaseFragment<WeChatView, WeChatPresenter> implements WeChatView {
    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.vp)
    ViewPager mVp;

    private VpKaiFaXuanXiangAdapter vpKaiFaXuanXiangAdapter;
    private ArrayList<Fragment> fragments;

    public The_publicFragment() {
        // Required empty public constructor
    }

    @Override
    protected WeChatPresenter initPresenter() {
        return new WeChatPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_the_public;
    }

    @Override
    protected void initData() {
        mPresenter.getTabData();
    }

    @Override
    protected void initView() {
        fragments = new ArrayList<>();
        vpKaiFaXuanXiangAdapter = new VpKaiFaXuanXiangAdapter(getChildFragmentManager(), fragments);
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

    @Override
    public void setTab(WeChatTabBean bean) {
        List<WeChatTabBean.DataBean> data = bean.getData();
        for (int i = 0; i < data.size(); i++) {
            mTab.addTab(mTab.newTab().setText(data.get(i).getName()));
            fragments.add(new ChildWeChatFragment(data.get(i).getId()));
        }
        vpKaiFaXuanXiangAdapter.notifyDataSetChanged();
    }


}
