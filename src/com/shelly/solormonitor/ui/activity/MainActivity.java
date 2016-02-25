package com.shelly.solormonitor.ui.activity;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.ocpsoft.prettytime.PrettyTime;

import android.widget.Button;
import android.widget.TextView;

import com.shelly.solormonitor.util.LogUtils;
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
		PrettyTime prettyTime = new PrettyTime(Locale.ENGLISH);
		
		mTv.setText(prettyTime.format(new Date(System.currentTimeMillis() + 1000 * 60 * 10)));
	}
	
	@Click(R.id.mBtn)
	void startSettingActivity(){
		CollectorConnectSetting_.intent(this).start();
	}

}
