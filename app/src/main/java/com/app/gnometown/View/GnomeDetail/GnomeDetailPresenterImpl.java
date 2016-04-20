package com.app.gnometown.View.GnomeDetail;

import com.app.gnometown.Model.Gnome;

/**
 * Created by andreinasarda on 19/4/16.
 */
public class GnomeDetailPresenterImpl implements
        GnomeDetailPresenter,
        populateDetailInteractor.onDetailLoadedListener {

    GnomeDetailView view;
    populateDetailInteractor populateDetailInteractor;
    int id ;

    public GnomeDetailPresenterImpl(GnomeDetailView view,int id) {
        this.view = view;
        this.id=id;

        populateDetailInteractor = new populateDetailInteractorImpl();
    }

    @Override
    public void onResume() {

        if(view!=null){

            populateDetailInteractor.loadDetail(this,id);
        }

    }

    @Override
    public void onDestroy() {

        view = null;

    }



    @Override
    public void onSuccess(Gnome gnome) {

        view.setGnome(gnome);

    }

    @Override
    public void onFail() {

        //There's no gnome :s How is this possible???

    }
}
