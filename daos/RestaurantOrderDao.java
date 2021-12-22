package com.dltour.manHanRestaurant.daos;

import com.dltour.manHanRestaurant.domains.Dish_Cook;
import com.dltour.manHanRestaurant.domains.RestaurantOrder;

import java.util.Date;
import java.util.List;

public interface RestaurantOrderDao{

    /**
     * 根据订单编号展示订单
     * @param orderId 订单编号
     */
    RestaurantOrder getOrder(int orderId);

    /**
     * 支付订单，分别修改订单的支付状态
     * @param orderId 支付的订单编号
     */
    int payOrder(int orderId);

    /**
     * 根据时间和总价格来创建订单；
     * @param time 创建订单的时间
     * @param price 订单的总价格
     */
    int createOrder(Date time,double price);

    /**
     * 得到最新创建的订单编号
     * @return
     */
    int getIncrementOrder();

    /**
     * 绑定订单号码和顾客手机号码
     * @param orderId 绑定的订单号码
     * @param phoneNum 绑定的顾客手机号码
     */
    int bondCustomer(int orderId,String phoneNum);

    /**
     * 绑定订单和餐座
     * @param orderId 绑定的订单
     * @param tableNum 绑定的餐座号码
     */
    int bondTable(int orderId ,int tableNum);

    /**
     * 绑定订单和厨师与菜肴的集合
     * @param orderId 订单编号
     * @param ds 厨师和菜肴的集合
     */
    int bondDishAndCook(int orderId, List<Dish_Cook> ds);

    /**
     * 改变订单的金额
     * @param orderId 改变金额的订单编号
     */
    int changeMoney(double money,int orderId);

    /**
     * 得到订单的总金额
     * @param orderId 查询的订单编号
     */
    double getTotalPrice(int orderId);

    /**
     * 得到订单对应的菜品和厨师集合
     * @param orderId 查询的订单号码
     */
    List<Dish_Cook> getDishAndCook(int orderId);

    /**
     * 得到和订单匹配的电话
     * @param orderId 订单的编号
     */
    String getPhoneNum(int orderId);

    /**
     * 改变订单状态
     * @param orderId 改变的订单状态
     */
    void changeOrderPayStat(int orderId);

    /**
     * 得到桌子编号
     * @param orderId 查询的订单编号
     */
    int getTableNum(int orderId);

    /**
     * 返回所有的订单
     */
    List<RestaurantOrder> getAllOrder();
}
