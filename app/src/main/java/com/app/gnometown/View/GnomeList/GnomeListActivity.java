package com.app.gnometown.View.GnomeList;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.app.gnometown.Model.Gnome;
import com.app.gnometown.R;
import com.app.gnometown.View.GnomeDetail.GnomeDetailActivity;
import com.app.gnometown.View.adapter.GnomeAdapter;
import com.app.gnometown.View.adapter.RecyclerViewItemClickListener;

import java.util.Collections;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

public class GnomeListActivity extends AppCompatActivity implements
        GnomeListView,
        RecyclerViewItemClickListener{

    @Bind(R.id.town_list)
    RecyclerView gnomesRecycler;

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    GnomeListPresenter presenter;

    GnomeAdapter adapter;
    List<Gnome> population= Collections.emptyList();
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        presenter = new GnomeListPresenterImpl(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.list_menu, menu);


        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextChange(String newText) {


                if (newText.equals("") ) {



                }

                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {

                presenter.filterAdapter(query);

                return false;
            }

        });


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void setItems(List<Gnome> gnomes) {

        population= gnomes;

        gnomesRecycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new GnomeAdapter(this,gnomes,this);
        gnomesRecycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void goToDetail(int id) {

        Intent i = new Intent(this, GnomeDetailActivity.class);
        i.putExtra(GnomeDetailActivity.EXTRA_ID,id);
        startActivity(i);

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

    @Override
    public void onItemClick(Gnome gnome) {
        presenter.onItemClick(gnome);

    }
}
