package com.ibrahimkiceci.mapapplication.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Placeholder;
import androidx.recyclerview.widget.RecyclerView;

import com.ibrahimkiceci.mapapplication.MapsActivity;
import com.ibrahimkiceci.mapapplication.databinding.RecyclerRowBinding;
import com.ibrahimkiceci.mapapplication.model.Place;

import java.util.List;

public class MapAdapter extends RecyclerView.Adapter<MapAdapter.PlaceHolder> {

    List<Place>placeList;
    public MapAdapter(List<Place>placeList){

        this.placeList = placeList;


    }

    @NonNull
    @Override
    public PlaceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding recyclerRowBinding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new PlaceHolder(recyclerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceHolder holder, int position) {

        // it will show the location name on the list
        holder.recyclerRowBinding.recyclerViewTextView.setText(placeList.get(position).name);

        // when it is clicked, go to the saved location

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =  new Intent(holder.itemView.getContext(), MapsActivity.class);
                //i will send a putextra. Because we need to get that it is clicked to create new place or saved place. It provides to distinguish these;
                intent.putExtra("locationInfo", "old_data");
                intent.putExtra("place",placeList.get(position));
                holder.itemView.getContext().startActivity(intent);


            }
        });


    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }

    public class PlaceHolder extends RecyclerView.ViewHolder {

        RecyclerRowBinding recyclerRowBinding;
        public PlaceHolder(RecyclerRowBinding recyclerRowBinding) {
            super(recyclerRowBinding.getRoot());
            this.recyclerRowBinding = recyclerRowBinding;
        }
    }

//Map adapter

}
