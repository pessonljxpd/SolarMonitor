// Generated code from Butter Knife. Do not modify!
package com.shelly.solarmonitor.presentation.ui.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class AboutFragment$$ViewInjector {
  public static void inject(Finder finder, final com.shelly.solarmonitor.presentation.ui.fragment.AboutFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165359, "field 'mCL'");
    target.mCL = (android.support.design.widget.CoordinatorLayout) view;
    view = finder.findRequiredView(source, 2131165360, "field 'mRecyclerView'");
    target.mRecyclerView = (android.support.v7.widget.RecyclerView) view;
    view = finder.findRequiredView(source, 2131165381, "field 'mToolbar'");
    target.mToolbar = (android.support.v7.widget.Toolbar) view;
    view = finder.findRequiredView(source, 2131165365, "field 'mFabFeedback'");
    target.mFabFeedback = (android.support.design.widget.FloatingActionButton) view;
    view = finder.findRequiredView(source, 2131165362, "field 'mCollapsingToolbarLayout'");
    target.mCollapsingToolbarLayout = (android.support.design.widget.CollapsingToolbarLayout) view;
  }

  public static void reset(com.shelly.solarmonitor.presentation.ui.fragment.AboutFragment target) {
    target.mCL = null;
    target.mRecyclerView = null;
    target.mToolbar = null;
    target.mFabFeedback = null;
    target.mCollapsingToolbarLayout = null;
  }
}
