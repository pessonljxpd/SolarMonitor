package com.shelly.solarmonitor.presentation.presenter;

import com.shelly.solarmonitor.presentation.presenter.base.BasePresenter;
import com.shelly.solarmonitor.presentation.ui.BaseView;

public interface MainPresenter extends BasePresenter {

	interface View extends BaseView {
		void displayWelcomeMessage(String msg);
	}
}
