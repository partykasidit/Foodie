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

    public RecyclerView recyclerView;
    //private RecyclerView.Adapter<MyViewHolder> adapter;
    public ArrayList<Order> orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
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
            ArrayList<Order> orders = new ArrayList<>();
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    return;
                }

                if (documentSnapshot != null && documentSnapshot.exists()) {
                    for (Object object: documentSnapshot.getData().keySet()){
                        String key = (String) object;
                        HashMap<String,Object> order = (HashMap<String, Object>)documentSnapshot.get(key);
                        //Log.d("Foodie-MOA",order.toString());
                        Map<String,Object> foodOrderMap = (Map<String,Object>) order.get("foodOrders");
                        ArrayList<Object> foodOrderArrayList = new ArrayList<Object>(foodOrderMap.values());
                        Log.d("Foodie-MOA",foodOrderMap.toString());
                        Log.d("Foodie-MOA",foodOrderArrayList.toString());
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
                        //Log.d("Foodie-MOA",foodOrders.toString());
                        orders.add(new Order(key,(Boolean) order.get("isFinished"),(String) order.get("customerUID"),(String) order.get("vendorName"),foodOrders));
                    }
                }else {
                    System.out.print("Don't have any orders");
                }



            }
        });



        /*recyclerView = new RecyclerView(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        adapter = new RecyclerView.Adapter<MyViewHolder>() {
            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview, viewGroup, false);
                return new MyViewHolder(v);
            }

            @Override
            public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
                myViewHolder.orderNumber.setText(""+dataset.indexOf(i));
                myViewHolder.orderName.setText("");
                myViewHolder.amountNum.setText("");

            }

            @Override
            public int getItemCount() {
                return dataset.size();
            }
        };
        recyclerView.setAdapter(adapter);
        setContentView(recyclerView);*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        TextView orderNumber;
        TextView orderName;
        TextView amountNum;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            orderNumber = (TextView)findViewById(R.id.orderNum);
            orderName = (TextView)findViewById(R.id.orderName);
            amountNum = (TextView)findViewById(R.id.amountNum);
        }
    }
}
