package com.foodie.foodie;

public class Order {

    private String orderNumber;
    private int amount;
    private String customerID;
    private String foodName;
    private String vendorName;

    public Order(String orderNumber, int amount, String customerID, String foodName, String vendorName) {
        this.orderNumber = orderNumber;
        this.amount = amount;
        this.customerID = customerID;
        this.foodName = foodName;
        this.vendorName = vendorName;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public int getAmount() {
        return amount;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getVendorName() {
        return vendorName;
    }
}
