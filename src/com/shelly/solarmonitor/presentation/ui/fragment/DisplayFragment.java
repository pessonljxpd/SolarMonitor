package com.shelly.solarmonitor.presentation.ui.fragment;

import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import butterknife.InjectView;

import com.shelly.library.eventbus.EventCenter;
import com.shelly.library.netstatus.NetUtils;
import com.shelly.solarmonitor.domin.executor.impl.ThreadExecutor;
import com.shelly.solarmonitor.presentation.presenter.DisplayPresenter;
import com.shelly.solarmonitor.presentation.presenter.impl.DisplayPresenterImpl;
import com.shelly.solarmonitor.presentation.ui.activity.base.BaseFragment;
import com.shelly.solarmonitor.storage.DisplayInfoRepositoryImpl;
import com.shelly.solarmonitor.threading.MainThreadImpl;
import com.ty.solarmonitor.R;

public class DisplayFragment extends BaseFragment implements DisplayPresenter.DisplayView{
	@InjectView(R.id.nested_scrollview)
	NestedScrollView mNestedScrollView;
	@InjectView(R.id.tv_content)
	TextView mTv;

	private DisplayPresenter mDisplayPresenter = null;
	private int mCurrentPage = 0;

	@Override
	protected void onFirstUserVisible() {
//		mCurrentPage = 0;
//		mDisplayPresenter = new DisplayPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(),
//				this, new DisplayInfoRepositoryImpl(getActivity()));
//		if (NetUtils.isNetworkConnected(getActivity())) {
//			//TODO
//		}else{
//			toggleNetworkError(true, new OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					showToast("测试触发刷新");
//				}
//			});
//		}
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
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	protected View getLoadingTargetView() {
		return mNestedScrollView;
	}

	@Override
	protected void initViewsAndEvents() {
		//TODO 
	}

	@Override
	protected int getContentViewLayoutID() {
		return R.layout.fragment_display_list;
	}

	@Override
	public void onResume() {
		super.onResume();
		mCurrentPage = 0;
		mDisplayPresenter = new DisplayPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(),
				this, new DisplayInfoRepositoryImpl(getActivity()));
		if (NetUtils.isNetworkConnected(getActivity())) {
			//TODO
		}else{
			toggleNetworkError(true, new OnClickListener() {
				@Override
				public void onClick(View v) {
					showToast("测试触发刷新");
				}
			});
		}
		mDisplayPresenter.resume();
	}

	@Override
	public void showLoading(String msg) {
		toggleShowLoading(true, "努力加载中...");
	}

	@Override
	public void hideLoading() {
		toggleShowLoading(false, "");
	}

	@Override
	public void displayWtgInfo(String message) {
		mTv.setText(message);
	}
	
	public void onPageSelected(int position, int id) {
		mCurrentPage = id;
	}

}
