package com.dltour.manHanRestaurant.domains;

//已经创建数据库
public class RestaurantTable_RestaurantOrder {
    private int restaurantTableNum;
    private int orderId;

    public RestaurantTable_RestaurantOrder() {
    }

    public RestaurantTable_RestaurantOrder(int restaurantTableNum, int orderId) {
        this.restaurantTableNum = restaurantTableNum;
        this.orderId = orderId;
    }

    public int getRestaurantTableNum() {
        return restaurantTableNum;
    }

    public void setRestaurantTableNum(int restaurantTableNum) {
        this.restaurantTableNum = restaurantTableNum;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
