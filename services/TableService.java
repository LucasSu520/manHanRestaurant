package com.dltour.manHanRestaurant.services;

import com.dltour.manHanRestaurant.daoImpl.RestaurantTableDaoImpl;
import com.dltour.manHanRestaurant.domains.RestaurantTable;

import java.util.List;

/**
 * 所有关于餐座的服务，查询全部餐座状态，亦或是显示全部餐座；
 */
public class TableService {
    RestaurantTableDaoImpl rtdi;

    /**
     * 显示全部餐座；
     */
    public void showAllTables(){
        rtdi=new RestaurantTableDaoImpl();
        System.out.println("=====餐座列表=====");
        System.out.println("餐桌号码    是否空闲");
        List<RestaurantTable> lists= rtdi.getAllTable();
        for (RestaurantTable newRt:lists){
            System.out.println(newRt);
        }
    }

    /**
     * 查询是否有空闲桌子
     * @return true means there is at least one empty table if false there is no empty table;
     */
    public boolean hasEmptyTable(){
        rtdi=new RestaurantTableDaoImpl();
        List<RestaurantTable> list= rtdi.getAllTable();
        for (RestaurantTable newRt:list){
            if (newRt.getIsOrder().equals("否")){
                return true;
            }
        }
        return false;
    }


    public boolean isEmpty(int tableNum){
        rtdi=new RestaurantTableDaoImpl();
        return rtdi.getTableStat(tableNum).equals("否");
    }

    public void changeTableStat(int tableNum){
        rtdi=new RestaurantTableDaoImpl();
        rtdi.changeTableStat(tableNum);
    }
}
