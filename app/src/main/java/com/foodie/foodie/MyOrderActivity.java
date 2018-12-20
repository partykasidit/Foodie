package com.foodie.foodie;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class MyOrderActivity extends AppCompatActivity {
    Toolbar toolbar;

    private RecyclerView recyclerView;
    private MyOrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("  My Order");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        SharedPreferences sharedPreferences = this.getSharedPreferences("FirebaseUser",Context.MODE_PRIVATE);
        final String userUID = sharedPreferences.getString("UserUID",null);

        if(userUID != null) {
            Log.d("Foodie-SA",userUID);
        }

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Orders").document("bXH5xY7FQFqQPtxw6cAU").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                final ArrayList<Order> orders = new ArrayList<>();

                if (e != null) {
                    return;
                }

                if (documentSnapshot != null && documentSnapshot.exists()) {
                    for (Object object: documentSnapshot.getData().keySet()){
                        String key = (String) object;
                        HashMap<String,Object> order = (HashMap<String, Object>)documentSnapshot.get(key);
                        Map<String,Object> foodOrderMap = (Map<String,Object>) order.get("foodOrders");
                        ArrayList<Object> foodOrderArrayList = new ArrayList<Object>(foodOrderMap.values());
                        ArrayList<FoodOrder> foodOrders = new ArrayList<>();
                        for(int i=0;i<foodOrderArrayList.size();i++) {
                            Log.d("Foodie-MOA",foodOrderArrayList.get(i).toString());
                            Map<String,Object> foodOrder = (Map<String,Object>)foodOrderArrayList.get(i);
                            String foodName = (String) foodOrder.get("foodName");
                            Long longAmount = (Long) foodOrder.get("amount");
                            int amount = longAmount.intValue();
                            Log.d("Foodie-MOA","foodName : " + foodName + " , " + "amount : " + amount );
                            foodOrders.add(new FoodOrder(foodName,amount));
                        }
                        orders.add(new Order(key,(Boolean) order.get("isFinished"),(String) order.get("customerUID"),(String) order.get("vendorName"),foodOrders));
                    }
                    recyclerView = findViewById(R.id.rv_orders);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    adapter = new MyOrderAdapter(orders,getApplicationContext());
                    recyclerView.setAdapter(adapter);

                }else {
                    System.out.print("Don't have any orders");
                }

            }
        });
    }


}
