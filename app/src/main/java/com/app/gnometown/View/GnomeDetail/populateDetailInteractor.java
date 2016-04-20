package com.app.gnometown.View.GnomeDetail;

import com.app.gnometown.Model.Gnome;

/**
 * Created by andreinasarda on 19/4/16.
 */
public interface populateDetailInteractor {

    interface onDetailLoadedListener{

        void onSuccess(Gnome gnome);

        void onFail();
    }


    void loadDetail(onDetailLoadedListener listener,int id);
}
