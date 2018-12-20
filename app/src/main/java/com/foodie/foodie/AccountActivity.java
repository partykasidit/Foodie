package com.foodie.foodie;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountActivity extends AppCompatActivity {

    private Button mLogoutBtn;
    private FirebaseAuth mAuth;
    private Button mHomeBtn;
    private Context mContext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        mContext = this;

        mLogoutBtn = (Button)findViewById(R.id.logoutBtn);
        mHomeBtn = (Button)findViewById(R.id.homeBtn);

        //Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //Status bar color
        getWindow().setStatusBarColor(Color.WHITE);

        mLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                LoginManager.getInstance().logOut();
                updateUI();

            }
        });

        mHomeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                updateUI2();
            }
        });

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            SharedPreferences sharedPreferences = mContext.getSharedPreferences("FirebaseUser",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("UserUID",currentUser.getUid());
            editor.apply();
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            updateUI();
        }
    }

    private void updateUI() {

        SharedPreferences sharedPreferences = getSharedPreferences("FirebaseUser",Context.MODE_PRIVATE);
        sharedPreferences.edit().remove("UserUID").apply();

        Toast.makeText(AccountActivity.this, "You're logged out", Toast.LENGTH_LONG).show();

        Intent accountIntent = new Intent(AccountActivity.this, MainActivity.class);
        startActivity(accountIntent);
        finish();

    }
    private void updateUI2() {
        Toast.makeText(AccountActivity.this,"Please make your Order",Toast.LENGTH_LONG).show();
        Intent accountIntent = new Intent(AccountActivity.this,MainActivity.class);
        startActivity(accountIntent);
        finish();
    }
}
