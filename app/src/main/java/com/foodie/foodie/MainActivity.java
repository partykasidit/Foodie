package com.foodie.foodie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button shopButton = findViewById(R.id.shop_button);
        ImageView glideTest = findViewById(R.id.iv_glide_test);

        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Pork Steak");

        GlideApp.with(this).load(storageReference).into(glideTest);
    }

    public void onShopButtonClicked(View view) {
        Intent intent = new Intent(MainActivity.this,ShopActivity.class);
        startActivity(intent);
    }
}
