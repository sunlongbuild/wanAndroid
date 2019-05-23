package com.jiyun.wanandroid.ui.search.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.entity.search.Bean;
import com.jiyun.wanandroid.ui.search.activity.WebSeacherActivity;

import java.util.List;

public class Search_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Bean.DataBean.DatasBean> list;
    private String s1;

    public Search_Adapter(Context context, List<Bean.DataBean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Myview(LayoutInflater.from(context).inflate(R.layout.layout_seacher,null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Myview myview= (Myview) holder;
        if(list.get(position).getSuperChapterName()!=null){
            myview.name.setText(list.get(position).getSuperChapterName());
        }
        myview.text_1.setText(list.get(position).getAuthor());
        myview.text_2.setText(list.get(position).getNiceDate());
        String title = list.get(position).getTitle();
        String s = title.replaceAll("<em class='highlight'>", "");

        s1 = s.replaceAll("</em>", "");
        myview.text_3.setText(s1);

        myview.text_4.setText(list.get(position).getSuperChapterName());
        myview.text_6.setText(list.get(position).getAuthor());
        myview.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebSeacherActivity.class);
                intent.putExtra("id",list.get(position).getLink());
                intent.putExtra("name",s1);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class Myview extends RecyclerView.ViewHolder {

        private final TextView name;
        TextView text_1;
        TextView text_2;
        TextView text_3;
        TextView text_4;
        TextView text_6;

        public Myview(View itemView) {
            super(itemView);
            text_1 = itemView.findViewById(R.id.text_1);
            text_2 = itemView.findViewById(R.id.text_2);
            text_3 = itemView.findViewById(R.id.text_3);
            text_4 = itemView.findViewById(R.id.text_4);
            text_6 = itemView.findViewById(R.id.text_6);
            name = itemView.findViewById(R.id.name);
        }
    }
}
