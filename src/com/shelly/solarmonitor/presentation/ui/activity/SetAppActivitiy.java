package com.shelly.solarmonitor.presentation.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.shelly.library.eventbus.EventCenter;
import com.shelly.library.netstatus.NetUtils.NetType;
import com.shelly.solarmonitor.presentation.ui.activity.base.BaseSwipeBackActivity;

public class SetAppActivitiy extends BaseSwipeBackActivity {

	@Override
	protected boolean isApplyKitKatTranslucency() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void getBundleExtras(Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected int getContentViewLayoutID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void onEventComming(EventCenter eventCenter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected View getLoadingTargetView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void initViewsAndEvents() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onNetworkConnected(NetType type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onNetworkDisConnected() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isApplyStatusBarTranslucency() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean isBindEventBusHere() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean toggleOverridePendingTransition() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected TransitionMode getOverridePendingTransitionMode() {
		// TODO Auto-generated method stub
		return null;
	}


}
