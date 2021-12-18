package com.dltour.manHanRestaurant.domains;

public class Order_Cook_Dish {
    private int orderId;
    private int cookId;
    private int dishId;

    public Order_Cook_Dish() {
    }

    public Order_Cook_Dish(int orderId, int cookId, int dishId) {
        this.orderId = orderId;
        this.cookId = cookId;
        this.dishId = dishId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCookId() {
        return cookId;
    }

    public void setCookId(int cookId) {
        this.cookId = cookId;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }
}
