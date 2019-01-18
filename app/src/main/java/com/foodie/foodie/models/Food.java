package com.foodie.foodie.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Food implements Parcelable {

    private String EN_name;
    private String TH_name;
    private double price;
    private StorageReference imageReference;

    public Food(String EN_name, String TH_name, double price, String imageURL) {
        this.EN_name = EN_name;
        this.TH_name = TH_name;
        this.price = price;
        if(imageURL != null && !imageURL.equals("")) {
            this.imageReference = FirebaseStorage.getInstance().getReferenceFromUrl(imageURL);
        }
    }

    public String getEN_name() {
        return EN_name;
    }

    public String getTH_name() {
        return TH_name;
    }

    public double getPrice() {
        return price;
    }

    public StorageReference getImageReference() {
        return imageReference;
    }

    //implementing Parcelable

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(EN_name);
        parcel.writeString(TH_name);
        parcel.writeDouble(price);
    }

    private Food(Parcel in) {
        EN_name = in.readString();
        TH_name = in.readString();
        price = in.readDouble();
    }

    public static final Parcelable.Creator<Food> CREATOR
            = new Parcelable.Creator<Food>() {
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        public Food[] newArray(int size) {
            return new Food[size];
        }
    };
}
