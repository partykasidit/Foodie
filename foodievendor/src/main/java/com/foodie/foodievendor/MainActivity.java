package com.foodie.foodievendor;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Items> mExampleList;
    private RecyclerView mRecycleView;
    private Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createExampleList();
        buildRecyclerView();
    }
    public void removeItem(int i){
        mExampleList.remove(i);
        mAdapter.notifyDataSetChanged();
    }

    public void createExampleList(){
        mExampleList = new ArrayList<>();
        mExampleList.add(new Items(R.drawable.ic_accessibility_black_24dp,"klod"));
        mExampleList.add(new Items(R.drawable.ic_android_black_24dp,"klod2"));

    }
    public void buildRecyclerView(){
        mRecycleView = findViewById(R.id.recycleView);
        mRecycleView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new Adapter(mExampleList);
        mRecycleView.setLayoutManager((mLayoutManager));
        mRecycleView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new Adapter.onItemClickListener() {
            @Override
            public void onDoneClick(int i) {
                removeItem(i);
            }
        });
    }
}
