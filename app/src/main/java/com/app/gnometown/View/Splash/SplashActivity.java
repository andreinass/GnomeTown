package com.app.gnometown.View.Splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.app.gnometown.R;
import com.app.gnometown.View.GnomeList.GnomeListActivity;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *
 * Full Screen Activity, init activity, load information about gnomes
 *
 */
public class SplashActivity extends AppCompatActivity implements SplashView {

    SplashPresenter presenter;

    @Bind(R.id.loader)
    ImageView loader;

    @Bind(R.id.text)
    TextView welcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        presenter = new SplashPresenterImpl(this,this);
    }

    @Override
    public void showLoader() {

        //starting loader to get data from service
        loader.startAnimation(AnimationUtils.loadAnimation(SplashActivity.this, R.anim.rotate));

    }

    @Override
    public void hideLoader() {

        loader.getAnimation().cancel();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT);
    }

    @Override
    public void goToMain() {
        //Gnome's list
        Intent i = new Intent(this, GnomeListActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
