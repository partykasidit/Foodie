package com.foodie.foodie;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyFoodOrderAdapter extends RecyclerView.Adapter<MyFoodOrderAdapter.FoodOrderViewHolder>{

    private ArrayList<FoodOrder> foodOrders;

    public MyFoodOrderAdapter(ArrayList<FoodOrder> foodOrders) {
        this.foodOrders = foodOrders;
    }

    class FoodOrderViewHolder extends RecyclerView.ViewHolder {

        TextView foodName;
        TextView amount;

        public FoodOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.tv_foodName);
            amount = itemView.findViewById(R.id.tv_amount);
        }
    }

    @NonNull
    @Override
    public FoodOrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.my_food_order,viewGroup,false);
        return new FoodOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodOrderViewHolder foodOrderViewHolder, int i) {
        FoodOrder currentFoodOrder = foodOrders.get(i);
        foodOrderViewHolder.foodName.setText(currentFoodOrder.getFoodName());
        foodOrderViewHolder.amount.setText(String.valueOf(currentFoodOrder.getAmount()));
    }

    @Override
    public int getItemCount() {
        return foodOrders.size();
    }
}
