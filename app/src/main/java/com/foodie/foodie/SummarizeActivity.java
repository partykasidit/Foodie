package com.foodie.foodie;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;


public class SummarizeActivity extends AppCompatActivity {
    Toolbar toolbar;

    public class foodMenu{
        private int amount;
        private String foodName;

        public foodMenu() {
        }

        public foodMenu(int amount, String foodName) {
            this.amount = amount;
            this.foodName = foodName;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getFoodName() {
            return foodName;
        }

        public void setFoodName(String foodName) {
            this.foodName = foodName;
        }

        @Override
        public String toString() {
            return "foodMenu{" +
                    "amount=" + amount +
                    ", foodName='" + foodName + '\'' +
                    '}';
        }
    }

    final ArrayList<Plate> orderView = new ArrayList<>();
    private static final String TAG = "SummarizeActivity";
    private RecyclerView mSummarizeList;
    private SummarizeListAdaptor listAdaptor;
    private TextView mSumprice;
    private FirebaseAuth mAuth;
    String userID;
    private  Order order;
    private SharedPreferences sharedPreferences;
    private FirebaseFirestore db;
    String vendorName;
    int i =1 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summarize);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("  Summarize");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Log.d(TAG, "onCreate: started.");
        db = FirebaseFirestore.getInstance();

        /**
         *
         */

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Orders").document("bXH5xY7FQFqQPtxw6cAU").addSnapshotListener(new EventListener<DocumentSnapshot>() {
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
                        Log.d("hihi", "onEvent: "+ key);
//                        String fd = (String)documentSnapshot.get(key);
//                        Log.d(TAG, "onEvent: fd "+fd);
                        try{
                        if(i<=Integer.parseInt(key)){
                            i=Integer.parseInt(key)+1;
                        }}catch (Exception EE)
                        {}

                        /**
                       HashMap<String,Object> foodRequest = (HashMap<String,Object>) documentSnapshot.get(key);
                        String id= (String) foodRequest.get("customerUID");
                       HashMap<String,foodMenu> test = (HashMap<String,foodMenu>)foodRequest.get("foodOrders");
                       int amount = test.get("1").getAmount();
                       String foodname = test.get("1").getFoodName();

                         Log.d("hihi", "onEvent: "+test.toString());
*/

//                        if(foodRequest.containsKey("foodOrders")) {
//                         String forRequest= foodRequest.get("foodOrders").toString();
//                            Log.d("hihi", "onEvent: "+forRequest);
//                        }

                    }
                } else {
                    Log.d("Foodie-MA", "Current data: null");
                }

            }
        });

        /**
         *
         */


        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setTitle("Summarize");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        ArrayList<Plate> order = intent.getParcelableArrayListExtra("order");
        vendorName=intent.getStringExtra("selectedVendor");


        for(Plate p: order){
            if(orderView.contains(p)){
             int i = orderView.get(orderView.indexOf(p)).getFoodAmt()+1;
                orderView.get(orderView.indexOf(p)).setFoodAmt(i);
            }else{
                p.setFoodAmt(1);
                orderView.add(p);

            }

        }
        Log.d("Foodie-SA",order.toString());

        mSummarizeList= findViewById(R.id.my_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mSummarizeList.setLayoutManager(layoutManager);
        mSummarizeList.setHasFixedSize(true);
        listAdaptor= new SummarizeListAdaptor(orderView);
        mSummarizeList.setAdapter(listAdaptor);

        sharedPreferences = this.getSharedPreferences("FirebaseUser",Context.MODE_PRIVATE);

        //calculate sum
        double sum=0;
        for(int i=0;i<order.size();i++){
            sum += (order.get(i).getFood().getPrice());
        }

        mSumprice = findViewById(R.id.tv_totalprice);
        mSumprice.setText(String.valueOf(sum) + " Baht");

    }

    public void onPayButtonClicked(View v){
        String userUID = sharedPreferences.getString("UserUID",null);
        if(userUID != null) {
            Log.d("Foodie-SA",userUID);
            Log.d("hihi", "onPayButtonClicked: " + db.collection("Orders").document("ixAGVMvgXXipttf6nO49").toString());


            /***/
            Map<String,Object> data = new HashMap<>();
            data.put("customerUID",userUID);
            Map<String,Object> foodOrder = new HashMap<>();

            for(int i =0;i< orderView.size();i++) {
                Map<String,Object> foodID = new HashMap<>();
                foodID.put("amount",orderView.get(i).getFoodAmt());
                foodID.put("foodName",orderView.get(i).getFood().getTH_name());
                foodOrder.put(String.valueOf(i+1),foodID);
            }
            data.put("foodOrders",foodOrder);
            data.put("isFinished",false);
            data.put("vendorName",vendorName);
            db.collection("Orders").document("bXH5xY7FQFqQPtxw6cAU").update(String.valueOf(i),data);

            Toast.makeText(this,"Order made!",Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(SummarizeActivity.this, MainActivity.class);
            startActivity(intent);

        } else {
            Toast.makeText(this,"Please log in to make order",Toast.LENGTH_SHORT);
        }

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
