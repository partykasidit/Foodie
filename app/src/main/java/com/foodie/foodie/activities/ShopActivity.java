package com.foodie.foodie.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.foodie.foodie.R;
import com.foodie.foodie.models.Vendor;
import com.foodie.foodie.adapters.VendorListAdapter;
import com.foodie.foodie.utils.MarginItemDecoration;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Nullable;

public class ShopActivity extends AppCompatActivity {

    private RecyclerView mVendorList;
    private VendorListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setTitle("Vendors");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Vendors").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                ArrayList<Vendor> vendors = new ArrayList<>();
                if (e != null) {
                    Log.w("Foodie-SA", "listen:error", e);
                    return;
                }

                for (DocumentChange dc : queryDocumentSnapshots.getDocumentChanges()) {
                    Map<String,Object> data = dc.getDocument().getData();
                    vendors.add(new Vendor(data.get("TH_name").toString(),data.get("EN_name").toString(),data.get("vendorImageReference").toString()));

                }
                mVendorList = findViewById(R.id.rv_vendor_list);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                mVendorList.setLayoutManager(layoutManager);
                mVendorList.setHasFixedSize(true);
                mListAdapter = new VendorListAdapter(vendors);
                mVendorList.setAdapter(mListAdapter);
                mVendorList.addItemDecoration(new MarginItemDecoration((int) getResources().getDimension(R.dimen.default_padding)));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
