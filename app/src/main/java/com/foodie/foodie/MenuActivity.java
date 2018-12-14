package com.foodie.foodie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Nullable;

public class MenuActivity extends AppCompatActivity {

    private RecyclerView mFoodList;
    private FoodListAdapter mFoodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        String selectedVendor = intent.getStringExtra("selectedVendor");

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Menus").document(selectedVendor).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                ArrayList<Food> foods = new ArrayList<>();
                if (e != null) {
                    Log.w("Foodie-MA", "listen:error", e);
                    return;
                }

                if (documentSnapshot != null && documentSnapshot.exists()) {
                    Log.d("Foodie-MA", "Current data: " + documentSnapshot.getData());
                    for(Object object : documentSnapshot.getData().keySet()) {
                        String key = (String) object;
                        HashMap<String,Object> food = (HashMap<String, Object>) documentSnapshot.get(key);
                        foods.add(new Food(food.get("EN_name").toString(),food.get("TH_name").toString(),Double.valueOf(food.get("price").toString()),food.get("foodImageReference").toString()));
                    }
                    mFoodList = findViewById(R.id.rv_food_list);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    mFoodList.setLayoutManager(layoutManager);
                    mFoodList.setHasFixedSize(true);
                    mFoodAdapter = new FoodListAdapter(foods);
                    mFoodList.setAdapter(mFoodAdapter);
                } else {
                    Log.d("Foodie-MA", "Current data: null");
                }

            }
        });

//        public static ArrayList<FoodInStock> getArrayListWithoutZero(DocumentSnapshot documentSnapshot) {
//            ArrayList<FoodInStock> foodList = new ArrayList<>();
//            for(Object object : documentSnapshot.getData().keySet()) {
//                String key = (String) object;
//                HashMap<String,Object> food = (HashMap<String, Object>) documentSnapshot.get(key);
//                FoodInStock foodInStock = getFoodInStock(food);
//                if(foodInStock.getQuantityLeft()!=0) {
//                    foodList.add(getFoodInStock(food));
//                }
//            }
//            return foodList;
//        }







    }
}
