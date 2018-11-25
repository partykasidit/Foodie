package com.foodie.foodie;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Vendor {

    private String TH_name;
    private String EN_name;
    private StorageReference imageResource;

    public Vendor(String TH_name, String EN_name, String imageURL) {
        this.TH_name = TH_name;
        this.EN_name = EN_name;
        this.imageResource = FirebaseStorage.getInstance().getReferenceFromUrl(imageURL);
    }

    public String getName() {
        return this.TH_name;
    }

    public StorageReference getImageResource() {
        return imageResource;
    }
}
