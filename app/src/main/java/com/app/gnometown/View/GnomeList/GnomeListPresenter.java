package com.app.gnometown.View.GnomeList;

import android.view.View;

import com.app.gnometown.Model.Gnome;

/**
 * Created by andreinasarda on 17/4/16.
 */
public interface GnomeListPresenter {

    void onResume();

    void onItemClick(Gnome gnome);

    void onDestroy();

    void filterAdapter(String query);
}
