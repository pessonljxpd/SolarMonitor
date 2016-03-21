package com.shelly.solarmonitor.presentation.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import butterknife.InjectView;

import com.shelly.library.eventbus.EventCenter;
import com.shelly.solarmonitor.domin.model.CardItemModel;
import com.shelly.solarmonitor.presentation.ui.activity.MainActivitiy;
import com.shelly.solarmonitor.presentation.ui.activity.base.BaseFragment;
import com.shelly.solarmonitor.presentation.ui.adapter.AboutRecyclerAdapter;
import com.ty.solarmonitor.R;

public class AboutFragment extends BaseFragment {
	@InjectView(R.id.toolbar)
	Toolbar mToolbar;
	@InjectView(R.id.collapsing_toolbar)
	CollapsingToolbarLayout mCollapsingToolbarLayout;
	@InjectView(R.id.fab_feedback)
	FloatingActionButton mFabFeedback;
	@InjectView(R.id.cl_about)
	CoordinatorLayout mCL;
	@InjectView(R.id.recycler_view)
	RecyclerView mRecyclerView;

	private MainActivitiy mActivity;

	private List<CardItemModel> cardItems = new ArrayList<CardItemModel>(10);

	@Override
	protected View getLoadingTargetView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void initViewsAndEvents() {
		this.mActivity = (MainActivitiy) getActivity();
		setHasOptionsMenu(true);
		setUpCollapsingToolbarLayout();
		setUpToolbar();
		setUpNavigationDrawer();
		initData();
	}

	@Override
	protected int getContentViewLayoutID() {
		return R.layout.fragment_about;
	}

	private void setUpCollapsingToolbarLayout() {
		mCollapsingToolbarLayout.setTitle("SolarMonitor");
	}

	protected void initData() {
		setUpRecyclerView();
	}

	private void setUpRecyclerView() {
		mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
		mRecyclerView.setHasFixedSize(true);
		initializeCardItemList();
		mRecyclerView.setAdapter(new AboutRecyclerAdapter(cardItems));
	}

	private void initializeCardItemList() {
		CardItemModel cardItemModel;
		String[] cardTitles = getResources().getStringArray(R.array.card_titles);
		String[] cardContents = getResources().getStringArray(R.array.card_contents);
		final int length = cardTitles.length / 10;
		for (int i = 0; i < length; i++) {
			cardItemModel = new CardItemModel(i, cardTitles[i], cardContents[i]);
			cardItems.add(cardItemModel);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemId = item.getItemId();
		switch (itemId) {
		case R.id.action_settings:
			Toast.makeText(mContext, "About Setting", Toast.LENGTH_SHORT).show();
			return true;
		}
		return false;
	}

	public void setUpToolbar() {
		mActivity.setSupportActionBar(mToolbar);
		mActivity.getSupportActionBar().setTitle("");
	}

	public void setUpNavigationDrawer() {
		mActivity.setupNavigationDrawer(mToolbar);
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
