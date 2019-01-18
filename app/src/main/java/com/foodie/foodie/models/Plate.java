package com.foodie.foodie.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Plate implements Parcelable {

    private Food food;
    private ArrayList<Food> toppings;
    private int foodAmt=0;

    public int getFoodAmt() {
        return foodAmt;
    }

    public void setFoodAmt(int foodAmt) {
        this.foodAmt = foodAmt;
    }

    public Plate(Food food) {
        this.food = food;
        toppings = new ArrayList<>();
    }

    public boolean addTopping(Food food) {
        if(toppings.contains(food)) {
            return false;
        }
        toppings.add(food);
        return true;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    @Override
    public boolean equals(Object obj) {
        //this must be changed when Plate has toppings
        return this.food.getTH_name().equals(((Plate) obj).food.getTH_name());
    }

    //implementing Parcelable

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(food,i);
        parcel.writeTypedList(toppings);
    }

    private Plate(Parcel in) {
        food = in.readParcelable(Food.class.getClassLoader());
        toppings= new ArrayList<>();
        in.readTypedList(toppings,Food.CREATOR);
    }

    public static final Parcelable.Creator<Plate> CREATOR
            = new Parcelable.Creator<Plate>() {
        public Plate createFromParcel(Parcel in) {
            return new Plate(in);
        }

        public Plate[] newArray(int size) {
            return new Plate[size];
        }
    };




}
