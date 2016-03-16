package com.shelly.solarmonitor.presentation.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.shelly.solarmonitor.presentation.ui.fragment.DisplayContentFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

	public ViewPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
		case 0:
			return new DisplayContentFragment();
		case 1:
			return new DisplayContentFragment();
		case 2:
			return new DisplayContentFragment();
		case 3:
			return new DisplayContentFragment();
		case 4:
			return new DisplayContentFragment();
		case 5:
			return new DisplayContentFragment();
		case 6:
			return new DisplayContentFragment();
		case 7:
			return new DisplayContentFragment();
		default:
			return null;
		}
	}

	@Override
	public int getCount() {
		return 8;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		switch (position) {
		case 0:
			return "Tab 1";
		case 1:
			return "Tab 2";
		case 2:
			return "Tab 3";
		case 3:
			return "Tab 4";
		case 4:
			return "Tab 5";
		case 5:
			return "Tab 6";
		case 6:
			return "Tab 7";
		case 7:
			return "Tab 8";
		default:
			return null;
		}
	}
}
