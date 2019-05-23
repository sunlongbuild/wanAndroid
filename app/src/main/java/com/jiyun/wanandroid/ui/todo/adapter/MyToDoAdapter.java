package com.jiyun.wanandroid.ui.todo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.jiyun.wanandroid.R;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import java.util.ArrayList;

/**
 * Created by $sl on 2019/5/22 14:17.
 */
public class MyToDoAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> mList;

    public MyToDoAdapter(Context context, ArrayList<String> list) {
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
            viewHolder.mSwipeMenuLayout = (SwipeMenuLayout) convertView.findViewById(R.id.swipeMenuLayout);
            viewHolder.mTvContent = (TextView) convertView.findViewById(R.id.tv_content);
            viewHolder.mBtnRead = (Button) convertView.findViewById(R.id.todo_read);
            viewHolder.mBtnDelete = (Button) convertView.findViewById(R.id.todo_delete);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mTvContent.setText(mList.get(position) + "");
        final ViewHolder lastViewHolder = viewHolder;
        //对删除按钮监听
        viewHolder.mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.remove(position);
                //刷新适配器
                notifyDataSetChanged();
                //关闭左滑菜单
                lastViewHolder.mSwipeMenuLayout.quickClose();
            }
        });
        return convertView;
    }

    class ViewHolder {
        SwipeMenuLayout mSwipeMenuLayout;
        TextView mTvContent;
        Button mBtnRead;
        Button mBtnDelete;
    }
}
