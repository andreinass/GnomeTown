package com.app.gnometown.View.Splash;

import android.app.Activity;

/**
 * Created by andreinasarda on 17/4/16.
 */
public interface loadDataInteractor {

    interface onLoadDataListener{

        void onSuccess();

        void onFail();
    }

    void loadData(Activity a,onLoadDataListener listener);
}
