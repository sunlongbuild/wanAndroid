package com.jiyun.wanandroid.ui.knowledge.fragment.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.base.BaseFragment;
import com.jiyun.wanandroid.entity.knowledge.KaiFaHuanJingBean;
import com.jiyun.wanandroid.presenter.knowledge.KaiFaHuanJingFragmentPresenter;
import com.jiyun.wanandroid.ui.knowledge.fragment.activity.KaiFaHuanJingShowActivity;
import com.jiyun.wanandroid.ui.knowledge.fragment.adapter.RvKaiFaHuanJingAdapter;
import com.jiyun.wanandroid.view.knowledge.KaiFaHuanJingFragmentView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class KaiFaHuanJingFragment extends BaseFragment<KaiFaHuanJingFragmentView, KaiFaHuanJingFragmentPresenter> implements KaiFaHuanJingFragmentView {


    @BindView(R.id.rv)
    RecyclerView mRv;
    private View view;
    private Unbinder unbinder;
    private ArrayList<KaiFaHuanJingBean.DataBean.DatasBean> mlist;
    private RvKaiFaHuanJingAdapter rvKaiFaHuanJingAdapter;

    public KaiFaHuanJingFragment() {
        // Required empty public constructor
    }

    int page;
    int id;

    @SuppressLint("ValidFragment")
    public KaiFaHuanJingFragment(int id) {
        this.id = id;
    }


    @Override
    protected KaiFaHuanJingFragmentPresenter initPresenter() {
        return new KaiFaHuanJingFragmentPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_kai_fa_huan_jing;
    }

    @Override
    protected void initData() {
        mPresenter.getData(page, id);
    }

    @Override
    protected void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRv.setLayoutManager(linearLayoutManager);
        mlist = new ArrayList<>();
        rvKaiFaHuanJingAdapter = new RvKaiFaHuanJingAdapter(getContext(), mlist);
        mRv.setAdapter(rvKaiFaHuanJingAdapter);
    }

    @Override
    public void setData(KaiFaHuanJingBean bean) {

        List<KaiFaHuanJingBean.DataBean.DatasBean> datas = bean.getData().getDatas();
        mlist.addAll(datas);
        rvKaiFaHuanJingAdapter.notifyDataSetChanged();

        rvKaiFaHuanJingAdapter.setMyOnItenClcik(new RvKaiFaHuanJingAdapter.MyOnItenClcik() {
            @Override
            public void setMyOnItenClcik(int position) {
                Intent intent = new Intent(getContext(), KaiFaHuanJingShowActivity.class);
                intent.putExtra("link",mlist.get(position).getLink());
                startActivity(intent);
            }
        });
    }


}
