package com.app.gnometown.View.GnomeList;

import com.app.gnometown.Model.Gnome;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by andreinasarda on 17/4/16.
 */
public class populateListInteractorImpl  implements  populateListInteractor{


    onListPopulatedListener listener;
    onListFilteredListener filteredListener;

    @Override
    public void loadData(onListPopulatedListener listener) {

        this.listener = listener;
        Realm realm = Realm.getDefaultInstance();

        RealmResults<Gnome> gnomes = realm.where(Gnome.class).findAll();

        if(gnomes!=null){

          this.listener.onSuccess(gnomes);
        }
    }

    @Override
    public void filterData(String query,onListFilteredListener listener) {
        filteredListener = listener;

        Realm realm = Realm.getDefaultInstance();

        RealmResults<Gnome> gnomes = realm.where(Gnome.class).contains("name",query).findAll();

        if(gnomes!=null){

            filteredListener.onSuccess(gnomes);
        }

    }
}
