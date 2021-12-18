package com.dltour.manHanRestaurant.domains;

public class Customer_Order {
    private String customerPhone;
    private int orderId;

    public Customer_Order() {
    }

    public Customer_Order(String customerPhone, int orderId) {
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
