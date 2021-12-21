package com.dltour.manHanRestaurant.domains;

import java.util.Date;

public class RestaurantOrder {
    private int id;
    private Date orderTime;
    private double money;
    private boolean isPayed;

    public RestaurantOrder() {
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

    public boolean isPayed() {
        return isPayed;
    }

    public void setIsPayed(boolean payed) {
        isPayed = payed;
    }

    @Override
    public String toString() {
                return
                " 订单编号:"+this.id+" 订单时间:"
                +this.orderTime+" 订单金额:"
                +String.format("%.2f",this.money)
                +"  是否已经支付:"
                +this.isPayed;
    }
}
