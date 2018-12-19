package com.foodie.foodie;

public class FoodOrder {

    private String foodName;
    private int amount;

    public FoodOrder(String foodName, int amount) {
        this.foodName = foodName;
        this.amount = amount;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getAmount() {
        return amount;
    }
}
