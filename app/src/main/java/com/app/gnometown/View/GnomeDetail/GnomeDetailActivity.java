package com.app.gnometown.View.GnomeDetail;

import android.graphics.Typeface;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.app.gnometown.Model.Gnome;
import com.app.gnometown.R;
import com.app.gnometown.connection.cache.ImageCacheManager;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GnomeDetailActivity extends AppCompatActivity implements GnomeDetailView {

    public static final String EXTRA_ID ="id";

    @Bind(R.id.list_friends)
    TextView friends;

    @Bind(R.id.list_profesion)
    TextView professions;

    @Bind(R.id.value_weight)
    TextView weight;

    @Bind(R.id.value_height)
    TextView height;

    @Bind(R.id.value_age)
    TextView age;

    @Bind(R.id.imageViewGnome)
    NetworkImageView image;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.value_hair)
    TextView hair;

    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    GnomeDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gnome_detail);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        int id = getIntent().getIntExtra(EXTRA_ID,0);

        presenter = new GnomeDetailPresenterImpl(this,id);

        final Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/AvenirLTStd-Medium.otf");
        collapsingToolbar.setCollapsedTitleTypeface(tf);
        collapsingToolbar.setExpandedTitleTypeface(tf);

    }

    @Override
    public void setGnome(Gnome gnome) {

        //name.setText(gnome.getName());
        age.setText(String.valueOf(gnome.getAge()));
        hair.setText(gnome.getHair_color());
        weight.setText(String.valueOf(gnome.getWeight()));
        height.setText(String.valueOf(gnome.getHeight()));
        collapsingToolbar.setTitle(gnome.getName());

        //Populating professions


        StringBuilder p = new StringBuilder();
        for (int i=0;i< gnome.getProfessions().size();i++){

            p.append(gnome.getProfessions().get(i).getString()+"\n");

        }

        professions.setText(p.toString());

        //Populating friends
        StringBuilder f = new StringBuilder();
        for (int i=0;i< gnome.getFriends().size()-1;i++){

            f.append(gnome.getFriends().get(i).getString()+"\n");

        }

       friends.setText(f.toString());
        image.setImageUrl(gnome.getThumbnail(), ImageCacheManager.getInstance().getImageLoader());


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
