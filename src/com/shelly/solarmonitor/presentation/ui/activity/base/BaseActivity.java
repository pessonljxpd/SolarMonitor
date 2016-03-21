package com.shelly.solarmonitor.presentation.ui.activity.base;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import butterknife.InjectView;

import com.shelly.library.R;
import com.shelly.library.base.BaseAppCompatActivity;
import com.shelly.solarmonitor.SolarMonitorApplication;
import com.shelly.solarmonitor.presentation.ui.BaseView;

public abstract class BaseActivity extends BaseAppCompatActivity implements BaseView {
	@InjectView(R.id.common_toolbar)
    protected Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isApplyKitKatTranslucency()) {
            setSystemBarTintDrawable(getResources().getDrawable(R.drawable.sr_primary));
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        if (null != mToolbar) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    protected SolarMonitorApplication getBaseApplication() {
        return (SolarMonitorApplication) getApplication();
    }

    @Override
    public void showError(String msg) {
        toggleShowError(true, msg, null);
    }

    @Override
    public void showException(String msg) {
        toggleShowError(true, msg, null);
    }

    @Override
    public void showNetError() {
        toggleNetworkError(true, null);
    }

    @Override
    public void showLoading(String msg) {
        toggleShowLoading(true, null);
    }

    @Override
    public void hideLoading() {
        toggleShowLoading(false, null);
    }

    protected abstract boolean isApplyKitKatTranslucency();
}

