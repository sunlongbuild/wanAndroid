package com.jiyun.wanandroid.ui.search.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.ui.search.activity.SeacherActivity;

import java.util.ArrayList;

public class LishiAdapter extends RecyclerView.Adapter<LishiAdapter.ViewHolder> {
    private ArrayList<String>list;
    private Context context;

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    public LishiAdapter(ArrayList<String> list, Context context) {

        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_lishi, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,final int i) {
    viewHolder.text_1.setText(list.get(i));
    viewHolder.text_1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, SeacherActivity.class);
            intent.putExtra("name",list.get(i));
            context.startActivity(intent);
        }
    });
    viewHolder.text_2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            list.remove(list.get(i));
            notifyDataSetChanged();
        }
    });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text_1;
        TextView text_2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text_1=itemView.findViewById(R.id.text);
            text_2=itemView.findViewById(R.id.text_2);
        }
    }
}
