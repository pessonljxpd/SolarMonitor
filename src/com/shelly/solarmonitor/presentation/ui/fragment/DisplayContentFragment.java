package com.shelly.solarmonitor.presentation.ui.fragment;

import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife;

import com.shelly.library.eventbus.EventCenter;
import com.shelly.solarmonitor.domin.executor.impl.ThreadExecutor;
import com.shelly.solarmonitor.presentation.presenter.MainPresenter;
import com.shelly.solarmonitor.presentation.presenter.impl.MainPresenterImpl;
import com.shelly.solarmonitor.presentation.ui.activity.base.BaseFragment;
import com.shelly.solarmonitor.storage.WtgInfoRepositoryImpl;
import com.shelly.solarmonitor.threading.MainThreadImpl;
import com.ty.solarmonitor.R;

public class DisplayContentFragment extends BaseFragment implements
		MainPresenter.View {

	// @InjectView(R.id.avloading)
	// AVLoadingIndicatorView mAVLoadingView;
	private NestedScrollView mNestedScrollView;
	private TextView mTv;

	private MainPresenter mPresenter;

	@Override
	protected View getLoadingTargetView() {
		mNestedScrollView = ButterKnife.findById(getActivity(), R.id.nested_scrollview);
		return mNestedScrollView;
	}

	@Override
	protected void initViewsAndEvents() {
		mTv = ButterKnife.findById(getActivity(), R.id.tv_content);
		mPresenter = new MainPresenterImpl(ThreadExecutor.getInstance(),
				MainThreadImpl.getInstance(), this, new WtgInfoRepositoryImpl(
						getActivity()));
	}

	@Override
	protected int getContentViewLayoutID() {
		return R.layout.fragment_display_content;
	}

	@Override
	public void onResume() {
		super.onResume();
		mPresenter.resume();
	}

	@Override
	public void displayWtgInfo(String msg) {
		mTv.setText(msg);
	}

	@Override
	public void showLoading(String msg) {
		toggleShowLoading(true, "努力加载中...");
		// mAVLoadingView.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideLoading() {
		toggleShowLoading(false, "");
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
		// TODO Auto-generated method stub
		return false;
	}

}
