package com.jiyun.wanandroid.ui.knowledge.fragment;


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

import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.base.BaseFragment;
import com.jiyun.wanandroid.entity.knowledge.KnowledgeBean;
import com.jiyun.wanandroid.presenter.knowledge.KnowledgePresenter;
import com.jiyun.wanandroid.ui.knowledge.adapter.RvKnowledgeAdapter;
import com.jiyun.wanandroid.view.knowledge.KnowledgeView;
import com.scwang.smartrefresh.header.DropBoxHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * A simple {@link Fragment} subclass.
 */
public class KnowledgeFragment extends BaseFragment<KnowledgeView, KnowledgePresenter> implements KnowledgeView {


    @BindView(R.id.knowledge_rv)
    RecyclerView knowledgeRv;
    @BindView(R.id.btn_hierarchy)
    FloatingActionButton mBtnHierarchy;
    @BindView(R.id.srl)
    SmartRefreshLayout mSrl;

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
            public void setMyClickListener(int position, KnowledgeBean.DataBean dataBean) {
                Intent intent = new Intent(getActivity(), KaiFaHuanJingActivity.class);
                List<KnowledgeBean.DataBean.ChildrenBean> children = dataBean.getChildren();
                intent.putExtra("position", position);
                intent.putExtra("bean", dataBean);
                getActivity().startActivity(intent);
            }
        });


        mSrl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mlist.clear();
                initData();
                mSrl.finishRefresh();
            }
        });
        mSrl.setRefreshHeader(new DropBoxHeader(getContext()));
        mSrl.setRefreshFooter(new ClassicsFooter(getContext()));

        //点击悬浮按钮回到顶部并显示隐藏的toolbar与底部导航栏
        mBtnHierarchy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                knowledgeRv.smoothScrollToPosition(0);
                getActivity().findViewById(R.id.toolbar).setVisibility(View.VISIBLE);
                getActivity().findViewById(R.id.rg).setVisibility(View.VISIBLE);
            }
        });
        initRecy();
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

    @SuppressLint("ClickableViewAccessibility")
    private void initRecy() {
        knowledgeRv.setOnTouchListener(new View.OnTouchListener() {
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
                            mBtnHierarchy.setVisibility(View.VISIBLE);
                            //这个就是当前页面的头布局id
                            //.setVisibility(View.VISIBLE);
                        } else if (v1 < 0) {
                            getActivity().findViewById(R.id.rg).setVisibility(View.GONE);
                            getActivity().findViewById(R.id.toolbar).setVisibility(View.GONE);
                            mBtnHierarchy.setVisibility(View.GONE);
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
}
