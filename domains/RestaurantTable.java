package com.dltour.manHanRestaurant.domains;

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
        return String.format("%-13d",this.getTableNum())
                +(this.isOrder.equals("否")?"是":"否");
    }
}
