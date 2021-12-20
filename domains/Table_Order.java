package com.dltour.manHanRestaurant.domains;

public class Table_Order {
    private int orderId;
    private int tableId;

    public Table_Order() {
    }

    public Table_Order(int orderId, int tableId) {
        this.orderId = orderId;
        this.tableId = tableId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }
}
