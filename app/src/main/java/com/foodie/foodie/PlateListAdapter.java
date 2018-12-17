package com.foodie.foodie;

import android.app.Activity;
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
    private Activity context;

    PlateListAdapter(Activity context,ArrayList plates) {
        this.plates = plates;
        this.context = context;
    }

    class PlateViewHolder extends RecyclerView.ViewHolder {

        ArrayList<Food> toppings;
        ImageButton removeButton;
        ListView toppingListView;

        PlateViewHolder(View itemView) {
            super(itemView);
            toppings = new ArrayList<>();
            //retrieve data from Firebase
            toppings.add(new Food("Add cheese","เพิ่มชีส",10,null));
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
        ToppingListAdapter toppingListAdapter = new ToppingListAdapter(context,plateViewHolder.toppings);
        plateViewHolder.toppingListView.setAdapter(toppingListAdapter);
    }

    @Override
    public int getItemCount() {
        return plates.size();
    }
}
