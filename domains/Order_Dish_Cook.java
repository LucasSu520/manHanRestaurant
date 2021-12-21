package com.dltour.manHanRestaurant.domains;

public class Order_Dish_Cook {
    int orderId;
    int dishId;
    int cookId;


    public Order_Dish_Cook() {
    }

    public Order_Dish_Cook(int orderId, int dishId, int cookId) {
        this.orderId = orderId;
        this.dishId = dishId;
        this.cookId = cookId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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
