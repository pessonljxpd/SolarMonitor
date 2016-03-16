package com.shelly.solarmonitor.presentation.ui.fragment;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.ButterKnife;

import com.shelly.library.eventbus.EventCenter;
import com.shelly.library.smartlayout.SmartTabLayout;
import com.shelly.solarmonitor.presentation.ui.activity.WindFarmActivity;
import com.shelly.solarmonitor.presentation.ui.activity.base.BaseFragment;
import com.shelly.solarmonitor.presentation.ui.adapter.ViewPagerAdapter;
import com.ty.solarmonitor.R;

public class DisplayFragment extends BaseFragment {

	private Toolbar mToolbar;
	private ViewPager mVP;
	
	private SmartTabLayout mSmartTabLayout;

	private WindFarmActivity mActivity;

	private void setUpTabLayout() {
		mSmartTabLayout.setViewPager(mVP);
//		mTabLayout.setupWithViewPager(mVP);
	}

	private void setUpViewPager() {
		mVP.setAdapter(new ViewPagerAdapter(getChildFragmentManager()));
	}

	private void setToolbar() {
		mToolbar.setTitle(getString(R.string.tab_fragment_title));
		mActivity.setSupportActionBar(mToolbar);
	}

	@Override
	protected View getLoadingTargetView() {
		return null;
	}

	@Override
	protected void initViewsAndEvents() {
		this.mActivity = (WindFarmActivity) getActivity();
		mToolbar = ButterKnife.findById(mActivity, R.id.tab_toolbar);
		mVP = ButterKnife.findById(mActivity, R.id.tab_view_pager);
		mSmartTabLayout = ButterKnife.findById(mActivity, R.id.tab_smart);
		setToolbar();
		setUpViewPager();
		setUpTabLayout();
		mActivity.setupNavigationDrawer(mToolbar);	
	}

	@Override
	protected int getContentViewLayoutID() {
		return R.layout.fragment_display;
	}
	
	@Override
	public void showLoading(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hideLoading() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showException(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showNetError() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onFirstUserVisible() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onUserVisible() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onUserInvisible() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onEventComming(EventCenter eventCenter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isBindEventBusHere() {
		return false;
	}

}
