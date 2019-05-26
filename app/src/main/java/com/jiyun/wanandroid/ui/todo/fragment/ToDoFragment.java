package com.jiyun.wanandroid.ui.todo.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.base.BaseFragment;
import com.jiyun.wanandroid.base.Constants;
import com.jiyun.wanandroid.entity.todo.ToDoListBean;
import com.jiyun.wanandroid.presenter.todo.ToDoListPresenter;
import com.jiyun.wanandroid.ui.todo.activity.AddToDoActivity;
import com.jiyun.wanandroid.ui.todo.adapter.MyToDoAdapter;
import com.jiyun.wanandroid.utils.SpUtil;
import com.jiyun.wanandroid.utils.ToastUtil;
import com.jiyun.wanandroid.view.todo.ToDoListView;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ToDoFragment extends BaseFragment<ToDoListView, ToDoListPresenter> implements
        ToDoListView, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.lv)
    ListView mLv;
    private ArrayList<ToDoListBean.DataBean.DatasBean> mList;
    private MyToDoAdapter mAdapter;

    @Override
    protected ToDoListPresenter initPresenter() {
        return new ToDoListPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_to_do;
    }

    @Override
    protected void initView() {
        mList = new ArrayList<>();
        mAdapter = new MyToDoAdapter(getContext(), mList);
        mLv.setAdapter(mAdapter);

        mLv.setOnItemLongClickListener(this);
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtil.showShort(position + "");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        String name = (String) SpUtil.getParam(Constants.NAME, "");
        String psw = (String) SpUtil.getParam(Constants.PSW, "");
        mPresenter.getToDoList(0, "loginUserName=" + name, "loginUserPassword=" + psw);
    }

    @Override
    public void setToDoList(ToDoListBean toDoList) {
        mList.addAll(toDoList.getData().getDatas());
        for (int i = 0; i < toDoList.getData().getDatas().size(); i++) {
            if (toDoList.getData().getDatas().get(i).getDateStr() != null) {
                mTv.setText(toDoList.getData().getDatas().get(i).getDateStr());
            }
        }

        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onStop() {
        super.onStop();
        mList.clear();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    //长按编辑
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getContext(), AddToDoActivity.class);
        intent.putExtra("type", 1);
        startActivity(intent);

        return false;
    }
}
