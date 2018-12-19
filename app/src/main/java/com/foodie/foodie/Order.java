package com.foodie.foodie;

import java.util.ArrayList;

public class Order {

    private int orderNumber;
    private boolean isFinished;
    private String customerUID;
    private String vendorName;
    private ArrayList<FoodOrder> foodOrders;

    public Order(int orderNumber, boolean status, String customerUID, String vendorName, ArrayList<FoodOrder> foodOrders) {
        this.orderNumber = orderNumber;
        this.isFinished = status;
        this.customerUID = customerUID;
        this.vendorName = vendorName;
        this.foodOrders = foodOrders;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public String getCustomerUID() {
        return customerUID;
    }

    public String getVendorName() {
        return vendorName;
    }

    public ArrayList<FoodOrder> getFoodOrders() {
        return foodOrders;
    }
}