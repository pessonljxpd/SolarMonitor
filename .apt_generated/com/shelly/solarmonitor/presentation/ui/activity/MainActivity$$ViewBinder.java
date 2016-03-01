// Generated code from Butter Knife. Do not modify!
package com.shelly.solarmonitor.presentation.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainActivity$$ViewBinder<T extends com.shelly.solarmonitor.presentation.ui.activity.MainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427433, "field 'mTv'");
    target.mTv = finder.castView(view, 2131427433, "field 'mTv'");
    view = finder.findRequiredView(source, 2131427434, "field 'mBtnJumpSetting' and method 'startSettingActivity'");
    target.mBtnJumpSetting = finder.castView(view, 2131427434, "field 'mBtnJumpSetting'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.startSettingActivity();
        }
      });
    view = finder.findRequiredView(source, 2131427432, "field 'mToolbar'");
    target.mToolbar = finder.castView(view, 2131427432, "field 'mToolbar'");
  }

  @Override public void unbind(T target) {
    target.mTv = null;
    target.mBtnJumpSetting = null;
    target.mToolbar = null;
  }
}
