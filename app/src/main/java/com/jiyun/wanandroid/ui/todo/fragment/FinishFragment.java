package com.jiyun.wanandroid.ui.todo.fragment;


import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.api.todo.ToDoApiService;
import com.jiyun.wanandroid.base.BaseFragment;
import com.jiyun.wanandroid.base.Constants;
import com.jiyun.wanandroid.entity.todo.ToDoListBean;
import com.jiyun.wanandroid.entity.todo.ToDoUpdataBean;
import com.jiyun.wanandroid.net.BaseObserver;
import com.jiyun.wanandroid.net.HttpUtils;
import com.jiyun.wanandroid.net.RxUtils;
import com.jiyun.wanandroid.presenter.EmptyPresenter;
import com.jiyun.wanandroid.presenter.todo.ToDoListPresenter;
import com.jiyun.wanandroid.ui.todo.adapter.MyFinishAdapter;
import com.jiyun.wanandroid.ui.todo.adapter.MyToDoAdapter;
import com.jiyun.wanandroid.utils.SpUtil;
import com.jiyun.wanandroid.utils.ToastUtil;
import com.jiyun.wanandroid.view.EmptyView;
import com.jiyun.wanandroid.view.todo.ToDoListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class FinishFragment extends BaseFragment<EmptyView, EmptyPresenter> implements
        ToDoListView, AdapterView.OnItemClickListener {

    @BindView(R.id.lv)
    ListView mLv;
    private ArrayList<ToDoUpdataBean.DataBean> mList;
    private MyFinishAdapter mAdapter;
    private String mName;
    private String mPsw;
    private int position;
    private int mId;


    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_finish;
    }

    @Override
    protected void initView() {
        mList = new ArrayList<>();
        mAdapter = new MyFinishAdapter(getContext(), mList);
        mLv.setAdapter(mAdapter);

        mLv.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
        finishToDo(1);
    }

    @Override
    public void setToDoList(ToDoListBean toDoList) {
        if (toDoList == null && toDoList.getData().getDatas() == null) {
            TextView textView = new TextView(getContext());
            textView.setText("暂无数据");
        } else {

            //mList.addAll(toDoList.getData().getDatas());
            mAdapter.notifyDataSetChanged();
        }
    }
    public void finishToDo(int status) {
        mName = (String) SpUtil.getParam(Constants.NAME, "");
        mPsw = (String) SpUtil.getParam(Constants.PSW, "");
        ToDoApiService apiserver = HttpUtils.getInstance().getApiserver(ToDoApiService.baseUrl,
                ToDoApiService.class);
        Observable<ToDoUpdataBean> observable = apiserver.finishList(mId, status,
                "loginUserName=" + mName, "loginUserPassword=" + mPsw);
        observable.compose(RxUtils.<ToDoUpdataBean>rxObserableSchedulerHelper()).subscribe(new BaseObserver<ToDoUpdataBean>() {
            @Override
            public void onNext(ToDoUpdataBean toDoUpdataBean) {
                if (toDoUpdataBean.getErrorCode() == 0) {
                    mId = toDoUpdataBean.getData().getId();
                    ToastUtil.showShort(toDoUpdataBean.getErrorMsg());
                    mList.add(toDoUpdataBean.getData());
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void error(String msg) {

            }

            @Override
            protected void subscribe(Disposable d) {

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        this.position = position;
    }
}
