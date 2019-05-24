package com.jiyun.wanandroid.ui.search.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.entity.search.SearchBean;
import com.jiyun.wanandroid.ui.search.activity.SeacherActivity;
import com.jiyun.wanandroid.utils.FlowLayout1;

import java.util.ArrayList;

public class NaviRecAdapter extends RecyclerView.Adapter<NaviRecAdapter.ViewHolder> {
    private ArrayList<SearchBean.DataBean> list=new ArrayList<>();
    private Context context;
    private TextView text;
    private ArrayList<Integer> colorlist;
    private OnItenClickListener listener;

    public void setListener(OnItenClickListener listener) {
        this.listener = listener;
    }

    public NaviRecAdapter(Context context) {
        this.context = context;
    }

    public void setList(ArrayList<SearchBean.DataBean> list) {
        this.list.clear();
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.item_navi_rec,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        colorlist = new ArrayList<>();
        colorlist.add(Color.RED);
        colorlist.add(Color.BLUE);
        colorlist.add(Color.GRAY);
        colorlist.add(R.color.white);
        colorlist.add(R.color.colorAccent);
            holder.flow.removeAllViews();
        for (int i = 0; i < list.size(); i++) {
            View view = View.inflate(context,R.layout.view_text,null);
            TextView tv = view.findViewById(R.id.textview);
            tv.setText(list.get(i).getName());
            tv.setTextColor(colorlist.get(i%colorlist.size()));
            final int finalI = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                       // listener.listener(list.get(position).getArticles().get(finalI).getLink(),list.get(position).getArticles().get(finalI).getTitle());
                        Intent intent = new Intent(context, SeacherActivity.class);
                        intent.putExtra("name",list.get(finalI).getName());
                        context.startActivity(intent);

                }
            });
            holder.flow.addView(view);
        }

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private FlowLayout1 flow;

        public ViewHolder(View itemView) {
            super(itemView);
            flow = itemView.findViewById(R.id.flow);
        }
    }

    public interface OnItenClickListener{
        void listener(String link, String title);
    }
}
