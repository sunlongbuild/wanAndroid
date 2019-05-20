package com.jiyun.wanandroid.utils;

import java.io.Closeable;
import java.io.IOException;
/**
 * Created by $sl on 2019/5/20 16:21.
 * IO操作的工具类
 */
public class IOUtils {

	/** 关闭流 */
	public static boolean close(Closeable io) {
		if (io != null) {
			try {
				io.close();
			} catch (IOException e) {
				Logger.println(e.toString());
			}
		}
		return true;
	}
}
