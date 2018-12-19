package com.foodie.foodievendor;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ArrayList<Items> mExampleList;
    private onItemClickListener mListener;
    public interface onItemClickListener{
        void onDoneClick(int i);
    }
    public void setOnItemClickListener(onItemClickListener listener){
        mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTextview1;
        public ImageView mDone;

        public ViewHolder(@NonNull View itemView, final onItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextview1 = itemView.findViewById(R.id.textView);
            mDone = itemView.findViewById(R.id.image_done);



            mDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int i = getAdapterPosition();
                        if(i != RecyclerView.NO_POSITION){
                            listener.onDoneClick(i);
                        }
                    }
                }
            });
        }
    }

    public Adapter(ArrayList<Items> exampleList) {
        mExampleList = exampleList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item,viewGroup,false);
        return new ViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Items currentItem = mExampleList.get(i);

        viewHolder.mImageView.setImageResource(currentItem.getImageResource());
        viewHolder.mTextview1.setText(currentItem.getmText1());

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
