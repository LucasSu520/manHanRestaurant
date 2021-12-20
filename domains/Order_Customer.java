package com.dltour.manHanRestaurant.domains;

import com.dltour.manHanRestaurant.daos.Order_Customer_Dao;

public class Order_Customer {
    private String customerPhone;
    private int orderId;

    public Order_Customer() {
    }

    public Order_Customer(String customerPhone, int orderId) {
        this.customerPhone = customerPhone;
        this.orderId = orderId;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerPhone(int orderId){
        Order_Customer_Dao ocd=new Order_Customer_Dao();
        String phoneNum=(String) ocd.queryScalar("select customerPhone from order_customer where orderId=?",orderId);
        return phoneNum;
    }
}
