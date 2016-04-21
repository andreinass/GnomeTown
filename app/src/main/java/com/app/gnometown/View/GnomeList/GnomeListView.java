package com.app.gnometown.View.GnomeList;

import android.view.View;

import com.app.gnometown.Model.Gnome;

import java.util.List;

import io.realm.RealmResults;

/**
 * Created by andreinasarda on 17/4/16.
 *
 * Interface to habdle GnomeListActivity view
 */
public interface GnomeListView {

    void setItems(List<Gnome> gnomes);

    void goToDetail(int id);

    void showMessage(String message);
}
