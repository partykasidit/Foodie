package com.foodie.foodie;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

class Vendor {

    private String TH_name;
    private String EN_name;
    private StorageReference imageResource;

    Vendor(String TH_name, String EN_name, String imageURL) {
        this.TH_name = TH_name;
        this.EN_name = EN_name;
        this.imageResource = FirebaseStorage.getInstance().getReferenceFromUrl(imageURL);
    }

    String getName() {
        return this.TH_name;
    }

    String getNameInDatabase() {
        return this.EN_name;
    }

    StorageReference getImageResource() {
        return imageResource;
    }
}
