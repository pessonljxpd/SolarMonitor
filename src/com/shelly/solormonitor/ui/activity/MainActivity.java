package com.shelly.solormonitor.ui.activity;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.widget.Button;
import android.widget.TextView;

import com.ty.solarmonitor.R;


@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {

	@ViewById(R.id.mTv)
	public TextView mTv;
	@ViewById(R.id.mBtn)
	public Button mBtnJumpSetting;

	@AfterInject
	void init(){
		
	}
	
	@AfterViews
	public void initView() {
		mTv.setText("Hello world");
	}
	
	@Click(R.id.mBtn)
	void startSettingActivity(){
		CollectorConnectSetting_.intent(this).start();
	}

}
