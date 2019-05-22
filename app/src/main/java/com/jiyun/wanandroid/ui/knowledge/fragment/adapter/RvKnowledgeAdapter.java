package com.jiyun.wanandroid.ui.knowledge.fragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.entity.knowledge.KnowledgeBean;

import java.util.ArrayList;
import java.util.List;

public class RvKnowledgeAdapter extends RecyclerView.Adapter<RvKnowledgeAdapter.MyHolder> {

    private Context context;
    private ArrayList<KnowledgeBean.DataBean> mlist;

    public RvKnowledgeAdapter(Context context, ArrayList<KnowledgeBean.DataBean> mlist) {
        this.context = context;
        this.mlist = mlist;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_knowledge_item, null);
        MyHolder myHolder = new MyHolder(inflate);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder,final int i) {
        myHolder.main_name_item.setText(mlist.get(i).getName());


        final KnowledgeBean.DataBean dataBean = mlist.get(i);
        myHolder.main_name_item.setText(dataBean.getName());
        List<KnowledgeBean.DataBean.ChildrenBean> children = dataBean.getChildren();
        StringBuffer stringBuffer = new StringBuffer();
        for (int j = 0; j < children.size(); j++) {
            stringBuffer.append(children.get(j).getName()+"  ");

        }
        String s = stringBuffer.toString();
        myHolder.name.setText(s);
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myClickListener!=null){
                    myClickListener.setMyClickListener(i,dataBean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView  name,main_name_item;


        public MyHolder(@NonNull View itemView) {
            super(itemView);
            main_name_item = itemView.findViewById(R.id.main_name_item);
            name = itemView.findViewById(R.id.name);
        }
    }


    public  interface  MyClickListener{
        void  setMyClickListener(int position,KnowledgeBean.DataBean dataBean);
    }
    private MyClickListener myClickListener;

    public void setMyClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }
}
