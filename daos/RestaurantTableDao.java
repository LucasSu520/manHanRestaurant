package com.dltour.manHanRestaurant.daos;

import com.dltour.manHanRestaurant.domains.RestaurantTable;


import java.util.List;

public interface RestaurantTableDao{

    /**
     * 根据餐座号码得到餐座是否被预订
     * @param tableNum 查询的餐座状态
     */
    String  getTableStat(int tableNum);

    /**
     * 改变餐座被预订的状态
     * @param tableNum 餐座号码
     */
    int changeTableStat(int tableNum);

    /**
     * 得到全部的餐座信息；
     */
    List<RestaurantTable> getAllTable();
 }
