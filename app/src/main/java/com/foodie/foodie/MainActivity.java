package com.foodie.foodie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecommendationList;
    private RecommendationListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.hide();
        }

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Menus").document("Recommendations").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                ArrayList<Food> foods = new ArrayList<>();
                if (e != null) {
                    Log.w("Foodie-MA", "listen:error", e);
                    return;
                }

                if (documentSnapshot != null && documentSnapshot.exists()) {
                    for(Object object : documentSnapshot.getData().keySet()) {
                        String key = (String) object;
                        HashMap<String,Object> food = (HashMap<String, Object>) documentSnapshot.get(key);
                        if(food.containsKey("EN_name") && food.containsKey("TH_name") && food.containsKey("price") && food.containsKey("foodImageReference")) {
                            foods.add(new Food(food.get("EN_name").toString(),food.get("TH_name").toString(),Double.valueOf(food.get("price").toString()),food.get("foodImageReference").toString()));
                        }

                    }
                    mRecommendationList = findViewById(R.id.rv_recommendations);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    mRecommendationList.setLayoutManager(layoutManager);
                    mRecommendationList.setHasFixedSize(true);
                    mAdapter = new RecommendationListAdapter(foods);
                    mRecommendationList.setAdapter(mAdapter);
                } else {
                    Log.d("Foodie-MA", "Current data: null");
                }
            }
        });

    }

    public void onShopButtonClicked(View view) {
        Intent intent = new Intent(MainActivity.this,ShopActivity.class);
        startActivity(intent);
    }

    public void onLoginButtonClicked(View view){
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
    }

    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
