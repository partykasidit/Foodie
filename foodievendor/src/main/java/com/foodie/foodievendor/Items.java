package com.foodie.foodievendor;

public class Items {
    private int mImageResource;
    private String mText1;

    public Items(int imageResource,String text1){
        mImageResource = imageResource;
        mText1 = text1;

    }

    public int getImageResource(){
        return mImageResource;
    }
     public String getmText1(){
        return mText1;
     }
}
