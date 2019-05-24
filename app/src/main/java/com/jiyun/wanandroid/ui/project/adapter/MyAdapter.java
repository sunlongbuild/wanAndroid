package com.jiyun.wanandroid.ui.project.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.entity.project.ListDataBean;
import com.jiyun.wanandroid.utils.ImageLoader;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context context;
    ArrayList<ListDataBean.DataBean.DatasBean> list;
    boolean love = false;
    public MyAdapter(Context context, ArrayList<ListDataBean.DataBean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_list_data, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final ListDataBean.DataBean.DatasBean datasBean = list.get(i);
        boolean collect = datasBean.isCollect();
        if (collect) {
            viewHolder.mXin.setImageResource(R.mipmap.icon_xin);
        }else {
            viewHolder.mXin.setImageResource(R.mipmap.icon_uxin);
        }
        ImageLoader.setImage(context, datasBean.getEnvelopePic(),viewHolder.mImg,R.drawable.zhanweitu_home_kapian);
        viewHolder.mTvTitle.setText(datasBean.getTitle());
        viewHolder.mTvMessage.setText(datasBean.getDesc());
        viewHolder.mTvName.setText(datasBean.getAuthor());
        viewHolder.mTvData.setText(datasBean.getNiceDate());
        viewHolder.mXin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (love==true){
                    viewHolder.mXin.setImageResource(R.mipmap.icon_xin);
                    love=false;
                }else {
                    viewHolder.mXin.setImageResource(R.mipmap.icon_uxin);
                    love=true;
                }
            }
        });


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClicklistener!=null){
                    onClicklistener.OnClick(i,datasBean);
                }
            }
        });
        viewHolder.mXin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMyImageOnClickListener!=null) {
                    mMyImageOnClickListener.setImgOnClick(i,viewHolder.mXin);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size() > 0 ? list.size() : 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImg;
        TextView mTvTitle;
        TextView mTvMessage;
        TextView mTvName;
        TextView mTvData;
        ImageView mXin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mImg = (ImageView) itemView.findViewById(R.id.img);
            this.mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            this.mTvMessage = (TextView) itemView.findViewById(R.id.tv_message);
            this.mTvName = (TextView) itemView.findViewById(R.id.tv_name);
            this.mTvData = (TextView) itemView.findViewById(R.id.tv_data);
            this.mXin = (ImageView) itemView.findViewById(R.id.xin);
        }
    }

    private OnClicklistener onClicklistener;

    public void setOnClicklistener(OnClicklistener onClicklistener) {
        this.onClicklistener = onClicklistener;
    }

    public interface OnClicklistener{
        void OnClick(int i,ListDataBean.DataBean.DatasBean datasBean);
     }

     public interface MyImageOnClickListener{
        void setImgOnClick(int position,ImageView view);
    }
    public MyImageOnClickListener mMyImageOnClickListener;

    public void setMyImageOnClickListener(MyImageOnClickListener myImageOnClickListener) {
        mMyImageOnClickListener = myImageOnClickListener;
    }

}
