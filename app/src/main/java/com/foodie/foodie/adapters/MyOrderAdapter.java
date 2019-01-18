package com.foodie.foodie.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.foodie.foodie.models.Order;
import com.foodie.foodie.R;

import java.util.ArrayList;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.OrderViewHolder> {

    private ArrayList<Order> orders;
    private Context context;

    public MyOrderAdapter(ArrayList<Order> orders, Context context) {
        this.orders = orders;
        this.context = context;
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView foodStatus;
        TextView orderNumber;
        RecyclerView foodOrders;

        OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            foodStatus = itemView.findViewById(R.id.tv_food_status);
            orderNumber = itemView.findViewById(R.id.orderNumber);
            foodOrders = itemView.findViewById(R.id.rv_food_order);

        }
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.my_order,viewGroup,false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder orderViewHolder, int i) {
        Order currentOrder = orders.get(i);
        //set foodStatus textView
        boolean isFinished = currentOrder.isFinished();
        if(!isFinished) {
            orderViewHolder.foodStatus.setText("Cooking");
        }else {
            orderViewHolder.foodStatus.setText("Done");
        }
        //set orderNumber textView
        orderViewHolder.orderNumber.setText(currentOrder.getOrderNumber());
        //populate foodOrders recyclerView
        RecyclerView foodOrders = orderViewHolder.foodOrders;
        foodOrders.setLayoutManager(new LinearLayoutManager(context));
        foodOrders.setHasFixedSize(true);
        MyFoodOrderAdapter adapter = new MyFoodOrderAdapter(currentOrder.getFoodOrders());
        foodOrders.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}
