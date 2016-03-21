package com.shelly.solarmonitor.presentation.ui.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.shelly.solarmonitor.domin.model.CardItemModel;
import com.shelly.solarmonitor.presentation.ui.fragment.DisplayFragment;

public class DisplayContainerPagerAdapter extends FragmentPagerAdapter {

	private List<CardItemModel> mCategoryList = null;

	public DisplayContainerPagerAdapter(FragmentManager fm, List<CardItemModel> categoryList) {
		super(fm);
		mCategoryList = categoryList;
	}

	@Override
	public int getCount() {
		return null != mCategoryList ? mCategoryList.size() : 0;
	}

	@Override
	public Fragment getItem(int position) {
		return new DisplayFragment();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return null != mCategoryList ? mCategoryList.get(position).getTitle() : null;
	}
}
