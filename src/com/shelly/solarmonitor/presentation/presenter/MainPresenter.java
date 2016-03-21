package com.shelly.solarmonitor.presentation.presenter;

import com.shelly.solarmonitor.presentation.presenter.base.BasePresenter;
import com.shelly.solarmonitor.presentation.ui.BaseView;

public interface MainPresenter extends BasePresenter {

	public interface View extends BaseView {
		void displayCollectorInfo(String msg);
	}
}
 