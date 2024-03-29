package com.foodie.foodie.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.foodie.foodie.R;
import com.foodie.foodie.activities.LoginActivity;
import com.foodie.foodie.activities.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences sharedPreferences = this.getSharedPreferences("FirebaseUser",Context.MODE_PRIVATE);
        String userUID = sharedPreferences.getString("UserUID",null);
        if(userUID != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

}