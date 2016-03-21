package com.shelly.solarmonitor.presentation.ui.fragment;

import java.util.List;

import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.InjectView;

import com.shelly.library.eventbus.EventCenter;
import com.shelly.library.smartlayout.SmartTabLayout;
import com.shelly.library.widgets.XViewPager;
import com.shelly.solarmonitor.domin.executor.impl.ThreadExecutor;
import com.shelly.solarmonitor.domin.model.CardItemModel;
import com.shelly.solarmonitor.presentation.presenter.DisplayContainerPresenter;
import com.shelly.solarmonitor.presentation.presenter.impl.DisplayContainerPresenterImpl;
import com.shelly.solarmonitor.presentation.ui.activity.base.BaseFragment;
import com.shelly.solarmonitor.presentation.ui.adapter.DisplayContainerPagerAdapter;
import com.shelly.solarmonitor.storage.DisplayContainerRepositoryImpl;
import com.shelly.solarmonitor.threading.MainThreadImpl;
import com.ty.solarmonitor.R;

public class DisplayContainerFragment extends BaseFragment implements
		DisplayContainerPresenter.DisplayContainerView {

	@InjectView(R.id.display_viewpager)
	XViewPager mViewPager;
	@InjectView(R.id.display_tab_smart)
	SmartTabLayout mSmartTabLayout;

	private DisplayContainerPresenter mDisplayContainerPresenter = null;

	public DisplayContainerFragment() {
		super();
	}

	@Override
	protected void onFirstUserVisible() {
//		mDisplayContainerPresenter = new DisplayContainerPresenterImpl(ThreadExecutor.getInstance(),
//				MainThreadImpl.getInstance(), this, new DisplayContainerRepositoryImpl(getActivity()));
	}
	
	@Override
	public void onResume() {
		super.onResume();
		mDisplayContainerPresenter = new DisplayContainerPresenterImpl(ThreadExecutor.getInstance(),
				MainThreadImpl.getInstance(), this, new DisplayContainerRepositoryImpl(getActivity()));
		mDisplayContainerPresenter.resume();
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
	protected View getLoadingTargetView() {
		return null;
	}

	@Override
	protected void initViewsAndEvents() {
		// TODO Auto-generated method stub
	}

	@Override
	protected int getContentViewLayoutID() {
		return R.layout.fragment_display;
	}

	@Override
	protected void onEventComming(EventCenter eventCenter) {
		// TODO Auto-generated method stub
	}

	@Override
	protected boolean isBindEventBusHere() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void initializePagerViews(final List<CardItemModel> categoryList) {
		if (null != categoryList && !categoryList.isEmpty()) {
			mViewPager.setOffscreenPageLimit(categoryList.size());
			mViewPager
					.setAdapter(new DisplayContainerPagerAdapter(getSupportFragmentManager(), categoryList));
			mSmartTabLayout.setViewPager(mViewPager);
			mSmartTabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
				@Override
				public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

				}

				@Override
				public void onPageSelected(int position) {
					DisplayFragment fragment = (DisplayFragment) mViewPager.getAdapter().instantiateItem(
							mViewPager, position);
					fragment.onPageSelected(position, categoryList.get(position).getId());
				}

				@Override
				public void onPageScrollStateChanged(int state) {

				}
			});
		}
	}
}
