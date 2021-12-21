package com.dltour.manHanRestaurant.services;

import com.dltour.manHanRestaurant.daoImpl.RestaurantOrderDaoImpl;
import com.dltour.manHanRestaurant.domains.*;
import com.dltour.manHanRestaurant.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class OrderService {

    Scanner scanner=null;
    RestaurantOrderDaoImpl rodi;
    Connection connection;
    DishService ds;
    CookService cs;
    CustomerService customerService;
    RestaurantOrder ro;
    TableService ts;
    List<Dish_Cook> list;


    /**
     * 得到订单
     * @param orderId 查询的订单号
     */
    public RestaurantOrder getOrder(int orderId){
        rodi=new RestaurantOrderDaoImpl();
       return rodi.getOrder(orderId);
    }

    /**
     * 加餐
     */
    public void addDish(int orderId){
        rodi=new RestaurantOrderDaoImpl();
        boolean dishLoop=true;
        boolean loop=true;
        list=new LinkedList<>();
        int dishId;
        int cookId;
        int addOrderId=0;
        double totalPrice=this.getTotalPrice(addOrderId);
        while (dishLoop) {
            System.out.println("请输入加菜编号(输入0表示点菜完成):");
            scanner = new Scanner(System.in);
            while (loop) {
                if (scanner.hasNextInt()) {
                    dishId = scanner.nextInt();
                    if (dishId == 0) {
                        System.out.println("加菜完成");
                        //更新订单总金额
                        rodi.changeMoney(totalPrice, addOrderId);
                        //更新绑定的厨师和菜肴
                        rodi.bondDishAndCook(addOrderId, list);
                        dishLoop=false;
                        break;
                    }
                    //判断是否有该菜品
                    if (ds.isExist(dishId)) {
                        boolean cookLoop = true;
                        while (cookLoop) {
                            System.out.println("请输入厨师ID(默认是厨师1):");
                            cookId = scanner.nextInt();
                            if (!new CookService().isExist(cookId)) {
                                System.out.println("抱歉输入厨师ID错误，请重新输入！");
                                continue;
                            }
                            cookLoop = false;
                            totalPrice = totalPrice + ds.getPrice(dishId);
                            list.add(new Dish_Cook(dishId, cookId));
                        }
                        break;
                    } else {
                        System.out.println("抱歉，菜品名称输入错误，请重新输入!");
                    }
                } else {
                    System.out.println("抱歉输入有误，请输入数字！");
                }
            }
        }
    }

    /**
     * 得到订单的总金额
     */
    public double getTotalPrice(int orderId){
        rodi=new RestaurantOrderDaoImpl();
        return  rodi.getTotalPrice(orderId);
    }

    /**
     *展示订单信息
     */
    public void showOrder(int orderId){
        customerService=new CustomerService();
        ro=rodi.getOrder(orderId);
        String phoneNum;
        String customerName;
        phoneNum=rodi.getPhoneNum(orderId);
        customerName=customerService.getCustomerName(phoneNum);
        System.out.println("顾客姓名:"+customerName+" 顾客电话:"+phoneNum +" 餐座编号: "+rodi.getTableNum(orderId)+ro );
        System.out.println("============订单详情============");
        System.out.println("菜品          厨师           价格");
        this.showDetail(ro.getId());
    }

    /**
     * 展示订单详情
     * @param orderId 需要展示的订单编号
     */
    public void showDetail(int orderId){
        ds=new DishService();
        cs=new CookService();
        List<Dish_Cook> list=rodi.getDishAndCook(orderId);
        for (Dish_Cook odc1:list){
            int dishId=odc1.getDishId();
            int cookId=odc1.getCookId();
            String dishName=ds.getDishName(dishId);
            String cookName=cs.getCookName(cookId);
            double dishPrice=ds.getPrice(dishId);
            System.out.println(String.format("%-11s",dishName)
                    +String.format("%-11s",cookName)
                    +String.format("%.2f",dishPrice));
        }
    }

    /**
     * 付款
     */
    public void pay(int  orderId) {
        ts=new TableService();
        try {
            connection= JDBCUtils.getConnection();
            connection.setAutoCommit(false);
            //修改订单付款状态;
            rodi.changeOrderPayStat(orderId);
            //修改餐座状态;
            ts.changeTableStat(rodi.getTableNum(orderId));
            connection.commit();
            System.out.println("账单结账成功;");
            showOrder(orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }


    //订餐，给予提供的顾客电话和选定的坐姿编号
    public void order(String customerPhone,int tableNum) {
        rodi=new RestaurantOrderDaoImpl();
        ds=new DishService();
        ts=new TableService();
        cs=new CookService();
        int cookId;
        int dishId ;
        double totalPrice=0;
        boolean dishLoop = true;
        List<Dish_Cook> dishAndCookList =new LinkedList<>();
        //TODO 在点菜点完后可以删除所点的菜品
        while (dishLoop) {
            System.out.println("请输入用户所选菜编号(输入0表示点菜完成):");
            scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                dishId = scanner.nextInt();
                if (dishId == 0) {
                    System.out.println("点菜完成");
                    ts.changeTableStat(tableNum);
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
                if (ds.isExist(dishId)) {
                    boolean cookLoop = true;
                    while (cookLoop) {
                        System.out.println("请输入厨师ID(默认是厨师1):");
                        cookId = scanner.nextInt();
                        if (!cs.isExist(cookId)) {
                            System.out.println("抱歉输入厨师ID错误，请重新输入！");
                            continue;
                        }
                        cookLoop = false;
                        totalPrice = totalPrice + ds.getPrice(dishId);
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
}
