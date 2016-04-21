package com.app.gnometown.View.Splash;

import com.app.gnometown.Model.Response;

/**
 * Created by andreinasarda on 17/4/16.
 *
 * Interface to hable SplashActivity View
 */
public interface SplashView {

    void showLoader();

    void hideLoader();

    void showMessage(String message);

    void goToMain();



}
