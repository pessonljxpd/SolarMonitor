package com.shelly.solarmonitor.presentation.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.InjectView;

import com.shelly.library.eventbus.EventCenter;
import com.shelly.library.netstatus.NetUtils.NetType;
import com.shelly.solarmonitor.presentation.presenter.MainPresenter;
import com.shelly.solarmonitor.presentation.ui.activity.base.BaseSwipeBackActivity;
import com.shelly.solarmonitor.presentation.ui.fragment.DisplayFragment;
import com.shelly.solarmonitor.presentation.ui.fragment.SetAppFragment;
import com.ty.solarmonitor.R;

public class WindFarmActivity extends BaseSwipeBackActivity implements
		NavigationView.OnNavigationItemSelectedListener {
	private static int sSelectedIndex;

	private final static String DISPLAY_TAG = "display";
	// private final static String ABOUT_FRAGMENT_TAG = "about";
	private final static String FAB_FRAGMENT_TAG = "fab";
	private final static String SELECTED_TAG = "selected_index";
	private final static int DISPLAY = 1;
	private final static int FAB = 2;
	private final static int ABOUT = 3;

	@InjectView(R.id.fragment_container)
	FrameLayout mFrameLayout;
	@InjectView(R.id.navigation_view)
	NavigationView mNavigationView;
	@InjectView(R.id.drawer_layout)
	DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mActionBarDrawerToggle;

	private MainPresenter mPresenter;

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(SELECTED_TAG, sSelectedIndex);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.navigation_menu, menu);
		return true;
	}
	
	@Override
	public boolean onNavigationItemSelected(MenuItem menuItem) {

		switch (menuItem.getItemId()) {
		case R.id.item_collector_list:
			if (!menuItem.isChecked()) {
				sSelectedIndex = DISPLAY;
				menuItem.setChecked(true);
				getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.fragment_container,
								new DisplayFragment(), DISPLAY_TAG).commit();
			}
			mDrawerLayout.closeDrawer(GravityCompat.START);
			return true;
		case R.id.item_setting:
			if (!menuItem.isChecked()) {
				sSelectedIndex = FAB;
				menuItem.setChecked(true);
				getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.fragment_container, new SetAppFragment(),
								FAB_FRAGMENT_TAG).commit();
			}
			mDrawerLayout.closeDrawer(GravityCompat.START);
			return true;
		case R.id.item_about:
			if (!menuItem.isChecked()) {
				startActivity(new Intent(WindFarmActivity.this,
						AboutActivity.class));
			}
			return true;
		}
		return false;
	}

	public void setupNavigationDrawer(Toolbar toolbar) {
		mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				toolbar, R.string.open_drawer, R.string.close_drawer) {
			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
			}

			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
			}
		};
		mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
		mActionBarDrawerToggle.syncState();
	}

	@Override
	protected boolean isApplyKitKatTranslucency() {
		return false;
	}

	private void setUpNavigationView() {
		mNavigationView.setNavigationItemSelectedListener(this);
	}

	private Bundle mExtras;
	
	@Override
	protected void getBundleExtras(Bundle extras) {
		this.mExtras = extras;
	}

	@Override
	protected int getContentViewLayoutID() {
		return R.layout.main;
	}

	@Override
	protected void onEventComming(EventCenter eventCenter) {
		// TODO Auto-generated method stub

	}

	@Override
	protected View getLoadingTargetView() {
		return mFrameLayout;
	}

	@Override
	protected void initViewsAndEvents() {
		setUpNavigationView();
		if (mExtras != null) {
			mNavigationView.getMenu().getItem(mExtras.getInt(SELECTED_TAG))
					.setChecked(true);
			return;
		}
		
		sSelectedIndex = DISPLAY;
		getSupportFragmentManager()
				.beginTransaction()
				.add(R.id.fragment_container, new DisplayFragment(),
						DISPLAY_TAG).commit();
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
		return true;
	}

	@Override
	protected boolean isBindEventBusHere() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean toggleOverridePendingTransition() {
		return true;
	}

	@Override
	protected TransitionMode getOverridePendingTransitionMode() {
		return TransitionMode.RIGHT;
	}

}
