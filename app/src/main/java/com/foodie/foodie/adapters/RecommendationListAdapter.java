package com.foodie.foodie.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.foodie.foodie.R;
import com.foodie.foodie.models.Food;

import java.util.ArrayList;

public class RecommendationListAdapter extends RecyclerView.Adapter<RecommendationListAdapter.RecommendedFoodViewHolder>{

    private ArrayList<Food> foodList;

    public RecommendationListAdapter(ArrayList<Food> foodList) {
        this.foodList = foodList;
    }

    class RecommendedFoodViewHolder extends RecyclerView.ViewHolder {

        private ImageView foodImage;
        private TextView foodName;
        private TextView price;

        RecommendedFoodViewHolder(View itemView) {
            super(itemView);
            foodImage = itemView.findViewById(R.id.iv_foodImage);
            foodName = itemView.findViewById(R.id.tv_foodName);
            price = itemView.findViewById(R.id.tv_price);
        }

    }

    @NonNull
    @Override
    public RecommendedFoodViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.recommended_food,viewGroup,false);
        return new RecommendedFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedFoodViewHolder recommendedFoodViewHolder, int position) {
        Food currentFood = foodList.get(position);
        recommendedFoodViewHolder.foodName.setText(currentFood.getTH_name());
        double price = currentFood.getPrice();
        if ((price == Math.floor(price)) && !Double.isInfinite(price)) {
            recommendedFoodViewHolder.price.setText(String.valueOf((int)currentFood.getPrice()));
        } else {
            recommendedFoodViewHolder.price.setText(String.valueOf((int)currentFood.getPrice()));
        }

        if(currentFood.getImageReference() != null) {
            Glide.with(recommendedFoodViewHolder.itemView.getContext()).load(currentFood.getImageReference()).into(recommendedFoodViewHolder.foodImage);
        }
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }
}
