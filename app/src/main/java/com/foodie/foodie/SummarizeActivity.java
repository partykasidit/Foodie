package com.foodie.foodie;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class SummarizeActivity extends AppCompatActivity {

    private static final String TAG = "SummarizeActivity";
    private RecyclerView mSummarizeList;
    private SummarizeListAdaptor listAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summarize);
        Log.d(TAG, "onCreate: started.");


        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setTitle("Summarize");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        ArrayList<Plate> order = intent.getParcelableArrayListExtra("order");
        Log.d("Foodie-SA",order.toString());

        mSummarizeList= findViewById(R.id.my_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mSummarizeList.setLayoutManager(layoutManager);
        mSummarizeList.setHasFixedSize(true);
        listAdaptor= new SummarizeListAdaptor(order);
        mSummarizeList.setAdapter(listAdaptor);

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
