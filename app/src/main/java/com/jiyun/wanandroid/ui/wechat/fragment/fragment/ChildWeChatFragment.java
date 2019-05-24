package com.jiyun.wanandroid.ui.wechat.fragment.fragment;


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
import com.jiyun.wanandroid.entity.wechat.WeChatBean;
import com.jiyun.wanandroid.presenter.wechat.ChildWeChatPresenter;
import com.jiyun.wanandroid.ui.wechat.fragment.WeChatWebActivity;
import com.jiyun.wanandroid.ui.wechat.fragment.adapter.WeChatAdapter;
import com.jiyun.wanandroid.utils.ToastUtil;
import com.jiyun.wanandroid.view.wechat.ChildWeChatView;
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
public class ChildWeChatFragment extends BaseFragment<ChildWeChatView, ChildWeChatPresenter> implements ChildWeChatView {


    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.srl)
    SmartRefreshLayout mSrl;
    Unbinder unbinder;
    @BindView(R.id.btn_main)
    FloatingActionButton mBtnMain;
    private ArrayList<WeChatBean.DataBean.DatasBean> mlist;
    private WeChatAdapter weChatAdapter;
    private ImageView image;


    public ChildWeChatFragment() {
        // Required empty public constructor
    }

    int id;
    int page = 0;

    @SuppressLint("ValidFragment")
    public ChildWeChatFragment(int id) {
        this.id = id;
    }

    @Override
    protected ChildWeChatPresenter initPresenter() {
        return new ChildWeChatPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_child_we_chat;
    }

    @Override
    protected void initData() {
        //数据加载完成之前加载动画
        showLoading();
        mPresenter.getData(id, page);
    }

    @Override
    protected void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(linearLayoutManager);

        mlist = new ArrayList<>();
        weChatAdapter = new WeChatAdapter(getContext(), mlist);
        rv.setAdapter(weChatAdapter);


        mSrl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initData();
                mSrl.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                mlist.clear();
                initData();
                mSrl.finishRefresh();
            }
        });
        mSrl.setRefreshHeader(new DropBoxHeader(getContext()));
        mSrl.setRefreshFooter(new ClassicsFooter(getContext()));

        //点击悬浮按钮回到顶部并显示隐藏的toolbar与底部导航栏
        mBtnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rv.smoothScrollToPosition(0);
                getActivity().findViewById(R.id.toolbar).setVisibility(View.VISIBLE);
                getActivity().findViewById(R.id.rg).setVisibility(View.VISIBLE);
            }
        });
        initRecy();
    }
    //下拉隐藏底部导航栏
    @SuppressLint("ClickableViewAccessibility")
    private void initRecy() {
        rv.setOnTouchListener(new View.OnTouchListener() {
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
    public void setData(WeChatBean bean) {
        List<WeChatBean.DataBean.DatasBean> datas = bean.getData().getDatas();
        mlist.addAll(datas);
        weChatAdapter.notifyDataSetChanged();

        //数据加载完毕隐藏加载动画
        hideLoading();

        weChatAdapter.setMyOnItenClcik(new WeChatAdapter.MyOnItenClcik() {
            @Override
            public void setMyOnItenClcik(int position) {
                String link = mlist.get(position).getLink();
                Intent intent = new Intent(getContext(), WeChatWebActivity.class);
                intent.putExtra("link",link);
                startActivity(intent);
            }
        });
        weChatAdapter.setMyImageOnClickListener(new WeChatAdapter.MyImageOnClickListener() {
            @Override
            public void setImgOnClick(int position, ImageView view) {
                image = view;
                boolean collect = mlist.get(position).isCollect();
                if (collect) {
                    mPresenter.unCollect(mlist.get(position).getId());
                }else {
                    mPresenter.collect(mlist.get(position).getId());
                }
            }
        });
    }

    @Override
    public void collectSuccess(CollectBean collectBean) {
        image.setImageResource(R.mipmap.icon_xin);
        ToastUtil.showShort("收藏成功");
    }

    @Override
    public void unCollectSuccess(CollectBean collectBean) {
        image.setImageResource(R.mipmap.icon_uxin);
        ToastUtil.showShort("取消收藏");
    }


}
