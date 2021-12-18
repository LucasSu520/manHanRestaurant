package com.dltour.manHanRestaurant.domains;

//已经创建数据库
public class RestaurantTable {
    private int tableNum;
    private boolean isOrder;

    public RestaurantTable() {
    }

    public RestaurantTable(int num, boolean isOrder) {
        this.tableNum = num;
        this.isOrder = isOrder;
    }

    public int getTableNum() {
        return tableNum;
    }

    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }

    public boolean isOrder() {
        return isOrder;
    }

    public void setOrder(boolean order) {
        isOrder = order;
    }

    @Override
    public String toString() {
        return (String.format("%2d",this.getTableNum())+"          "+(this.isOrder?"否":"是"));
    }
}
