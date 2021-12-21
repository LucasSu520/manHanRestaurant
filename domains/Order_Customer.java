package com.dltour.manHanRestaurant.domains;

import com.dltour.manHanRestaurant.daos.Order_Customer_Dao;

public class Order_Customer {
    private int orderId;
    private String customerPhone;

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

}
