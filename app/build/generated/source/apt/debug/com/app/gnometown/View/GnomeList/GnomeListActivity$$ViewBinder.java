// Generated code from Butter Knife. Do not modify!
package com.app.gnometown.View.GnomeList;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class GnomeListActivity$$ViewBinder<T extends com.app.gnometown.View.GnomeList.GnomeListActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492988, "field 'gnomesRecycler'");
    target.gnomesRecycler = finder.castView(view, 2131492988, "field 'gnomesRecycler'");
    view = finder.findRequiredView(source, 2131492975, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131492975, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131492973, "field 'collapsingToolbar'");
    target.collapsingToolbar = finder.castView(view, 2131492973, "field 'collapsingToolbar'");
  }

  @Override public void unbind(T target) {
    target.gnomesRecycler = null;
    target.toolbar = null;
    target.collapsingToolbar = null;
  }
}
