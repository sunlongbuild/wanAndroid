package com.jiyun.wanandroid.ui.knowledge.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.base.BaseFragment;
import com.jiyun.wanandroid.entity.knowledge.KnowledgeBean;
import com.jiyun.wanandroid.presenter.knowledge.KnowledgePresenter;
import com.jiyun.wanandroid.ui.knowledge.fragment.adapter.RvKnowledgeAdapter;
import com.jiyun.wanandroid.view.knowledge.KnowledgeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class KnowledgeFragment extends BaseFragment<KnowledgeView, KnowledgePresenter> implements KnowledgeView {


    @BindView(R.id.main_name)
    TextView mainName;
    @BindView(R.id.knowledge_rv)
    RecyclerView knowledgeRv;
    Unbinder unbinder;
    private ArrayList<KnowledgeBean.DataBean> mlist;
    private RvKnowledgeAdapter rvKnowledgeAdapter;


    public KnowledgeFragment() {
        // Required empty public constructor
    }

    @Override
    protected KnowledgePresenter initPresenter() {
        return new KnowledgePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledge;
    }

    @Override
    protected void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        knowledgeRv.setLayoutManager(linearLayoutManager);
        mlist = new ArrayList<>();
        rvKnowledgeAdapter = new RvKnowledgeAdapter(getContext(), mlist);
        knowledgeRv.setAdapter(rvKnowledgeAdapter);


       rvKnowledgeAdapter.setMyClickListener(new RvKnowledgeAdapter.MyClickListener() {
           @Override
           public void setMyClickListener(int position,KnowledgeBean.DataBean dataBean) {
               Intent intent = new Intent(getActivity(), KaiFaHuanJingActivity.class);
               List<KnowledgeBean.DataBean.ChildrenBean> children = dataBean.getChildren();
               intent.putExtra("position",position);
               intent.putExtra("bean",dataBean);
               getActivity().startActivity(intent);
           }
       });

    }

    @Override
    protected void initData() {
        mPresenter.getData();
    }

    @Override
    public void setData(final KnowledgeBean bean) {

        final List<KnowledgeBean.DataBean> data = bean.getData();
        mlist.addAll(data);
        rvKnowledgeAdapter.notifyDataSetChanged();

    }


}
