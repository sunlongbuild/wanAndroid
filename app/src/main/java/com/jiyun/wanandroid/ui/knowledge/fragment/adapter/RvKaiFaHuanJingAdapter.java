package com.jiyun.wanandroid.ui.knowledge.fragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.entity.knowledge.KaiFaHuanJingBean;

import java.util.ArrayList;

public class RvKaiFaHuanJingAdapter extends RecyclerView.Adapter<RvKaiFaHuanJingAdapter.MyHolder> {

    private Context context;
    private ArrayList<KaiFaHuanJingBean.DataBean.DatasBean>list;

    public RvKaiFaHuanJingAdapter(Context context, ArrayList<KaiFaHuanJingBean.DataBean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_kaifahuaning_item, null);
        MyHolder myHolder = new MyHolder(inflate);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {

        myHolder.author.setText(list.get(i).getAuthor());
        myHolder.chapterName.setText(list.get(i).getChapterName());
        myHolder.niceDate.setText(list.get(i).getNiceDate());
        myHolder.title.setText(list.get(i).getTitle());
        myHolder.superChapterName.setText(list.get(i).getSuperChapterName());

        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myOnItenClcik!=null){
                    myOnItenClcik.setMyOnItenClcik(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        private TextView chapterName,superChapterName,title,niceDate,author;
        private ImageView follow_unselected;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            author=itemView.findViewById(R.id.author);
            chapterName=itemView.findViewById(R.id.chapterName);
            superChapterName=itemView.findViewById(R.id.superChapterName);
            title=itemView.findViewById(R.id.title);
            follow_unselected=itemView.findViewById(R.id.follow_unselected);
            niceDate=itemView.findViewById(R.id.niceDate);

        }

    }

    public  interface  MyOnItenClcik{
        void  setMyOnItenClcik(int position);
    }
    private MyOnItenClcik myOnItenClcik;

    public void setMyOnItenClcik(MyOnItenClcik myOnItenClcik) {
        this.myOnItenClcik = myOnItenClcik;
    }
}
