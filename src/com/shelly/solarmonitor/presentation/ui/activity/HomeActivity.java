package com.shelly.solarmonitor.presentation.ui.activity;

import java.util.List;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.shelly.library.adapter.ListViewDataAdapter;
import com.shelly.library.adapter.ViewHolderBase;
import com.shelly.library.adapter.ViewHolderCreator;
import com.shelly.library.base.BaseLazyFragment;
import com.shelly.library.eventbus.EventCenter;
import com.shelly.library.netstatus.NetUtils.NetType;
import com.shelly.library.widgets.XViewPager;
import com.shelly.solarmonitor.domin.executor.impl.ThreadExecutor;
import com.shelly.solarmonitor.domin.model.CardItemModel;
import com.shelly.solarmonitor.presentation.presenter.HomePresenter;
import com.shelly.solarmonitor.presentation.presenter.HomePresenter.HomeView;
import com.shelly.solarmonitor.presentation.presenter.impl.HomePresenterImpl;
import com.shelly.solarmonitor.presentation.ui.activity.base.BaseActivity;
import com.shelly.solarmonitor.presentation.ui.adapter.VPFragmentAdapter;
import com.shelly.solarmonitor.storage.HomeRepositoryImpl;
import com.shelly.solarmonitor.threading.MainThreadImpl;
import com.ty.solarmonitor.R;

public class HomeActivity extends BaseActivity implements HomeView {
	@InjectView(R.id.frame_content)
	FrameLayout mFrameLayout;
	@InjectView(R.id.container_viewpager)
	XViewPager mViewPager;
	@InjectView(R.id.home_drawer)
	DrawerLayout mDrawerLayout;
	@InjectView(R.id.home_navigation_list)
	ListView mNavListView;

	private HomePresenter mPresenter;

	private static long DOUBLE_CLICK_TIME = 0L;
	private int mCurrentMenuCheckedPos = 0;
	private ActionBarDrawerToggle mActionBarDrawerToggle = null;
	private ListViewDataAdapter<CardItemModel> mNavListAdapter = null;

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
		mPresenter = new HomePresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this,
				new HomeRepositoryImpl(this));
	}

	@Override
	protected void onResume() {
		super.onResume();
		initializeActionBarDrawerToggle();
		mPresenter.resume();
	}

	private void initializeActionBarDrawerToggle() {
		mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
				R.string.drawer_open, R.string.drawer_close) {

			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				mToolbar.setTitle(R.string.app_name);
			}

			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				if (null != mNavListAdapter) {
					mToolbar.setTitle(mNavListAdapter.getItem(mCurrentMenuCheckedPos).getTitle());
				}
			}
		};

		mActionBarDrawerToggle.setDrawerIndicatorEnabled(true);
		mActionBarDrawerToggle.syncState();
		mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
	}

	@Override
	public void initializeViews(List<BaseLazyFragment> fragments, List<CardItemModel> navigationList) {
		if (null != fragments && fragments.isEmpty()) {
			mViewPager.setEnabled(false);
			mViewPager.setOffscreenPageLimit(fragments.size());
			mViewPager.setAdapter(new VPFragmentAdapter(getSupportFragmentManager(), fragments));
		}

		mNavListAdapter = new ListViewDataAdapter<CardItemModel>(new ViewHolderCreator<CardItemModel>() {

			@Override
			public ViewHolderBase<CardItemModel> createViewHolder(int position) {

				return new ViewHolderBase<CardItemModel>() {
					ImageView itemIcon;
					TextView itemName;

					@Override
					public View createView(LayoutInflater layoutInflater) {
						View convertView = layoutInflater.inflate(R.layout.navigation_list_item, null);
						itemIcon = ButterKnife.findById(convertView, R.id.list_item_navigation_icon);
						itemName = ButterKnife.findById(convertView, R.id.list_item_navigation_name);
						return convertView;
					}

					@Override
					public void showData(int position, CardItemModel itemData) {
						itemIcon.setImageResource(itemData.getIconResId());
						itemName.setText(itemData.getTitle());

						if (mCurrentMenuCheckedPos == position) {
							// checked
							itemName.setTextColor(getResources().getColor(R.color.primary_dark));
						} else {
							// unchecked
							itemName.setTextColor(getResources().getColor(android.R.color.black));
						}
					}
				};

			}
		});

		mNavListView.setAdapter(mNavListAdapter);
		mNavListAdapter.getDataList().addAll(navigationList);
		mNavListAdapter.notifyDataSetChanged();
		setTitle(mNavListAdapter.getItem(mCurrentMenuCheckedPos).getTitle());

		mNavListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				mCurrentMenuCheckedPos = position;
				mNavListAdapter.notifyDataSetChanged();
				mDrawerLayout.closeDrawer(Gravity.LEFT);
				mViewPager.setCurrentItem(mCurrentMenuCheckedPos, false);
			}
		});
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
		return false;
	}

	@Override
	protected boolean isBindEventBusHere() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean toggleOverridePendingTransition() {
		return false;
	}

	@Override
	protected TransitionMode getOverridePendingTransitionMode() {
		return null;
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		if (mActionBarDrawerToggle != null) {
			mActionBarDrawerToggle.onConfigurationChanged(newConfig);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mActionBarDrawerToggle != null && mActionBarDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		switch (item.getItemId()) {
		case R.id.action_search:
			readyGo(AboutActivity.class);
			break;
		case R.id.action_about_us:
			readyGo(AboutActivity.class);
			break;
		case R.id.action_feedback:
			readyGo(AboutActivity.class);
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void getBundleExtras(Bundle extras) {

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

	@Override
	protected boolean isApplyKitKatTranslucency() {
		return true;
	}
}
