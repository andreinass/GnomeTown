// Generated code from Butter Knife. Do not modify!
package com.app.gnometown.View.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class GnomeAdapter$MyViewHolder$$ViewBinder<T extends com.app.gnometown.View.adapter.GnomeAdapter.MyViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492974, "field 'gnomeImage'");
    target.gnomeImage = finder.castView(view, 2131492974, "field 'gnomeImage'");
    view = finder.findRequiredView(source, 2131493001, "field 'gnomeName'");
    target.gnomeName = finder.castView(view, 2131493001, "field 'gnomeName'");
    view = finder.findRequiredView(source, 2131493005, "field 'gnomeFriends'");
    target.gnomeFriends = finder.castView(view, 2131493005, "field 'gnomeFriends'");
    view = finder.findRequiredView(source, 2131493003, "field 'gnomeProfessions'");
    target.gnomeProfessions = finder.castView(view, 2131493003, "field 'gnomeProfessions'");
  }

  @Override public void unbind(T target) {
    target.gnomeImage = null;
    target.gnomeName = null;
    target.gnomeFriends = null;
    target.gnomeProfessions = null;
  }
}
