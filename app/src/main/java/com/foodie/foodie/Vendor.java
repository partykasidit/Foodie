package com.foodie.foodie;

public class Vendor {

    private String TH_name;
    private String EN_name;
    private int imageResource;

    public Vendor(String TH_name, String EN_name, int image) {
        this.TH_name = TH_name;
        this.EN_name = EN_name;
        this.imageResource = image;
    }

    public String getName() {
        return this.TH_name;
    }

    public int getImageResource() {
        return imageResource;
    }
}
