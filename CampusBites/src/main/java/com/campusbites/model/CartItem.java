package com.campusbites.model;

public class CartItem {

    private FoodItem food;
    private int quantity;

    public CartItem() {
    }

    public CartItem(FoodItem food, int quantity) {
        this.food = food;
        this.quantity = quantity;
    }

    public FoodItem getFood() {
        return food;
    }

    public void setFood(FoodItem food) {
        this.food = food;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return food.getPrice() * quantity;
    }
}