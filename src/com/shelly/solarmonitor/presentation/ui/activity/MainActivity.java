package com.shelly.solarmonitor.presentation.ui.activity;

import java.util.Date;
import java.util.Locale;

import org.ocpsoft.prettytime.PrettyTime;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.shelly.solarmonitor.presentation.presenter.MainPresenter;
import com.ty.solarmonitor.R;

public class MainActivity extends BaseActivity implements MainPresenter.View{

	@Bind(R.id.mToolbar)
	public Toolbar mToolbar;

	@Bind(R.id.mTv)
	public TextView mTv;
	@Bind(R.id.mBtn)
	public Button mBtnJumpSetting;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		
		PrettyTime prettyTime = new PrettyTime(Locale.ENGLISH);
		mTv.setText(prettyTime.format(new Date(System.currentTimeMillis() + 1000 * 60 * 10)));
	}


	@OnClick(R.id.mBtn)
	void startSettingActivity() {
//		CollectorConnectSetting.intent(this).start();
	}


	@Override
	public void showProgress() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void hideProgress() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void showError(String message) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void displayWelcomeMessage(String msg) {
		// TODO Auto-generated method stub
		
	}

}
