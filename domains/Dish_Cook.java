package com.dltour.manHanRestaurant.domains;

public class Dish_Cook {
    private int dishId;
    private int cookId;

    public Dish_Cook() {
    }

    public Dish_Cook(int dishId, int cookId) {
        this.dishId = dishId;
        this.cookId = cookId;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public int getCookId() {
        return cookId;
    }

    public void setCookId(int cookId) {
        this.cookId = cookId;
    }

}
