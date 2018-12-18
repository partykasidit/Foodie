package com.foodie.foodievendor;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcv;
    private RecyclerView.Adapter<MyViewHolder> adapter;
    private ArrayList<MyData> dataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rcv = new RecyclerView(this);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerView.Adapter<MyViewHolder>() {
            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View card = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_main,viewGroup,false);
                return new MyViewHolder(card);
            }

            @Override
            public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

            }

            @Override
            public int getItemCount() {
                return 100;
            }
        };
        rcv.setAdapter(adapter);
        setContentView(rcv);
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        TextView test;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }

    private class MyData {
    }
}
