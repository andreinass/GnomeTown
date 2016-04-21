package com.app.gnometown.View.Splash;

import android.app.Activity;
import android.os.Handler;

/**
 * Created by andreinasarda on 17/4/16.
 */
public class SplashPresenterImpl implements
        SplashPresenter,
        loadDataInteractor.onLoadDataListener
{

    Activity activity;
    SplashView view;
    loadDataInteractor loadDataInteractor;

    public SplashPresenterImpl(Activity a,SplashView view) {

        activity = a;
        this.view= view;

        loadDataInteractor = new loadDataInteractorImpl();
    }


    @Override
    public void onResume() {

        if(activity!=null){

            view.showLoader();




            loadDataInteractor.loadData(activity,SplashPresenterImpl.this);

        }

    }

    @Override
    public void onDestroy() {

        view =null;

    }

    @Override
    public void onSuccess() {

        view.hideLoader();
        view.goToMain();
    }

    @Override
    public void onFail(String msg) {
        view.hideLoader();
        view.showMessage(msg);

    }
}
