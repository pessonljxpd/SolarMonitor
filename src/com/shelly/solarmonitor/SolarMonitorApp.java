package com.shelly.solarmonitor;

import android.app.Application;
import android.content.res.Configuration;

public class SolarMonitorApp extends Application {

	private SolarMonitorApp mApplication;
	
	@Override
	public void onCreate() {
		super.onCreate();
		mApplication = this;
	}
	
	public SolarMonitorApp getApplication(){
		return mApplication;
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
