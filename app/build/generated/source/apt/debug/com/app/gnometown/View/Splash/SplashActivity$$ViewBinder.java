// Generated code from Butter Knife. Do not modify!
package com.app.gnometown.View.Splash;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SplashActivity$$ViewBinder<T extends com.app.gnometown.View.Splash.SplashActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492991, "field 'loader'");
    target.loader = finder.castView(view, 2131492991, "field 'loader'");
  }

  @Override public void unbind(T target) {
    target.loader = null;
  }
}
