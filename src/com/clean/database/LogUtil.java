package com.clean.database;

import android.util.Log;

public class LogUtil {
	private static boolean isDebug = true;

	/**
	 * 停止调试
	 */
	public static void shutUp() {
		isDebug = false;
	}

	/**
	 * 开启调试
	 */
	public static void openDebug() {
		isDebug = true;
	}

	public static void i(String tag, String msg) {
		if (isDebug) {
			Log.i(tag, msg);
		}
	}

	public static void e(String tag, String msg) {
		if (isDebug) {
			Log.e(tag, msg);
		}
	}

	public static void w(String tag, String msg) {
		if (isDebug) {
			Log.w(tag, msg);
		}
	}

	public static void v(String tag, String msg) {
		if (isDebug) {
			Log.v(tag, msg);
		}
	}

	public static void d(String tag, String msg) {
		if (isDebug) {
			Log.d(tag, msg);
		}
	}

	public static void exception(String tag, Exception e) {
		if (isDebug) {
			e.printStackTrace();
			Log.e(tag, e.getMessage());
		}
	}
}
