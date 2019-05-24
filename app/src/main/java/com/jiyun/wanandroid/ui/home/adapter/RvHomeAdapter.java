package com.jiyun.wanandroid.ui.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.entity.home.HomeBannerBean;
import com.jiyun.wanandroid.entity.home.HomeRevBean;
import com.jiyun.wanandroid.entity.home.HomeTopBean;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class RvHomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<HomeRevBean.DataBean.DatasBean> rvlist;
    private ArrayList<HomeBannerBean.DataBean> bannerlist;
    private ArrayList<HomeTopBean.DataBean>toplist;

    public RvHomeAdapter(Context context, ArrayList<HomeRevBean.DataBean.DatasBean> rvlist, ArrayList<HomeBannerBean.DataBean> bannerlist ) {
        this.context = context;
        this.rvlist = rvlist;
        this.bannerlist = bannerlist;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder holder = null;
        if (i == 1) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
            holder = new MyBanner(inflate);

        } else if (i == 2) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.layout_home_rvitem, null);
            holder = new MyRvItem(inflate);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {

        int itemViewType = getItemViewType(i);
        if (itemViewType == 1) {
            MyBanner myBanner = (MyBanner) viewHolder;
            ArrayList<String> strings = new ArrayList<>();
            for (int j = 0; j < bannerlist.size(); j++) {
                String title = bannerlist.get(j).getTitle();

                strings.add(title);
            }
            myBanner.banner_item.setImages(bannerlist);
            myBanner.banner_item.setBannerTitles(strings);
            myBanner.banner_item.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
            myBanner.banner_item.setBannerAnimation(Transformer.DepthPage);
            myBanner.banner_item.isAutoPlay(true);
            myBanner.banner_item.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    HomeBannerBean.DataBean bean = (HomeBannerBean.DataBean) path;
                    String imagePath = bean.getImagePath();
                    Glide.with(context).load(imagePath).into(imageView);
                }
            });
            myBanner.banner_item.start();
             myBanner.banner_item.setOnBannerListener(new OnBannerListener() {
                 @Override
                 public void OnBannerClick(int position) {
                     if (myBannerOnClickListener!=null){
                         myBannerOnClickListener.setMyBannerOnClickListener(position);
                     }
                 }
             });

        } else if (itemViewType == 2) {

            final MyRvItem myRvItem = (MyRvItem) viewHolder;
            int mposition = i;
            if (bannerlist.size() > 0) {
                mposition = i - 1;
            }
            myRvItem.author.setText(rvlist.get(mposition).getAuthor());
            myRvItem.chapterName.setText(rvlist.get(mposition).getChapterName());
            myRvItem.niceDate.setText(rvlist.get(mposition).getNiceDate());
            myRvItem.title.setText(rvlist.get(mposition).getTitle());
            myRvItem.superChapterName.setText(rvlist.get(mposition).getSuperChapterName() + "   /   ");
            boolean collect = rvlist.get(i).isCollect();
            if (collect) {
                myRvItem.img_shou_home.setImageResource(R.mipmap.icon_xin);
            }else {
                myRvItem.img_shou_home.setImageResource(R.mipmap.icon_uxin);
            }
            myRvItem.img_shou_home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mMyImageOnClickListener!=null) {
                        mMyImageOnClickListener.setImgOnClick(i,myRvItem.img_shou_home);
                    }
                }
            });

            myRvItem.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (myItemOnClickListener != null) {
                        myItemOnClickListener.setMyItemOnClickListener(i);
                    }
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        if (bannerlist.size() > 0) {
            return rvlist.size() - 1;
        }else
        return rvlist.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        } else {
            return 2;
        }

    }

    public class MyBanner extends RecyclerView.ViewHolder {
        private Banner banner_item;

        public MyBanner(@NonNull View itemView) {
            super(itemView);
            banner_item = itemView.findViewById(R.id.banner);
        }
    }

    public class MyRvItem extends RecyclerView.ViewHolder {
        private TextView author, title, superChapterName, chapterName, niceDate;
        private ImageView img_shou_home;

        public MyRvItem(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.author);
            title = itemView.findViewById(R.id.title);
            superChapterName = itemView.findViewById(R.id.superChapterName);
            chapterName = itemView.findViewById(R.id.chapterName);
            niceDate = itemView.findViewById(R.id.niceDate);
            img_shou_home=itemView.findViewById(R.id.home_follow_unselectedd);
        }

    }

    public interface MyItemOnClickListener {
        void setMyItemOnClickListener(int position);
    }

    private MyItemOnClickListener myItemOnClickListener;

    public void setMyItemOnClickListener(MyItemOnClickListener myItemOnClickListener) {
        this.myItemOnClickListener = myItemOnClickListener;
    }

    public interface MyImageOnClickListener{
        void setImgOnClick(int position,ImageView view);
    }
    public MyImageOnClickListener mMyImageOnClickListener;

    public void setMyImageOnClickListener(MyImageOnClickListener myImageOnClickListener) {
        mMyImageOnClickListener = myImageOnClickListener;
    }

    public interface MyBannerOnClickListener {
        void setMyBannerOnClickListener(int position);
    }

    private MyBannerOnClickListener myBannerOnClickListener;

    public void setMyBannerOnClickListener(MyBannerOnClickListener myBannerOnClickListener) {
        this.myBannerOnClickListener = myBannerOnClickListener;
    }
}
