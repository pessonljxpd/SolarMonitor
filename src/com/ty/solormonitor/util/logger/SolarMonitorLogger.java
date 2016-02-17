package com.ty.solormonitor.util.logger;

import com.ty.solarmonitor.BuildConfig;

import android.util.Log;

public class SolarMonitorLogger implements ILogger {

	private boolean debug = BuildConfig.DEBUG;

	private static SolarMonitorLogger mLogger = null;

	private SolarMonitorLogger() {
	}

	public static SolarMonitorLogger getInstance() {
		if (mLogger == null) {
			synchronized (SolarMonitorLogger.class) {
				if (mLogger == null) {
					mLogger = new SolarMonitorLogger();
				}
			}
		}
		return mLogger;
	}

	protected String buildTag(Class<?> clazz) {
		String clazzName = clazz.getSimpleName();
		return clazzName + ".SolarMonitor";
	}

	protected String buildMessage(String paramString) {
		StackTraceElement localStackTraceElement = new Throwable().fillInStackTrace().getStackTrace()[2];
		return localStackTraceElement.getClassName() + "." + localStackTraceElement.getMethodName() + "(): "
				+ paramString;
	}

	@Override
	public void v(Class<?> clazz, String paramMsg) {
		if (debug) {
			Log.v(buildTag(clazz), buildMessage(paramMsg));
		}
	}

	@Override
	public void d(Class<?> clazz, String paramMsg) {
		if (debug) {
			Log.d(buildTag(clazz), buildMessage(paramMsg));
		}
	}

	@Override
	public void i(Class<?> clazz, String paramMsg) {
		if (debug) {
			Log.i(buildTag(clazz), buildMessage(paramMsg));
		}
	}

	@Override
	public void w(Class<?> clazz, String paramMsg) {
		if (debug) {
			Log.w(buildTag(clazz), buildMessage(paramMsg));
		}
	}

	@Override
	public void e(Class<?> clazz, String paramMsg) {
		if (debug) {
			Log.e(buildTag(clazz), buildMessage(paramMsg));
		}
	}

	@Override
	public void v(Class<?> clazz, String paramMsg, Throwable paramTr) {
		if (debug) {
			Log.v(buildTag(clazz), buildMessage(paramMsg), paramTr);
		}
	}

	@Override
	public void d(Class<?> clazz, String paramMsg, Throwable paramTr) {
		if (debug) {
			Log.d(buildTag(clazz), buildMessage(paramMsg), paramTr);
		}
	}

	@Override
	public void i(Class<?> clazz, String paramMsg, Throwable paramTr) {
		if (debug) {
			Log.i(buildTag(clazz), buildMessage(paramMsg), paramTr);
		}
	}

	@Override
	public void w(Class<?> clazz, String paramMsg, Throwable paramTr) {
		if (debug) {
			Log.w(buildTag(clazz), buildMessage(paramMsg), paramTr);
		}
	}

	@Override
	public void e(Class<?> clazz, String paramMsg, Throwable paramTr) {
		if (debug) {
			Log.e(buildTag(clazz), buildMessage(paramMsg), paramTr);
		}

	}
}
