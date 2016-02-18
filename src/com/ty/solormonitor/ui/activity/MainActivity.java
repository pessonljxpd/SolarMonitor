package com.ty.solormonitor.ui.activity;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.widget.TextView;

import com.ty.solarmonitor.R;


@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {

	@ViewById(R.id.mTv)
	public TextView mTv;

	@AfterInject
	void init(){
		
		
	}
	
	@AfterViews
	public void initView() {
		mTv.setText("Hello world");
	}

}
