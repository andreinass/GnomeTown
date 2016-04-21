package com.app.gnometown.View.adapter;

import com.app.gnometown.Model.Gnome;

/**
 * Created by andreinasarda on 18/4/16.
 *
 * Handles click items on RecyclerView
 */
public interface RecyclerViewItemClickListener {

    void onItemClick(Gnome gnome);
}
