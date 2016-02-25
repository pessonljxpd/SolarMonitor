package com.shelly.solormonitor.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;

import android.text.TextUtils;
import android.util.Log;

import com.ty.solarmonitor.BuildConfig;

public class LogUtils {

	private static final boolean DEBUG = BuildConfig.DEBUG;
	private static final String SEPARATOR = ",";

	private static String getDefaultTag(StackTraceElement stackTraceElement) {
		String fileName = stackTraceElement.getFileName();
		String[] stringArray = fileName.split("\\.");
		String tag = stringArray[0];
		return tag;
	}

	private static String getLogInfo(StackTraceElement stackTraceElement, String paramMsg) {
		String stackTraceElementInfo = StackTraceElementInfoFormat(stackTraceElement);

		return stackTraceElementInfo + paramMsg;
	}

	private static String getLogInfo(StackTraceElement stackTraceElement, String paramMsg, Throwable paramTr) {
		String stackTraceElementInfo = StackTraceElementInfoFormat(stackTraceElement);

		return stackTraceElementInfo + paramMsg + "\n" + getStackTraceString(paramTr);
	}

	private static String StackTraceElementInfoFormat(StackTraceElement stackTraceElement) {
		StringBuilder logInfoStringBuilder = new StringBuilder();
		String threadName = Thread.currentThread().getName();
		long threadId = Thread.currentThread().getId();
		String fileName = stackTraceElement.getFileName();
		String className = stackTraceElement.getClassName();
		String methodName = stackTraceElement.getMethodName();
		int lineNumber = stackTraceElement.getLineNumber();

		logInfoStringBuilder.append("[");
		logInfoStringBuilder.append("threadName=" + threadName).append(SEPARATOR);
		logInfoStringBuilder.append("threadId=" + threadId).append(SEPARATOR);
		logInfoStringBuilder.append("fileName=" + fileName).append(SEPARATOR);
		logInfoStringBuilder.append("className=" + className).append(SEPARATOR);
		logInfoStringBuilder.append("methodName=" + methodName).append(SEPARATOR);
		logInfoStringBuilder.append("lineNumber=" + lineNumber);
		logInfoStringBuilder.append("]:");

		return logInfoStringBuilder.toString();
	}

	public static String getStackTraceString(Throwable tr) {
		if (tr == null) {
			return "";
		}

		// This is to reduce the amount of log spew that apps do in the
		// non-error
		// condition of the network being unavailable.
		Throwable t = tr;
		while (t != null) {
			if (t instanceof UnknownHostException) {
				return "";
			}
			t = t.getCause();
		}

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		tr.printStackTrace(pw);
		pw.flush();
		return sw.toString();
	}

	public static void v(String paramMsg) {
		if (DEBUG) {
			StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
			String tag = getDefaultTag(stackTraceElement);
			Log.v(tag, getLogInfo(stackTraceElement, paramMsg));
		}
	}

	public static void d(String paramMsg) {
		if (DEBUG) {
			StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
			String tag = getDefaultTag(stackTraceElement);
			Log.d(tag, getLogInfo(stackTraceElement, paramMsg));
		}
	}

	public static void i(String paramMsg) {
		if (DEBUG) {
			StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
			String tag = getDefaultTag(stackTraceElement);
			Log.i(tag, getLogInfo(stackTraceElement, paramMsg));
		}
	}

	public static void w(String paramMsg) {
		if (DEBUG) {
			StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
			String tag = getDefaultTag(stackTraceElement);
			Log.w(tag, getLogInfo(stackTraceElement, paramMsg));
		}
	}

	public static void e(String paramMsg) {
		if (DEBUG) {
			StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
			String tag = getDefaultTag(stackTraceElement);
			Log.e(tag, getLogInfo(stackTraceElement, paramMsg));
		}
	}

	public static void v(String paramMsg, Throwable tr) {
		if (DEBUG) {
			StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
			String tag = getDefaultTag(stackTraceElement);
			Log.v(tag, getLogInfo(stackTraceElement, paramMsg, tr));
		}
	}

	public static void d(String paramMsg, Throwable tr) {
		if (DEBUG) {
			StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
			String tag = getDefaultTag(stackTraceElement);
			Log.d(tag, getLogInfo(stackTraceElement, paramMsg, tr));
		}
	}

	public static void i(String paramMsg, Throwable tr) {
		if (DEBUG) {
			StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
			String tag = getDefaultTag(stackTraceElement);
			Log.i(tag, getLogInfo(stackTraceElement, paramMsg, tr));
		}
	}

	public static void w(String paramMsg, Throwable tr) {
		if (DEBUG) {
			StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
			String tag = getDefaultTag(stackTraceElement);
			Log.w(tag, getLogInfo(stackTraceElement, paramMsg, tr));
		}
	}

	public static void e(String paramMsg, Throwable tr) {
		if (DEBUG) {
			StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
			String tag = getDefaultTag(stackTraceElement);
			Log.e(tag, getLogInfo(stackTraceElement, paramMsg, tr));
		}
	}

	public static void v(String paramTag, String paramMsg) {
		if (DEBUG) {
			StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
			if (TextUtils.isEmpty(paramTag)) {
				paramTag = getDefaultTag(stackTraceElement);
			}
			Log.v(paramTag, getLogInfo(stackTraceElement, paramMsg));
		}
	}

	public static void d(String paramTag, String paramMsg) {
		if (DEBUG) {
			StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
			if (TextUtils.isEmpty(paramTag)) {
				paramTag = getDefaultTag(stackTraceElement);
			}
			Log.d(paramTag, getLogInfo(stackTraceElement, paramMsg));
		}
	}

	public static void i(String paramTag, String paramMsg) {
		if (DEBUG) {
			StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
			if (TextUtils.isEmpty(paramTag)) {
				paramTag = getDefaultTag(stackTraceElement);
			}
			Log.i(paramTag, getLogInfo(stackTraceElement, paramMsg));
		}
	}

	public static void w(String paramTag, String paramMsg) {
		if (DEBUG) {
			StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
			if (TextUtils.isEmpty(paramTag)) {
				paramTag = getDefaultTag(stackTraceElement);
			}
			Log.w(paramTag, getLogInfo(stackTraceElement, paramMsg));
		}
	}

	public static void e(String paramTag, String paramMsg) {
		if (DEBUG) {
			StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
			if (TextUtils.isEmpty(paramTag)) {
				paramTag = getDefaultTag(stackTraceElement);
			}
			Log.e(paramTag, getLogInfo(stackTraceElement, paramMsg));
		}
	}

	public static void v(String paramTag, String paramMsg, Throwable tr) {
		if (DEBUG) {
			StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
			if (TextUtils.isEmpty(paramTag)) {
				paramTag = getDefaultTag(stackTraceElement);
			}
			Log.v(paramTag, getLogInfo(stackTraceElement, paramMsg, tr));
		}
	}

	public static void d(String paramTag, String paramMsg, Throwable tr) {
		if (DEBUG) {
			StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
			if (TextUtils.isEmpty(paramTag)) {
				paramTag = getDefaultTag(stackTraceElement);
			}
			Log.d(paramTag, getLogInfo(stackTraceElement, paramMsg, tr));
		}
	}

	public static void i(String paramTag, String paramMsg, Throwable tr) {
		if (DEBUG) {
			StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
			if (TextUtils.isEmpty(paramTag)) {
				paramTag = getDefaultTag(stackTraceElement);
			}
			Log.i(paramTag, getLogInfo(stackTraceElement, paramMsg, tr));
		}
	}

	public static void w(String paramTag, String paramMsg, Throwable tr) {
		if (DEBUG) {
			StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
			if (TextUtils.isEmpty(paramTag)) {
				paramTag = getDefaultTag(stackTraceElement);
			}
			Log.w(paramTag, getLogInfo(stackTraceElement, paramMsg, tr));
		}
	}

	public static void e(String paramTag, String paramMsg, Throwable tr) {
		if (DEBUG) {
			StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
			if (TextUtils.isEmpty(paramTag)) {
				paramTag = getDefaultTag(stackTraceElement);
			}
			Log.e(paramTag, getLogInfo(stackTraceElement, paramMsg, tr));
		}
	}

}
