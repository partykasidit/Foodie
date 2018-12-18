package com.foodie.foodie;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SummarizeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summarize);

        Intent intent = getIntent();
        ArrayList<Plate> order = intent.getParcelableArrayListExtra("order");
        Log.d("Foodie-SA",order.toString());

    }
}
