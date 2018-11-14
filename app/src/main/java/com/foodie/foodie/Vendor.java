package com.foodie.foodie;

public class Vendor {

    private String TH_name;
    private String EN_name;

    public Vendor(String TH_name, String EN_name) {
        this.TH_name = TH_name;
        this.EN_name = EN_name;
    }

    public String getName() {
        return this.TH_name;
    }
}
