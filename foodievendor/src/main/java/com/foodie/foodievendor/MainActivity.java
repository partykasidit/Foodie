package com.foodie.foodievendor;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private OrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Orders").document("bXH5xY7FQFqQPtxw6cAU").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                ArrayList<Order> orders = new ArrayList<>();

                if (e != null) {
                    return;
                }

                if (documentSnapshot != null && documentSnapshot.exists()) {
                    for (Object object: documentSnapshot.getData().keySet()){
                        String key = (String) object;
                        if(documentSnapshot.get(key) instanceof Map) {
                            HashMap<String,Object> order = (HashMap<String, Object>)documentSnapshot.get(key);
                            Map<String,Object> foodOrderMap = (Map<String,Object>) order.get("foodOrders");
                            ArrayList<Object> foodOrderArrayList = new ArrayList<Object>(foodOrderMap.values());
                            ArrayList<FoodOrder> foodOrders = new ArrayList<>();
                            for(int i=0;i<foodOrderArrayList.size();i++) {
                                if(foodOrderArrayList.get(i) instanceof Map) {
                                    Map<String,Object> foodOrder = (Map<String,Object>)foodOrderArrayList.get(i);
                                    String foodName = (String) foodOrder.get("foodName");
                                    Long longAmount = (Long) foodOrder.get("amount");
                                    int amount = longAmount.intValue();
                                    foodOrders.add(new FoodOrder(foodName,amount));
                                } else {
                                    Log.e("Foodie-MOA","data format invalid, check database");
                                }
                            }
                            orders.add(new Order(key,(Boolean) order.get("isFinished"),(String) order.get("customerUID"),(String) order.get("vendorName"),foodOrders));
                        } else {
                            Log.e("Foodie-MOA","data format invalid, check database");
                        }
                    }

                    /*Iterator iterator = orders.iterator();
                    while(iterator.hasNext()) {
                        Order currentOrder = (Order) iterator.next();
                        if(!currentOrder.getCustomerUID().equals(userUID)) {
                            iterator.remove();
                        }
                    }*/

                    recyclerView = findViewById(R.id.rv_orders);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    adapter = new OrderAdapter(orders,getApplicationContext());
                    recyclerView.setAdapter(adapter);

                }else {
                    System.out.print("Don't have any orders");
                }

            }
        });

    }

}
