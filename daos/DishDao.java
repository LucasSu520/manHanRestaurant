package com.dltour.manHanRestaurant.daos;

import com.dltour.manHanRestaurant.domains.Dish;

import java.util.List;

public interface DishDao {

    /**
     * 添加菜品
     * @param name 添加菜品名称
     * @param price 添加菜品价格
     */
     int addDish(String name,double price);

    /**
     * 删除菜品
     * @param dishId 删除菜品ID
     */
     int deleteDish(int dishId);

    /**
     * 展示所有的菜品
     * @return 返回包含所有菜品的一个List
     */
    List<Dish> getAllDish();

    /**
     * 得到菜品的价格
     * @param dishId 菜品的ID
     */
    double getPrice(int dishId);

    /**
     * 得到菜品的名称
     * @param dishId 查询的菜品的ID
     */
    String getDishName(int dishId);

    /**
     * 得到菜肴
     * @param dishId 查询的菜肴ID
     */
    Dish getDish(int dishId);
}
