package com.dltour.manHanRestaurant.domains;

import java.util.Date;

//已经创建数据库
public class RestaurantOrder {
    private int id;
    private Date orderTime;
    private double money;

    public RestaurantOrder() {
    }


    public RestaurantOrder(Date orderTime, double money) {
        this.orderTime = orderTime;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
