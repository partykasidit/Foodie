package com.foodie.foodie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity {

    private RecyclerView mVendorList;
    private VendorListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        ArrayList<Vendor> vendors = new ArrayList<>();
        vendors.add(new Vendor("ทูเดย์ สเต๊ก","Today's Steak"));
        vendors.add(new Vendor("ทูเดย์ สเต๊ก (อาหารตามสั่ง)","Today's Steak (À la carte)"));

        mVendorList = findViewById(R.id.rv_vendor_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mVendorList.setLayoutManager(layoutManager);
        mVendorList.setHasFixedSize(true);
        mListAdapter = new VendorListAdapter(vendors);
        mVendorList.setAdapter(mListAdapter);



    }


}
