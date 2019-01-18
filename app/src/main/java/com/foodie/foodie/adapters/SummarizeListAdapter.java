package com.foodie.foodie.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.foodie.foodie.R;
import com.foodie.foodie.models.Plate;

import java.util.ArrayList;


public class SummarizeListAdapter extends RecyclerView.Adapter<SummarizeListAdapter.SummarizeViewHolder> {
    private static final String TAG = "SummarizeListAdapter";
    private ArrayList<Plate> plates;


    public SummarizeListAdapter(ArrayList<Plate> plates) {
        this.plates = plates;
    }

    @NonNull
    @Override
    public SummarizeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem_summarize,viewGroup,false) ;
        SummarizeViewHolder holder = new SummarizeViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SummarizeViewHolder summarizeviewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder: called");

        summarizeviewHolder.bind(i);
//
//        viewHolder.imageName.setText(mImageNames.get(i));
//
//        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "onClick:  clicked on: " + mImageNames.get(i));
//                Toast.makeText(mconText, mImageNames.get(i),Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return plates.size();
    }


    public class SummarizeViewHolder extends RecyclerView.ViewHolder {

        TextView summarize_foodname;
        TextView summarize_price;

        SummarizeViewHolder(View itemView) {
            super(itemView);
            summarize_foodname = itemView.findViewById(R.id.tv_foodName);
            summarize_price = itemView.findViewById(R.id.tv_price);

        }

        public SummarizeViewHolder(@NonNull View itemView, TextView summarize_foodname, TextView summarize_price) {
            super(itemView);
            this.summarize_foodname = summarize_foodname;
            this.summarize_price = summarize_price;
        }

        void bind(int position) {
            summarize_foodname.setText(plates.get(position).getFood().getTH_name()+"x"+plates.get(position).getFoodAmt());
            summarize_price.setText(String.valueOf((plates.get(position).getFood().getPrice())*plates.get(position).getFoodAmt()) + "  Baht");
        }
    }

}
