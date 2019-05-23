package com.jiyun.wanandroid.utils;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jiyun.wanandroid.base.Constants;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
/**
 * Created by $sl on 2019/5/20 16:21.
 * 加载图片工具类
 */
public class ImageLoader {




    /**
     * 通过链接加载网络图片
     * @param context
     * @param url
     * @param iv
     * @param placeImg
     */
    public static void setImage(Context context, String url, ImageView iv,int placeImg){
        RequestOptions options = new RequestOptions()
                .placeholder(placeImg);
        //获取设置页面选择的无图模式
        boolean mIsLoadingImage = (boolean) SpUtil.getParam(Constants.SETTING_NO_IMAGE, false);
        if (mIsLoadingImage== false){
            Glide.with(context).load(url).apply(options).into(iv);
        }
    }

    /**
     * 加载本地资源图片
     * @param context
     * @param resId
     * @param iv
     * @param placeImg
     */
    public static void setImage(Context context, int resId, ImageView iv,int placeImg){
        RequestOptions options = new RequestOptions()
                .placeholder(placeImg);
        boolean mIsLoadingImage = (boolean) SpUtil.getParam(Constants.SETTING_NO_IMAGE, false);
        if (mIsLoadingImage == false) {
            Glide.with(context).load(resId).apply(options).into(iv);
        }
    }

    /**
     * 加载本地资源图片--圆形
     * @param context
     * @param resId
     * @param iv
     * @param placeImg
     */
    public static void setCircleImage(Context context, int resId, ImageView iv,int placeImg){
        RequestOptions options = new RequestOptions()
                .placeholder(placeImg)
                .circleCrop();
        boolean mIsLoadingImage = (boolean) SpUtil.getParam(Constants.SETTING_NO_IMAGE, false);
        if (mIsLoadingImage== false) {
            Glide.with(context).load(resId).apply(options).into(iv);
        }
    }

    /**
     * 通过链接加载网络图片 -- 圆形
     * @param context
     * @param url
     * @param iv
     * @param placeImg
     */
    public static void setCircleImage(Context context, String url, ImageView iv,int placeImg){
        RequestOptions options = new RequestOptions()
                .placeholder(placeImg)
                .circleCrop();
        boolean mIsLoadingImage = (boolean) SpUtil.getParam(Constants.SETTING_NO_IMAGE, false);
        if (mIsLoadingImage== false) {
            Glide.with(context).load(url).apply(options).into(iv);
        }
    }
    /**
     * 通过链接加载网络图片 -- 圆角
     * @param context
     * @param url
     * @param iv
     * @param radiusDp 圆角大小,单位dp
     * @param placeImg
     */
    public static void setCornerImage(Context context, String url, ImageView iv,
                                      int radiusDp,int placeImg){
        RequestOptions options = new RequestOptions()
                .placeholder(placeImg)
                .transform(new RoundedCornersTransformation(SystemUtil.dp2px(radiusDp),0));
        boolean mIsLoadingImage = (boolean) SpUtil.getParam(Constants.SETTING_NO_IMAGE, false);
        if (mIsLoadingImage== false) {
            Glide.with(context).load(url).apply(options).into(iv);
        }
    }

    /**
     * 加载本地资源图片 -- 圆角
     * @param context
     * @param resId
     * @param iv
     * @param radiusDp 圆角大小,单位dp
     * @param placeImg
     */
    public static void setCornerImage(Context context, int resId, ImageView iv,
                                      int radiusDp,int placeImg){
        RequestOptions options = new RequestOptions()
                .placeholder(placeImg)
                .transform(new RoundedCornersTransformation(SystemUtil.dp2px(radiusDp),0));
        boolean mIsLoadingImage = (boolean) SpUtil.getParam(Constants.SETTING_NO_IMAGE, false);
        if (!mIsLoadingImage) {
            Glide.with(context).load(resId).apply(options).into(iv);
        }
    }
}
