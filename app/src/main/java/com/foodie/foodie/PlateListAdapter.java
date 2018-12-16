package com.foodie.foodie;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class PlateListAdapter extends RecyclerView.Adapter<PlateListAdapter.PlateViewHolder>{

    private ArrayList<Plate> plates;

    PlateListAdapter(ArrayList plates) {
        this.plates = plates;
    }

    class PlateViewHolder extends RecyclerView.ViewHolder {

        ArrayList<Food> toppings;
        ImageButton removeButton;
        ListView toppingListView;

        public PlateViewHolder(View itemView) {
            super(itemView);
            toppingListView = itemView.findViewById(R.id.lv_toppings);
            removeButton = itemView.findViewById(R.id.ib_remove_button);
        }

    }

    @NonNull
    @Override
    public PlateViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.plate,viewGroup,false);
        return new PlateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlateViewHolder plateViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
