package com.dltour.manHanRestaurant.domains;

//已经创建数据库
public class RestaurantTable {
    private Integer tableNum;
    private String isOrder;

    public RestaurantTable() {
    }

    public Integer getTableNum() {
        return tableNum;
    }

    public void setTableNum(Integer tableNum) {
        this.tableNum = tableNum;
    }

    public String getIsOrder() {
        return isOrder;
    }

    public void setIsOrder(String isOrder) {
        this.isOrder = isOrder;
    }

    @Override
    public String toString() {
        return String.format("%-8d",this.getTableNum())
                +(this.isOrder.equals("否")?"是":"否");
    }
}
