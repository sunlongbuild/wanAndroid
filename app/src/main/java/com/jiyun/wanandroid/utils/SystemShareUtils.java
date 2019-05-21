package com.jiyun.wanandroid.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.File;

/*
* *  author gme
*    time
*/
//系统分享
public class SystemShareUtils {
            //分享文字
    public static void shareText(Context context,String text) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, text);
        shareIntent.setType("text/plain");
        context.startActivity(Intent.createChooser(shareIntent, "分享到")); //设置标题（弹出分享列表的界面标题），
    }
         //分享本地图片
     public static void shareLocalityImage(Context context,String fileImagePath) {
        //获取图片路径  将本地图片路径转化为Uri
        Uri imageUri = Uri.fromFile(new File(fileImagePath));
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
        shareIntent.setType("image/*");
        context.startActivity(Intent.createChooser(shareIntent, "分享到："));
    }
            //分享网络图片
/*    public static void shareNetImage(Context context,String imagePath) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, imagePath);
        shareIntent.setType("image/*");
        context.startActivity(Intent.createChooser(shareIntent, "分享到："));
    }*/
    /**
     * 分享功能
     *
     * @param context
     *            上下文
     *            Activity的名字
     * @param msgTitle
     *            消息标题
     * @param msgText
     *            消息内容
     * @param imgPath
     *            图片路径，不分享图片则传null
     */
  /*  public static void shareMsg(Context context,String msgTitle, String msgText,
                         String imgPath) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        if (imgPath == null || imgPath.equals("")) {
            intent.setType("text/plain"); // 纯文本
        } else {
            File f = new File(imgPath);//创建文件
            if (f != null && f.exists() && f.isFile()) {//判断文件不诶空   判断文件是否存在  //判断是否是文件
                intent.setType("image/jpg");
                Uri u = Uri.fromFile(f);
                intent.putExtra(Intent.EXTRA_STREAM, u);
            }
        }
        intent.putExtra(Intent.EXTRA_SUBJECT, msgTitle);
        intent.putExtra(Intent.EXTRA_TEXT, msgText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, "分享到"));
    }*/
}
