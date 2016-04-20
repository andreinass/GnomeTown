package com.app.gnometown.View.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.toolbox.NetworkImageView;
import com.app.gnometown.Model.Gnome;
import com.app.gnometown.R;
import com.app.gnometown.Utils.Utils;
import com.app.gnometown.connection.cache.ImageCacheManager;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by andreinasarda on 17/4/16.
 *
 * GnomeAdapter:
 *
 * Adapter created to load all information about population in GnomeTown.
 *
 *
 */

public class GnomeAdapter extends RecyclerView.Adapter<GnomeAdapter.MyViewHolder> {

    private final LayoutInflater inflater;
    List<Gnome> gnomes;

    RecyclerViewItemClickListener listener ;
    Context context;

    public GnomeAdapter(Context c,List<Gnome> gnomes, RecyclerViewItemClickListener listener) {
        inflater = LayoutInflater.from(c);
        this.gnomes = gnomes;
        this.listener = listener;
        context = c;
    }

    @Override
    public int getItemCount() {
        return gnomes.size();
    }

     class MyViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.imageViewGnome)
        NetworkImageView gnomeImage;

         @Bind(R.id.textViewGnomeName)
         TextView gnomeName;

         @Bind(R.id.textViewGnomeFriendsCount)
         TextView gnomeFriends;

         @Bind(R.id.textViewGnomeProfessionCount)
         TextView gnomeProfessions;

         @Bind(R.id.imageFriends)
         ImageView friendsIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(getItem(getAdapterPosition()));
                }
            });
        }

     }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_card_gnome, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Gnome gnome = gnomes.get(position);

        holder.gnomeImage.setImageUrl(gnome.getThumbnail(), ImageCacheManager.getInstance().getImageLoader());
        holder.gnomeImage.setDefaultImageResId(Utils.randomColors());
        holder.gnomeName.setText(gnome.getName());

        if(gnome.getFriends().size()==0) {


            holder.friendsIcon.setImageResource(R.drawable.ic_sad);


        }else{

            holder.friendsIcon.setImageResource(R.drawable.ic_happy);

        }
        String friends = context.getResources().getQuantityString(R.plurals.friends, gnome.getFriends().size(), gnome.getFriends().size());
        holder.gnomeFriends.setText(friends);

        String professions = context.getResources().getQuantityString(R.plurals.profession, gnome.getProfessions().size(), gnome.getProfessions().size());
        holder.gnomeProfessions.setText(professions);

    }

    public Gnome getItem(int position){

        return gnomes.get(position);
    }



    public void setGnomes(List<Gnome> gnomes) {
        this.gnomes = gnomes;
    }
}

