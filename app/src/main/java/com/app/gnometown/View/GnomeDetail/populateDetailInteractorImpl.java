package com.app.gnometown.View.GnomeDetail;

import com.app.gnometown.Model.Gnome;

import io.realm.Realm;

/**
 * Created by andreinasarda on 19/4/16.
 */
public class populateDetailInteractorImpl implements
        populateDetailInteractor{


    @Override
    public void loadDetail(onDetailLoadedListener listener,int id) {

        //Finding gnome that matches with id, and show detail
        Realm realm = Realm.getDefaultInstance();

        Gnome gnome = realm.where(Gnome.class).equalTo("id",id).findFirst();

        if(gnome!=null) {

            listener.onSuccess(gnome);

        }else{
            //Gnome is not found, this should not happend because all info was loaded in realm at beginning
            listener.onFail();
        }
    }
}
