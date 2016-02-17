package com.ty.solormonitor;

import org.androidannotations.annotations.EApplication;

import android.app.Application;
import android.content.res.Configuration;

import com.ty.solormonitor.util.logger.ILogger;
import com.ty.solormonitor.util.logger.SolarMonitorLogger;

@EApplication
public class SolarMonitorApp extends Application {

	private SolarMonitorApp mApplication;
	private ILogger mLogger;
	
	@Override
	public void onCreate() {
		super.onCreate();
		mApplication = this;
		mLogger = SolarMonitorLogger.getInstance();
	}
	
	public SolarMonitorApp getApplication(){
		return mApplication;
	}
	
	public ILogger getAppLogger(){
		return mLogger;
	}

	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onLowMemory() {
		// TODO Auto-generated method stub
		super.onLowMemory();
	}
}
