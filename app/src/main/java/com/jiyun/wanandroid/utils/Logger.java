package com.jiyun.wanandroid.utils;

import android.util.Log;

import com.jiyun.wanandroid.base.Constants;
/**
 * Created by $sl on 2019/5/20 16:22.
 */
public class Logger {
    public static void logD(String tag,String msg){
        if (Constants.isDebug){
            Log.d(tag, "logD: "+msg);
        }
    }
    public static void println(String msg){
        if (Constants.isDebug){
            System.out.println(msg);
        }
    }
}
