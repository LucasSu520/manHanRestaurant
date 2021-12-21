package com.dltour.manHanRestaurant.daos;

import com.dltour.manHanRestaurant.domains.Cook;

import java.util.List;

public interface CookDao {
    /**
     * 查询厨师
     * @param cookId 厨师ID
     * @return 返回厨师类
     */
    Cook getCook(int cookId);

    /**
     * 根据ID添加厨师
     * @param name 厨师姓名
     * @return 影响添加影响行数
     */
    int addCook(String name);

    /**
     * 查询所有厨师
     * @return 返回所有厨师列表
     */
    List<Cook> getAllCook();

    /**
     * 根据厨师ID删除厨师
     * @return 是否删除成功
     */
    int deleteCook(int cookId);

    /**
     * 得到厨师名字
     * @param cookId 查询的厨师ID
     */
    String getCookName(int cookId);

}
