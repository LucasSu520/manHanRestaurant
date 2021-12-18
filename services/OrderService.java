package com.dltour.manHanRestaurant.services;

import com.dltour.manHanRestaurant.daos.Customer_Dish_CookDao;
import com.dltour.manHanRestaurant.daos.Order_Customer_Dao;
import com.dltour.manHanRestaurant.daos.RestaurantOrderDao;
import com.dltour.manHanRestaurant.domains.Dish_Cook;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class OrderService {
    //创建一个新订单，并且提交给数据库;
    public void createOrder(Date date, double price){
        new RestaurantOrderDao().update("insert into restaurantOrder values(null,?,?)",date,price);
    }

    /**
     * 返回最新增加的自增ID
     * @return
     */
    public int incrementId(){
        return ((BigInteger)new RestaurantOrderDao().queryScalar("select LAST_INSERT_ID();")).intValue();
    }

    /**
     * 绑定订单和顾客
     */
    public void bondCustomer(int orderId,String customerPhone){
        new Order_Customer_Dao().update("insert into order_customer values(?,?);",orderId,customerPhone);
    }

    //
    public void bondDishAndCook(int orderId, List<Dish_Cook> ds){
        Customer_Dish_CookDao cdcd=  new  Customer_Dish_CookDao();
        for (Dish_Cook d : ds) {
            cdcd.update("insert into order_dish_cook values(?,?,?);",orderId,d.getDishId(),d.getCookId());
        }
    }

}
