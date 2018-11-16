package com.foodie.foodie;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class ShopActivity extends AppCompatActivity {

    private RecyclerView mVendorList;
    private VendorListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        ArrayList<Vendor> vendors = getDataFromDatabase();

        mVendorList = findViewById(R.id.rv_vendor_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mVendorList.setLayoutManager(layoutManager);
        mVendorList.setHasFixedSize(true);
        mListAdapter = new VendorListAdapter(vendors);
        mVendorList.setAdapter(mListAdapter);

    }

    private ArrayList<Vendor> getDataFromDatabase() {

        final ArrayList<Vendor> vendors = new ArrayList<>();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Vendors").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Map<String,Object> data = document.getData();
                        Log.d("Foodie-SA",data.get("TH_name").toString());
                        //why arrayList not adding element?
                        vendors.add(new Vendor(data.get("TH_name").toString(),data.get("EN_name").toString(),R.drawable.ic_launcher_background));
                    }
                } else {
                    Log.w("Foodie-SA", "Error getting documents.", task.getException());
                }
            }
        });

        Log.d("Foodie-SA",String.valueOf(vendors.size()));

        return vendors;

    }

}
