// Generated code from Butter Knife. Do not modify!
package com.shelly.solarmonitor.presentation.ui.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class CollectorListFragment$$ViewInjector {
  public static void inject(Finder finder, final com.shelly.solarmonitor.presentation.ui.fragment.CollectorListFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165384, "field 'mToolbar'");
    target.mToolbar = (android.support.v7.widget.Toolbar) view;
    view = finder.findRequiredView(source, 2131165382, "field 'mCL'");
    target.mCL = (android.support.design.widget.CoordinatorLayout) view;
    view = finder.findRequiredView(source, 2131165385, "field 'mFabRetry'");
    target.mFabRetry = (android.support.design.widget.FloatingActionButton) view;
    view = finder.findRequiredView(source, 2131165360, "field 'mRecyclerView'");
    target.mRecyclerView = (android.support.v7.widget.RecyclerView) view;
  }

  public static void reset(com.shelly.solarmonitor.presentation.ui.fragment.CollectorListFragment target) {
    target.mToolbar = null;
    target.mCL = null;
    target.mFabRetry = null;
    target.mRecyclerView = null;
  }
}
