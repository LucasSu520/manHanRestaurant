package com.dltour.manHanRestaurant.services;

import com.dltour.manHanRestaurant.daos.RestaurantTableDao;
import com.dltour.manHanRestaurant.daos.Table_RestaurantOrderDao;
import com.dltour.manHanRestaurant.domains.RestaurantTable;


import java.util.List;

/**
 * 所有关于餐座的服务，查询全部餐座状态，亦或是显示全部餐座；
 */
public class TableService {
    RestaurantTable rt;
    RestaurantTableDao rtd=new RestaurantTableDao();

    /**
     * 显示全部餐座；
     */
    public void showAllTables(){
        System.out.println("=====餐座列表=====");
        System.out.println("餐桌号码    是否空闲");
        List<RestaurantTable> lists= rtd.queryMulti("select * from restaurantTable",RestaurantTable.class);
        for (RestaurantTable newRt:lists){
            System.out.println(newRt);
        }
    }

    /**
     * 查询是否有空闲桌子
     * @return true means there is at least one empty table if false there is no empty table;
     */
    public boolean hasEmptyTable(){
        List<RestaurantTable> list= rtd.queryMulti("select * from restaurantTable",RestaurantTable.class);
        for (RestaurantTable newRt:list){
            if (newRt.getIsOrder().equals("否")){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断用户输入的餐座号的餐座是否空闲
     */
    public boolean isTableEmpty(int tableNum){
        rt=(RestaurantTable) rtd.querySingle("select * from restaurantTable where tableNum=?",RestaurantTable.class,tableNum);
        return rt.getIsOrder().equals("否");
    }

    /**
     * 餐座绑定订单
     */
    public void bondOrder(int tableNum,int orderId){
        new Table_RestaurantOrderDao().update("insert into table_order values(?,?);",tableNum,orderId);
    }

    /**
     * 修改座位好的状态
     */
    public void changeTableToIsOrder(int tableNum){
        new RestaurantTableDao().update("update restaurantTable set isOrder=? where tableNum=?",this.isTableEmpty(tableNum)?"是":"否",tableNum);
    }
}
