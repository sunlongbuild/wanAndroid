package com.jiyun.wanandroid.ui.todo.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.base.BaseFragment;
import com.jiyun.wanandroid.presenter.EmptyPresenter;
import com.jiyun.wanandroid.ui.project.adapter.MyAdapter;
import com.jiyun.wanandroid.ui.todo.adapter.MyToDoAdapter;
import com.jiyun.wanandroid.view.EmptyView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ToDoFragment extends BaseFragment<EmptyView, EmptyPresenter> implements EmptyView {

    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.lv)
    ListView mLv;
    private ArrayList<String> mList;

    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_to_do;
    }

    @Override
    protected void initView() {
        mList = new ArrayList<>();
        mList.add("我是小可爱(●'◡'●)");
        MyToDoAdapter adapter = new MyToDoAdapter(getContext(), mList);
        mLv.setAdapter(adapter);

    }
}
