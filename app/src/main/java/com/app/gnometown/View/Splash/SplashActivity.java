package com.app.gnometown.View.Splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import com.app.gnometown.R;
import com.app.gnometown.View.GnomeList.GnomeListActivity;
import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends AppCompatActivity implements SplashView {

    SplashPresenter presenter;

    @Bind(R.id.loader)
    ImageView loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        presenter = new SplashPresenterImpl(this,this);

      //  runnable.run();

    }

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {

        }
    };


    @Override
    public void showLoader() {
        loader.setVisibility(View.VISIBLE);
        loader.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate));
    }

    @Override
    public void hideLoader() {
        loader.setVisibility(View.GONE);

    }

    @Override
    public void showMessage() {
        Toast.makeText(this,"Data loaded",Toast.LENGTH_SHORT);
    }

    @Override
    public void goToMain() {

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
