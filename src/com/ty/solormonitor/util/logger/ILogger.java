package com.ty.solormonitor.util.logger;

public interface ILogger {
	
	void v(Class<?> clazz, String paramMsg);

	void d(Class<?> clazz, String paramMsg);

	void i(Class<?> clazz, String paramMsg);

	void w(Class<?> clazz, String paramMsg);

	void e(Class<?> clazz, String paramMsg);

	void v(Class<?> clazz, String paramMsg, Throwable paramTr);

	void d(Class<?> clazz, String paramMsg, Throwable paramTr);

	void i(Class<?> clazz, String paramMsg, Throwable paramTr);

	void w(Class<?> clazz, String paramMsg, Throwable paramTr);

	void e(Class<?> clazz, String paramMsg, Throwable paramTr);
}
