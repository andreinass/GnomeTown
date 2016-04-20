// Generated code from Butter Knife. Do not modify!
package com.app.gnometown.View.GnomeDetail;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class GnomeDetailActivity$$ViewBinder<T extends com.app.gnometown.View.GnomeDetail.GnomeDetailActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492987, "field 'friends'");
    target.friends = finder.castView(view, 2131492987, "field 'friends'");
    view = finder.findRequiredView(source, 2131492985, "field 'professions'");
    target.professions = finder.castView(view, 2131492985, "field 'professions'");
    view = finder.findRequiredView(source, 2131492981, "field 'weight'");
    target.weight = finder.castView(view, 2131492981, "field 'weight'");
    view = finder.findRequiredView(source, 2131492983, "field 'height'");
    target.height = finder.castView(view, 2131492983, "field 'height'");
    view = finder.findRequiredView(source, 2131492977, "field 'age'");
    target.age = finder.castView(view, 2131492977, "field 'age'");
    view = finder.findRequiredView(source, 2131492974, "field 'image'");
    target.image = finder.castView(view, 2131492974, "field 'image'");
    view = finder.findRequiredView(source, 2131492975, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131492975, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131492979, "field 'hair'");
    target.hair = finder.castView(view, 2131492979, "field 'hair'");
    view = finder.findRequiredView(source, 2131492973, "field 'collapsingToolbar'");
    target.collapsingToolbar = finder.castView(view, 2131492973, "field 'collapsingToolbar'");
  }

  @Override public void unbind(T target) {
    target.friends = null;
    target.professions = null;
    target.weight = null;
    target.height = null;
    target.age = null;
    target.image = null;
    target.toolbar = null;
    target.hair = null;
    target.collapsingToolbar = null;
  }
}
