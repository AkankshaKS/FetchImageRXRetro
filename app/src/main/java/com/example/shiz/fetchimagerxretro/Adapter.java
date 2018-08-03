package com.example.shiz.fetchimagerxretro;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

     List<Worldpopulation> List;
     Context context;

    public Adapter(List<Worldpopulation> List) {
        this.List = List;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_row, viewGroup, false);
        ViewHolder myHolder = new ViewHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        Worldpopulation world = List.get(i);
        viewHolder.mrank.setText("RANK - " + String.valueOf(world.getRank()));
        viewHolder.mcountry.setText("COUNTRY - " + world.getCountry());
        viewHolder.mpopu.setText("POPULATION - " + String.valueOf(world.getPopulation()));
        String image1 = world.getFlag();
        Picasso.get().load(image1).into(viewHolder.img_android);
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mrank,mpopu,mcountry;
        ImageView img_android;
        public ViewHolder(View view) {
            super(view);

            img_android = (ImageView)view.findViewById(R.id.img_android);
            mrank = (TextView)view.findViewById(R.id.rank);
            mpopu = (TextView)view.findViewById(R.id.population);
            mcountry = (TextView)view.findViewById(R.id.name);

        }
    }
}
