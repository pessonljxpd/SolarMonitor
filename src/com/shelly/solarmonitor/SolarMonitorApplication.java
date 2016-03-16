package com.shelly.solarmonitor;

import android.app.Application;
import android.content.res.Configuration;

import com.shelly.library.base.BaseAppManager;

public class SolarMonitorApplication extends Application {
	
	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onLowMemory() {
		android.os.Process.killProcess(android.os.Process.myPid());
		super.onLowMemory();
	}
	
	public void exitApp(){
		BaseAppManager.getInstance().clear();
		System.gc();
		android.os.Process.killProcess(android.os.Process.myPid());
	}
}
