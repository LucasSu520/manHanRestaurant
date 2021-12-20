package com.dltour.manHanRestaurant.domains;

import com.dltour.manHanRestaurant.daos.CookDao;
import com.dltour.manHanRestaurant.daos.DishDao;
import com.dltour.manHanRestaurant.services.CookService;

public class Order_Dish_Cook {
    int orderId;
    int dishId;
    int cookId;


    public Order_Dish_Cook() {
    }

    public Order_Dish_Cook(int orderId, int dishId, int cookId) {
        this.orderId = orderId;
        this.dishId = dishId;
        this.cookId = cookId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public int getCookId() {
        return cookId;
    }

    public void setCookId(int cookId) {
        this.cookId = cookId;
    }

    //通过菜品id，得到菜品名称
    public String getDishName(int dishId){
        DishDao dd=new DishDao();
        Dish d;
        d=(Dish) dd.querySingle("select * from dish where id=?",Dish.class,dishId);
        return d.getName();
    }

    //得到厨师名称
    public String getCookName(int cookId){
        CookDao cd=new CookDao();
        Cook c;
        c=(Cook) cd.querySingle("select * from cook where id=?",Cook.class,cookId);
        return c.getName();

    }

    @Override
    public String toString() {
        return this.getDishId()
                +" "
                +String.format("%-10s",this.getDishName(this.getDishId()))
                +this.getCookId()
                +" "
                +String.format("%-8s",this.getCookName(this.getCookId()));
    }
}
