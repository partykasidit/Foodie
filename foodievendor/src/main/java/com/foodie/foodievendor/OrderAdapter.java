package com.foodie.foodievendor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder>{

    private ArrayList<Order> orders;
    private Context context;

    public OrderAdapter(ArrayList<Order> orders, Context context) {
        this.orders = orders;
        this.context = context;
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView orderNumber;
        RecyclerView foodOrders;
        ImageButton notifyButton;
        ImageButton finishButton;

        OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderNumber = itemView.findViewById(R.id.tv_orderNumber);
            foodOrders = itemView.findViewById(R.id.rv_food_order);
            notifyButton = itemView.findViewById(R.id.ib_notify_button);
            finishButton = itemView.findViewById(R.id.ib_finish_button);
        }

    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.order,viewGroup,false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder orderViewHolder, int i) {
        Order currentOrder = orders.get(i);
        //set orderNumber textView
        orderViewHolder.orderNumber.setText(currentOrder.getOrderNumber());
        //populate foodOrders recyclerView
        RecyclerView foodOrders = orderViewHolder.foodOrders;
        foodOrders.setLayoutManager(new LinearLayoutManager(context));
        foodOrders.setHasFixedSize(true);
        FoodOrderAdapter adapter = new FoodOrderAdapter(currentOrder.getFoodOrders());
        foodOrders.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}
