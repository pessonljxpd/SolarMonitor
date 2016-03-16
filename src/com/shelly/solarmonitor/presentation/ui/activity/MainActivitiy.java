package com.shelly.solarmonitor.presentation.ui.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.shelly.library.eventbus.EventCenter;
import com.shelly.library.netstatus.NetUtils.NetType;
import com.shelly.solarmonitor.domin.model.CardItemModel;
import com.shelly.solarmonitor.presentation.ui.activity.base.BaseActivity;
import com.shelly.solarmonitor.presentation.ui.fragment.AboutFragment;
import com.shelly.solarmonitor.presentation.ui.fragment.CollectorListFragment;
import com.shelly.solarmonitor.presentation.ui.fragment.SettingFragment;
import com.ty.solarmonitor.R;

public class MainActivitiy extends BaseActivity implements OnNavigationItemSelectedListener {

	private static long DOUBLE_CLICK_TIME = 0L;

	private NavigationView mNavigationView;
	private DrawerLayout mDrawerLayout;
	private FrameLayout mFrameLayout;

	private ActionBarDrawerToggle mActionBarDrawerToggle;
	private static int sSelectedIndex;
	private final static String SELECTED_TAG = "selected_tag";
	private final static String COLLECTOR_LIST_FRAGMENT_TAG = "collector_list_fragment_tag";
	private final static String SETTING_FRAGMENT_TAG = "setting_fragment_tag";
	private final static String ABOUT_FRAGMENT_TAG = "about_fragment_tag";
	private final static int COLLECTOR_LIST_FRAGMENT = 1;
	private final static int SETTING_FRAGMENT = 2;
	private final static int ABOUT_FRAGMENT = 3;
	private final static int USER_FRAGMENT = 9;

	private Context mContext;
	private List<CardItemModel> cardItems = new ArrayList<CardItemModel>(10);

	private void setUpNavigationView() {
		mNavigationView.setNavigationItemSelectedListener(this);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(SELECTED_TAG, sSelectedIndex);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		int itemId = item.getItemId();
		switch (itemId) {
		case R.id.item_collector_list:
			if (!item.isChecked()) {
				sSelectedIndex = COLLECTOR_LIST_FRAGMENT;
				item.setChecked(true);
				getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.fragment_container, new CollectorListFragment(),
								COLLECTOR_LIST_FRAGMENT_TAG).commit();

			}
			mDrawerLayout.closeDrawers();
			return true;
		case R.id.item_setting:
			if (!item.isChecked()) {
				sSelectedIndex = SETTING_FRAGMENT;
				item.setChecked(true);
				getSupportFragmentManager().beginTransaction()
						.replace(R.id.fragment_container, new SettingFragment(), SETTING_FRAGMENT_TAG)
						.commit();
			}
			mDrawerLayout.closeDrawers();
			return true;
		case R.id.item_about:
			if (!item.isChecked()) {
				sSelectedIndex = ABOUT_FRAGMENT;
				item.setChecked(true);
				getSupportFragmentManager().beginTransaction()
						.replace(R.id.fragment_container, new AboutFragment(), ABOUT_FRAGMENT_TAG).commit();
			}
			mDrawerLayout.closeDrawers();
			return true;
		}
		return false;
	}

	public void setupNavigationDrawer(Toolbar toolbar) {
		mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
				R.string.open_drawer, R.string.close_drawer) {
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

	private Bundle mExtras;

	@Override
	protected void getBundleExtras(Bundle extras) {
		this.mExtras = extras;
	}

	@Override
	protected int getContentViewLayoutID() {
		return R.layout.data_collector;
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
		this.mContext = this;
		mDrawerLayout = ButterKnife.findById(this, R.id.drawer_layout);
		mFrameLayout = ButterKnife.findById(this, R.id.fragment_container);
		mNavigationView = ButterKnife.findById(this, R.id.navigation_view);

		setUpNavigationView();
		if (mExtras != null) {
			mNavigationView.getMenu().getItem(mExtras.getInt(SELECTED_TAG)).setChecked(true);
			return;
		}
		sSelectedIndex = COLLECTOR_LIST_FRAGMENT;
		getSupportFragmentManager().beginTransaction()
				.add(R.id.fragment_container, new CollectorListFragment(), COLLECTOR_LIST_FRAGMENT_TAG)
				.commit();
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

	@Override
	public void showLoading(String msg) {
		toggleShowLoading(true, "努力加载中...");
	}

	@Override
	public void hideLoading() {
		toggleShowLoading(false, null);
	}

	@Override
	public void showError(String msg) {
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
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_MENU) {
			if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
				mDrawerLayout.closeDrawer(Gravity.LEFT);
			} else {
				mDrawerLayout.openDrawer(Gravity.LEFT);
			}
			return true;
		} else if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
			if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
				mDrawerLayout.closeDrawer(Gravity.LEFT);
			} else {
				if ((System.currentTimeMillis() - DOUBLE_CLICK_TIME) > 2000) {
					showToast(getString(R.string.double_click_exit));
					DOUBLE_CLICK_TIME = System.currentTimeMillis();
				} else {
					getBaseApplication().exitApp();
				}
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
