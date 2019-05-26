package com.jiyun.wanandroid.ui.todo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.entity.todo.ToDoUpdataBean;

import java.util.ArrayList;

/**
 * Created by $sl on 2019/5/26 12:04.
 */
public class MyFinishAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<ToDoUpdataBean.DataBean> mList;

    public MyFinishAdapter(Context context, ArrayList<ToDoUpdataBean.DataBean> list) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_todo, null);
            holder.mTv1 = convertView.findViewById(R.id.todo_title);
            holder.mTv2 = convertView.findViewById(R.id.tv_content);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTv1.setText(mList.get(position).getTitle());
        holder.mTv2.setText(mList.get(position).getContent());
        return convertView;
    }

    class ViewHolder {
        TextView mTv1;
        TextView mTv2;
    }
}
