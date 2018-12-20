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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

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
            notifyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    String thisOrderNumber = orders.get(getAdapterPosition()).getOrderNumber();
                    String updater = thisOrderNumber + ".isFinished";
                    Log.d("Foodie-OA",updater);
                    db.collection("Orders").document("bXH5xY7FQFqQPtxw6cAU").update(updater,true);
                }
            });
            finishButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    final DocumentReference documentReference = db.collection("Orders").document("bXH5xY7FQFqQPtxw6cAU");
                    documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {

                                    Map<String,Object> data = document.getData();
                                    String thisOrderNumber = orders.get(getAdapterPosition()).getOrderNumber();
                                    data.put(thisOrderNumber,FieldValue.delete());
                                    documentReference.update(data);

                                } else {
                                    Log.d("Foodie Vendor-OA", "No such document");
                                }
                            } else {
                                Log.d("Foodie Vendor-OA", "get failed with ", task.getException());
                            }
                        }
                    });
                }
            });
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
