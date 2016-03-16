package com.shelly.solarmonitor.presentation.ui.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import butterknife.InjectView;

import com.shelly.library.eventbus.EventCenter;
import com.shelly.library.netstatus.NetUtils.NetType;
import com.shelly.solarmonitor.domin.model.CardItemModel;
import com.shelly.solarmonitor.presentation.ui.activity.base.BaseSwipeBackActivity;
import com.shelly.solarmonitor.presentation.ui.adapter.AboutRecyclerAdapter;
import com.ty.solarmonitor.R;

public class AboutActivity extends BaseSwipeBackActivity {
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
	
    private List<CardItemModel> cardItems = new ArrayList<CardItemModel>(10);


//	setSupportActionBar(mToolbar);
//	getSupportActionBar().setTitle("");
//	getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//	getSupportActionBar().setDisplayShowHomeEnabled(true);
//	getSupportActionBar().setHomeButtonEnabled(true);
	
	@Override
	protected void getBundleExtras(Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected int getContentViewLayoutID() {
		return R.layout.about;
	}


	@Override
	protected View getLoadingTargetView() {
		return mRecyclerView;
	}

	@Override
	protected void initViewsAndEvents() {
		setUpRecyclerView();
		mCollapsingToolbarLayout.setTitle("SolarMonitor");
		mFabFeedback.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showToast("feedback");
			}
		});
	}

	private void setUpRecyclerView() {
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		mRecyclerView.setHasFixedSize(true);
        initializeCardItemList();
        mRecyclerView.setAdapter(new AboutRecyclerAdapter(cardItems));
	}

	 private void initializeCardItemList(){
        CardItemModel cardItemModel;
        String[] cardTitles = getResources().getStringArray(R.array.card_titles);
        String[] cardContents = getResources().getStringArray(R.array.card_contents);
        final int length = cardTitles.length/3;
        for(int i=0;i<length;i++){
            cardItemModel = new CardItemModel(cardTitles[i],cardContents[i]);
            cardItems.add(cardItemModel);
        }
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemId = item.getItemId();
		switch (itemId) {
		case android.R.id.home:
			onBackPressed();
			return true;
		case R.id.action_settings:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected boolean isApplyKitKatTranslucency() {
		return false;
	}

	@Override
	protected void onEventComming(EventCenter eventCenter) {
		// TODO Auto-generated method stub
		
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

}
