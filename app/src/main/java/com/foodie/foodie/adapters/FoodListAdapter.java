package com.foodie.foodie.adapters;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.foodie.foodie.models.Food;
import com.foodie.foodie.models.Plate;
import com.foodie.foodie.R;

import java.util.ArrayList;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.FoodViewHolder>{

    private ArrayList<Food> foodList;
    private ArrayList<Plate> plates;
    private FragmentManager fragmentManager;

    public FoodListAdapter(ArrayList<Food> foodList,FragmentManager fragmentManager) {
        this.foodList = foodList;
        this.plates = new ArrayList<>();
        this.fragmentManager = fragmentManager;
    }

    public ArrayList<Plate> getOrder() {
        return plates;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.food,viewGroup,false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder foodViewHolder, int position) {
        Food currentFood = foodList.get(position);
        foodViewHolder.foodName.setText(currentFood.getTH_name());
        double price = currentFood.getPrice();
        if ((price == Math.floor(price)) && !Double.isInfinite(price)) {
            foodViewHolder.price.setText(String.valueOf((int)currentFood.getPrice()));
        } else {
            foodViewHolder.price.setText(String.valueOf((int)currentFood.getPrice()));
        }

        if(currentFood.getImageReference() != null) {
            Glide.with(foodViewHolder.itemView.getContext()).load(currentFood.getImageReference()).into(foodViewHolder.foodImage);
        }

    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    class FoodViewHolder extends RecyclerView.ViewHolder {

        ImageView foodImage;
        TextView foodName;
        TextView price;
        TextView plateCounter;
        int counter = 0;
        CardView cardView;
        ImageButton addButton;
        ImageButton removeButton;

        FoodViewHolder(final View itemView) {
            super(itemView);
            foodImage = itemView.findViewById(R.id.iv_foodImage);
            foodName = itemView.findViewById(R.id.tv_foodName);
            price = itemView.findViewById(R.id.tv_price);
            plateCounter = itemView.findViewById(R.id.tv_counter);
            cardView = itemView.findViewById(R.id.cv_food);
            /*cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DialogFragment dialogFragment = new ToppingFragment();
                    dialogFragment.show(fragmentManager,"Topping");
                }
            });*/
            addButton = itemView.findViewById(R.id.ib_add_button);
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    plates.add(new Plate(foodList.get(getAdapterPosition())));
                    counter++;
                    plateCounter.setText(String.valueOf(counter));
                    if(counter > 0) {
                        plateCounter.setVisibility(View.VISIBLE);
                    }
                }
            });
            removeButton = itemView.findViewById(R.id.ib_remove_button);
            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean removed = plates.remove(new Plate(foodList.get(getAdapterPosition())));
                    if(counter > 0 && removed) {
                        counter--;
                    }
                    plateCounter.setText(String.valueOf(counter));
                    if(counter <= 0) {
                        plateCounter.setVisibility(View.INVISIBLE);
                    }
                }
            });

        }

    }

}
