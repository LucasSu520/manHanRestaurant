package com.dltour.manHanRestaurant.domains;

import com.dltour.manHanRestaurant.daos.Table_Order_Dao;

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

    //得到订单的座号
    public int getTableNum(int orderId){
        Table_Order_Dao tod=new Table_Order_Dao();
        Table_Order to=null;
        to= (Table_Order)tod.querySingle("select * from table_order where orderId=?",Table_Order.class,orderId);
        return to.getTableId();
    }

    @Override
    public String toString() {
        return "餐座编号:"
                +this.getTableNum(this.id)
                +" 订单编号:"+this.id+" 订单时间:"
                +this.orderTime+" 订单金额:"
                +String.format("%.2f",this.money);
    }
}
