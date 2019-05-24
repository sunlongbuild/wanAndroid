package com.jiyun.wanandroid.ui.collect.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.api.collect.OnClickListener;
import com.jiyun.wanandroid.entity.collect.CollectListBean;
import com.jiyun.wanandroid.utils.ImageLoader;

import java.util.List;

public class Collect_Adapter extends RecyclerView.Adapter {
    private List<CollectListBean.DataBean.DatasBean> mDatasBeans;
    private Context mContext;
    private OnClickListener mOnClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public Collect_Adapter(Context context, List<CollectListBean.DataBean.DatasBean> datasBeans) {
        mDatasBeans = datasBeans;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i==1) {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.layout_collect_project, null, false);
            return new ViewHolder_one(inflate);
        }else {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.layout_collect_list, null, false);
            return new ViewHolder_two(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        int itemViewType = viewHolder.getItemViewType();
        if (itemViewType==1) {
            ViewHolder_one vho = (ViewHolder_one) viewHolder;
            ImageLoader.setImage(mContext,mDatasBeans.get(i).getEnvelopePic(),vho.mSuch,R.mipmap.ic_launcher);
            vho.mAuthor.setText(mDatasBeans.get(i).getAuthor());
            vho.mTab.setText(mDatasBeans.get(i).getChapterName());
            vho.mTitle.setText(mDatasBeans.get(i).getTitle());
            vho.mTime.setText(mDatasBeans.get(i).getNiceDate());
            vho.mXin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnClickListener!=null) {
                        mOnClickListener.OnClick(i);
                    }
                }
            });
        }else {
            ViewHolder_two vht = (ViewHolder_two) viewHolder;
            vht.mAuthor.setText(mDatasBeans.get(i).getAuthor());
            vht.mChapterName.setText(mDatasBeans.get(i).getChapterName());
            vht.mNiceData.setText(mDatasBeans.get(i).getNiceDate());
            vht.mXin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnClickListener!=null) {
                        mOnClickListener.OnClick(i);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatasBeans.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mDatasBeans.get(position).getId()!=-1||mDatasBeans.get(position).getEnvelopePic()!=null){
            return 1;
        }else {
            return 2;
        }
    }

    private class ViewHolder_one extends RecyclerView.ViewHolder {

        private final TextView mAuthor;
        private final TextView mTime;
        private final TextView mTitle;
        private final TextView mTab;
        private final ImageView mSuch;
        private final ImageView mXin;

        public ViewHolder_one(View inflate) {
            super(inflate);
            mAuthor = inflate.findViewById(R.id.collect_tv_project_author);
            mTime = inflate.findViewById(R.id.collect_tv_project_time);
            mTitle = inflate.findViewById(R.id.collect_tv_project_title);
            mTab = inflate.findViewById(R.id.collect_tv_project_tab);
            mSuch = inflate.findViewById(R.id.collect_image_project_such);
            mXin = inflate.findViewById(R.id.collect_image_project_xin);
        }
    }

    private class ViewHolder_two extends RecyclerView.ViewHolder {

        private final TextView mAuthor;
        private final TextView mTitle;
        private final TextView mChapterName;
        private final ImageView mXin;
        private final TextView mNiceData;

        public ViewHolder_two(View inflate) {
            super(inflate);
            mAuthor = inflate.findViewById(R.id.author);
            mTitle = inflate.findViewById(R.id.title);
            mChapterName = inflate.findViewById(R.id.chapterName);
            mNiceData = inflate.findViewById(R.id.niceDate);
            mXin = inflate.findViewById(R.id.img_shou_home);
        }
    }
}
