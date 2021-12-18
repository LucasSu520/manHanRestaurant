package com.dltour.manHanRestaurant.domains;

//已经创建出数据库
public class RestaurantOrder_Dish {
    private int orderId;
    private String dishName;

    public RestaurantOrder_Dish() {
    }

    public RestaurantOrder_Dish(int orderId, String dishName) {
        this.orderId = orderId;
        this.dishName = dishName;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }
}
