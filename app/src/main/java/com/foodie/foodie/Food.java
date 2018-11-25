package com.foodie.foodie;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Food {

    private String EN_name;
    private String TH_name;
    private double price;
    private StorageReference imageReference;

    public Food(String EN_name, String TH_name, double price, String imageURL) {
        this.EN_name = EN_name;
        this.TH_name = TH_name;
        this.price = price;
        this.imageReference = FirebaseStorage.getInstance().getReferenceFromUrl(imageURL);
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
}
