package com.yongche.util;

import android.util.Log;

/**
 * 
 * @ClassName: LogUtils
 * @Description: TODO(日志类,添加调试控制)
 * @author guobangbang
 * @date 2014年8月5日 下午4:25:16
 * 
 */
public class LogUtils {
	// 是否开启调试，true:开启，false:关闭
	public static final boolean debug = true;

	public static void e(String tag, String msg) {
		if (debug) {
			Log.e(tag, msg);
		}
	}

	public static void v(String tag, String msg) {
		if (debug) {
			Log.v(tag, msg);
		}
	}

	public static void i(String tag, String msg) {
		if (debug) {
			Log.i(tag, msg);
		}
	}

	public static void d(String tag, String msg) {
		if (debug) {
			Log.d(tag, msg);
		}
	}

	public static void w(String tag, String msg) {
		if (debug) {
			Log.w(tag, msg);
		}
	}
}
