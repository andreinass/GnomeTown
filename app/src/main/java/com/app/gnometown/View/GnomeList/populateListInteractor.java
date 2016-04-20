package com.app.gnometown.View.GnomeList;

import com.app.gnometown.Model.Gnome;

import io.realm.RealmResults;

/**
 * Created by andreinasarda on 17/4/16.
 */
public interface populateListInteractor {

    interface onListPopulatedListener{

        void onSuccess(RealmResults<Gnome> gnomes);

        void onFail();
    }

    interface onListFilteredListener {

        void onSuccess(RealmResults<Gnome> gnomes);

        void onFail();
    }

    void loadData(onListPopulatedListener listener);

    void filterData(String query,onListFilteredListener listener);
}
