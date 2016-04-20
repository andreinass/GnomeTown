package com.app.gnometown.View.GnomeList;

import com.app.gnometown.Model.Gnome;

import java.util.List;

import io.realm.RealmResults;

/**
 * Created by andreinasarda on 17/4/16.
 */
public interface GnomeListView {

    void setItems(List<Gnome> gnomes);

    void goToDetail(int id);
}
