package com.dltour.manHanRestaurant.domains;


import com.dltour.manHanRestaurant.services.OrderService;

import java.util.Date;

//已经创建数据库
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
        OrderService os=new OrderService();
                return "餐座编号:"
                +os.getTableNum(this.id)
                +" 订单编号:"+this.id+" 订单时间:"
                +this.orderTime+" 订单金额:"
                +String.format("%.2f",this.money)
                +"  是否已经支付:"
                +this.isPayed;
    }
}
