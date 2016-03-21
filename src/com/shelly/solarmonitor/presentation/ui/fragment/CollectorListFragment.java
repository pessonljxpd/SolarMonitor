package com.shelly.solarmonitor.presentation.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.InjectView;

import com.shelly.library.eventbus.EventCenter;
import com.shelly.solarmonitor.domin.model.CardItemModel;
import com.shelly.solarmonitor.presentation.presenter.MainPresenter;
import com.shelly.solarmonitor.presentation.ui.activity.MainActivitiy;
import com.shelly.solarmonitor.presentation.ui.activity.HomeActivity;
import com.shelly.solarmonitor.presentation.ui.activity.base.BaseFragment;
import com.shelly.solarmonitor.presentation.ui.adapter.CollectorRecyclerAdapter;
import com.shelly.solarmonitor.presentation.ui.adapter.CollectorRecyclerAdapter.OnRecyclerViewListener;
import com.ty.solarmonitor.R;

public class CollectorListFragment extends BaseFragment implements MainPresenter.View {

	@InjectView(R.id.tab_toolbar)
	Toolbar mToolbar;
	@InjectView(R.id.fab_retry)
	FloatingActionButton mFabRetry;
	@InjectView(R.id.cl_data_collector)
	CoordinatorLayout mCL;
	@InjectView(R.id.recycler_view)
	RecyclerView mRecyclerView;

	private MainActivitiy mActivity;

	private List<CardItemModel> cardItems = new ArrayList<CardItemModel>(10);

	private void setUpRecyclerView() {
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		mRecyclerView.setHasFixedSize(true);
		initializeCardItemList();
		CollectorRecyclerAdapter mRecyclerAdapter = new CollectorRecyclerAdapter(cardItems);
		mRecyclerView.setAdapter(mRecyclerAdapter);
		mRecyclerAdapter.setOnRecyclerViewListener(new OnRecyclerViewListener() {

			@Override
			public boolean onItemLongClick(int position) {
				showToast(position + 1 + "is onLongClick");
				return true;
			}

			@Override
			public void onItemClick(int position) {
				showToast(position + 1 + "is onClick");
				readyGo(HomeActivity.class);
			}
		});
	}

	private void initializeCardItemList() {
		CardItemModel cardItemModel;
		String[] cardTitles = getResources().getStringArray(R.array.card_titles);
		String[] cardContents = getResources().getStringArray(R.array.card_contents);
		final int length = cardTitles.length;
		for (int i = 0; i < length; i++) {
			cardItemModel = new CardItemModel(i, cardTitles[i], cardContents[i]);
			cardItems.add(cardItemModel);
		}
	}

	public void setUpToolbar() {
		mActivity.setSupportActionBar(mToolbar);
		mActivity.getSupportActionBar().setTitle("Collector List");
	}

	public void setUpNavigationDrawer() {
		mActivity.setupNavigationDrawer(mToolbar);
	}

	@Override
	protected View getLoadingTargetView() {
		return mRecyclerView;
	}

	@Override
	protected void initViewsAndEvents() {
		this.mActivity = (MainActivitiy) getActivity();
		setUpToolbar();
		setUpRecyclerView();
		setUpNavigationDrawer();
	}

	@Override
	protected int getContentViewLayoutID() {
		return R.layout.fragment_collector_list;
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

	@Override
	public void displayCollectorInfo(String msg) {
		// TODO Auto-generated method stub

	}

}
