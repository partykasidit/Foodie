package com.foodie.foodie;

import java.util.ArrayList;

public class Plate {

    private Food food;
    private ArrayList<Food> toppings;

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

}
