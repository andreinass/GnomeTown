package com.app.gnometown.View.GnomeList;

import android.view.View;

import com.app.gnometown.Model.Gnome;
import com.app.gnometown.R;

import java.util.List;
import io.realm.RealmResults;

/**
 * Created by andreinasarda on 17/4/16.
 */
public class GnomeListPresenterImpl implements GnomeListPresenter,
        populateListInteractor.onListPopulatedListener,
        populateListInteractor.onListFilteredListener
{

    GnomeListView view;

    populateListInteractor populateListInteractor;

    public GnomeListPresenterImpl(GnomeListView view) {

        this.view = view;
        populateListInteractor = new populateListInteractorImpl();
    }

    @Override
    public void onResume() {

        if(view!=null){

            populateListInteractor.loadData(this);
        }
    }

    @Override
    public void onItemClick(Gnome gnome) {
        view.goToDetail(gnome.getId());
    }


    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void filterAdapter(String query) {

        populateListInteractor.filterData(query,this);

    }

    @Override
    public void onSuccess(RealmResults<Gnome> gnomes) {

        List<Gnome> g = gnomes.subList(0,gnomes.size());
        view.setItems(g);
    }

    @Override
    public void onFail() {

    }


    @Override
    public void onFail(String message) {

        view.showMessage(message);
    }
}
