package com.dltour.manHanRestaurant.services;

import com.dltour.manHanRestaurant.daoImpl.DishDaoImpl;
import com.dltour.manHanRestaurant.daoImpl.RestaurantOrderDaoImpl;
import com.dltour.manHanRestaurant.daoImpl.RestaurantTableDaoImpl;
import com.dltour.manHanRestaurant.domains.Dish;
import com.dltour.manHanRestaurant.domains.Dish_Cook;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

//菜品服务，包括点菜，添加菜品等等；
public class DishService {
    RestaurantOrderDaoImpl rodi;
    RestaurantTableDaoImpl rtdi;
    DishDaoImpl ddi;
    double totalPrice=0;
    Scanner scanner = null;
    OrderService os;
    TableService ts=new TableService();

    /**
     * 返回所有的菜品
     */
    public void showAll(){
        ddi=new DishDaoImpl();
        List<Dish> dishes= ddi.getAllDish();
        for (Dish dish:dishes){
            System.out.println(dish);
        }
    }


    /**
     * 点菜，然后绑定订单--菜肴--厨师 和 绑定订单--座号  和  订单和顾客电话
     * @param tableNum 顾客所选的座号
     */
    public void order(String customerPhone,int tableNum) {
        rodi=new RestaurantOrderDaoImpl();
        rtdi=new RestaurantTableDaoImpl();
        int cookId;
        int dishId ;
        boolean dishLoop = true;
        boolean cookLoop;
        os=new OrderService();
        List<Dish_Cook> dishAndCookList =new LinkedList<>();
        //TODO 在点菜点完后可以删除所点的菜品
        while (dishLoop) {
            System.out.println("请输入用户所选菜编号(输入0表示点菜完成):");
            scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                dishId = scanner.nextInt();
                if (dishId == 0) {
                    System.out.println("点菜完成");
                    rtdi.changeTableStat(tableNum);
                    Date date = new Date(System.currentTimeMillis());
                    //创建一个订单
                    rodi.createOrder(date, totalPrice);
                    //向customerDishCook数据库中添加数据；
                    int orderId = rodi.getIncrementOrder();
                    //在这里绑定订单和顾客
                    rodi.bondCustomer(orderId,customerPhone);
                    //绑定餐座和订单
                    rodi.bondTable(tableNum, orderId);
                    //绑定订单和菜肴和厨师
                    rodi.bondDishAndCook(orderId, dishAndCookList);
                    break;
                }
                //判断是否有该菜品
                if (isExist(dishId)) {
                    cookLoop = true;
                    while (cookLoop) {
                        System.out.println("请输入厨师ID(默认是厨师1):");
                        cookId = scanner.nextInt();
                        if (!new CookService().isExist(cookId)) {
                            System.out.println("抱歉输入厨师ID错误，请重新输入！");
                            continue;
                        }
                        cookLoop = false;
                        totalPrice = totalPrice + this.getPrice(dishId);
                        dishAndCookList.add(new Dish_Cook(dishId, cookId));
                    }
                } else {
                    System.out.println("抱歉，菜品名称输入错误，请重新输入!");
                }
            }else {
                System.out.println("输入错误，请输入数字.");
            }
        }
    }

    //计算菜品价格
    public double getPrice(int id){
        ddi=new DishDaoImpl();
        return ddi.getPrice(id);
    }

    public String getDishName(int dishId){
        ddi =new DishDaoImpl();
       return ddi.getDishName(dishId);
    }

    public boolean isExist(int dishId){
        ddi=new DishDaoImpl();
        return ddi.getDish(dishId)!=null;
    }
}
