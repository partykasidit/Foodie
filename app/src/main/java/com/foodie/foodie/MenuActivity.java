package com.foodie.foodie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position",-1);
        Toast.makeText(this,String.valueOf(position),Toast.LENGTH_LONG).show();
    }
}
