package com.jiyun.wanandroid.ui.knowledge.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.base.BaseFragment;
import com.jiyun.wanandroid.base.Constants;
import com.jiyun.wanandroid.entity.collect.CollectBean;
import com.jiyun.wanandroid.entity.knowledge.KaiFaHuanJingBean;
import com.jiyun.wanandroid.presenter.knowledge.KaiFaHuanJingFragmentPresenter;
import com.jiyun.wanandroid.ui.knowledge.activity.KaiFaHuanJingShowActivity;
import com.jiyun.wanandroid.ui.knowledge.adapter.RvKaiFaHuanJingAdapter;
import com.jiyun.wanandroid.ui.login.LoginActivity;
import com.jiyun.wanandroid.utils.SpUtil;
import com.jiyun.wanandroid.utils.ToastUtil;
import com.jiyun.wanandroid.view.knowledge.KaiFaHuanJingFragmentView;
import com.scwang.smartrefresh.header.DropBoxHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

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
    @BindView(R.id.btn_main)
    FloatingActionButton btnMain;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    private ArrayList<KaiFaHuanJingBean.DataBean.DatasBean> mlist;
    private RvKaiFaHuanJingAdapter rvKaiFaHuanJingAdapter;
    private ImageView Image;

    public KaiFaHuanJingFragment() {
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
        //数据加载完成之前加载动画
        showLoading();
        mPresenter.getData(page, id);
    }

    @Override
    protected void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRv.setLayoutManager(linearLayoutManager);
        mlist = new ArrayList<>();
        rvKaiFaHuanJingAdapter = new RvKaiFaHuanJingAdapter(getContext(), mlist);
        mRv.setAdapter(rvKaiFaHuanJingAdapter);

        srl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initData();
                srl.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                mlist.clear();
                initData();

                srl.finishRefresh();
            }
        });
        srl.setRefreshHeader(new DropBoxHeader(getContext()));
        srl.setRefreshFooter(new ClassicsFooter(getContext()));


        //点击悬浮按钮回到顶部并显示隐藏的toolbar与底部导航栏
        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRv.smoothScrollToPosition(0);
                getActivity().findViewById(R.id.toolbarr).setVisibility(View.VISIBLE);
              //  getActivity().findViewById(R.id.rg).setVisibility(View.VISIBLE);
            }
        });
        initRecy();
    }


    //下拉隐藏底部导航栏
    @SuppressLint("ClickableViewAccessibility")
    private void initRecy() {
        mRv.setOnTouchListener(new View.OnTouchListener() {
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
                            getActivity().findViewById(R.id.toolbarr).setVisibility(View.VISIBLE);
                         //   getActivity().findViewById(R.id.rg).setVisibility(View.VISIBLE);
                            btnMain.setVisibility(View.VISIBLE);
                        } else if (v1 < 0) {
                          //  getActivity().findViewById(R.id.rg).setVisibility(View.GONE);
                            getActivity().findViewById(R.id.toolbarr).setVisibility(View.GONE);
                            btnMain.setVisibility(View.GONE);
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
    protected void initListenter() {
        super.initListenter();
        rvKaiFaHuanJingAdapter.setMyImageOnClickListener(new RvKaiFaHuanJingAdapter.MyImageOnClickListener() {
            @Override
            public void setImgOnClick(int position, ImageView view) {
                Image = view;
                String param = (String) SpUtil.getParam(Constants.NAME, null);
                if (TextUtils.isEmpty(param)) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }else {
                    boolean collect = mlist.get(position).isCollect();
                    if (collect) {
                        mPresenter.unCollect(mlist.get(position).getId());

                    }else {
                        mPresenter.collect(mlist.get(position).getId());
                    }
                }
            }
        });
    }

    @Override
    public void setData(KaiFaHuanJingBean bean) {

        List<KaiFaHuanJingBean.DataBean.DatasBean> datas = bean.getData().getDatas();
        mlist.addAll(datas);
        rvKaiFaHuanJingAdapter.notifyDataSetChanged();
        //数据加载完毕隐藏加载动画
        hideLoading();
        rvKaiFaHuanJingAdapter.setMyOnItenClcik(new RvKaiFaHuanJingAdapter.MyOnItenClcik() {
            @Override
            public void setMyOnItenClcik(int position) {
                Intent intent = new Intent(getContext(), KaiFaHuanJingShowActivity.class);
                intent.putExtra("link", mlist.get(position).getLink());
                startActivity(intent);
            }
        });
    }

    @Override
    public void collectSuccess(CollectBean collectBean) {
        Image.setImageResource(R.mipmap.icon_xin);
        ToastUtil.showShort("收藏成功");
    }

    @Override
    public void unCollectSuccess(CollectBean collectBean) {
        Image.setImageResource(R.mipmap.icon_uxin);
        ToastUtil.showShort("取消收藏");
    }
}
