package com.jiyun.wanandroid.ui.todo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.api.todo.ToDoApiService;
import com.jiyun.wanandroid.base.Constants;
import com.jiyun.wanandroid.entity.todo.ToDoDeleteBean;
import com.jiyun.wanandroid.entity.todo.ToDoListBean;
import com.jiyun.wanandroid.net.BaseObserver;
import com.jiyun.wanandroid.net.HttpUtils;
import com.jiyun.wanandroid.net.RxUtils;
import com.jiyun.wanandroid.utils.SpUtil;
import com.jiyun.wanandroid.utils.ToastUtil;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import java.util.ArrayList;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * Created by $sl on 2019/5/22 14:17.
 */
public class MyToDoAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<ToDoListBean.DataBean.DatasBean> mList;

    public MyToDoAdapter(Context context, ArrayList<ToDoListBean.DataBean.DatasBean> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_todo, null);
            viewHolder.tv_title = convertView.findViewById(R.id.tv_title);
            viewHolder.tv_content = convertView.findViewById(R.id.tv_content);
            viewHolder.mSwipeMenuLayout = convertView.findViewById(R.id.swipeMenuLayout);
            viewHolder.mTodoDelete = convertView.findViewById(R.id.todo_delete);
            viewHolder.mTodoRead = convertView.findViewById(R.id.todo_read);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_title.setText(mList.get(position).getTitle());
        viewHolder.tv_content.setText(mList.get(position).getContent());
        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.mTodoDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.remove(position);
                deleteToDo(position);
                notifyDataSetChanged();
                finalViewHolder.mSwipeMenuLayout.quickClose();//关闭左滑删除
            }
        });

        return convertView;
    }

    class ViewHolder {
        public TextView tv_title;
        public TextView tv_content;
        @BindView(R.id.todo_read)
        Button mTodoRead;
        @BindView(R.id.todo_delete)
        Button mTodoDelete;
        @BindView(R.id.swipeMenuLayout)
        SwipeMenuLayout mSwipeMenuLayout;
    }

    public void deleteToDo(int position) {
        Integer id = (Integer) SpUtil.getParam(Constants.todoid, 0);
        String name = (String) SpUtil.getParam(Constants.NAME, "");
        String psw = (String) SpUtil.getParam(Constants.PSW, "");
        ToDoApiService apiserver = HttpUtils.getInstance().getApiserver(ToDoApiService.baseUrl,
                ToDoApiService.class);
        Observable<ToDoDeleteBean> observable = apiserver.deleteList(id, "loginUserName=" + name,
                "loginUserPassword=" + psw);
        observable.compose(RxUtils.<ToDoDeleteBean>rxObserableSchedulerHelper()).subscribe(new BaseObserver<ToDoDeleteBean>() {
            @Override
            public void onNext(ToDoDeleteBean toDoDeleteBean) {
                if (toDoDeleteBean.getErrorCode() == 0) {
                    ToastUtil.showShort("删除成功！");
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
}
