// Generated code from Butter Knife. Do not modify!
package com.shelly.solarmonitor.presentation.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class WindFarmActivity$$ViewInjector {
  public static void inject(Finder finder, final com.shelly.solarmonitor.presentation.ui.activity.WindFarmActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165373, "field 'mFrameLayout'");
    target.mFrameLayout = (android.widget.FrameLayout) view;
    view = finder.findRequiredView(source, 2131165374, "field 'mNavigationView'");
    target.mNavigationView = (android.support.design.widget.NavigationView) view;
    view = finder.findRequiredView(source, 2131165372, "field 'mDrawerLayout'");
    target.mDrawerLayout = (android.support.v4.widget.DrawerLayout) view;
  }

  public static void reset(com.shelly.solarmonitor.presentation.ui.activity.WindFarmActivity target) {
    target.mFrameLayout = null;
    target.mNavigationView = null;
    target.mDrawerLayout = null;
  }
}
