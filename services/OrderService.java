package com.dltour.manHanRestaurant.services;

import com.dltour.manHanRestaurant.daos.*;
import com.dltour.manHanRestaurant.domains.*;
import com.dltour.manHanRestaurant.utils.JDBCUtils;


import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class OrderService {

    Scanner scanner=null;
    RestaurantOrderDao rod=new RestaurantOrderDao();
    Connection connection;
    Order_Dish_Cook_Dao odcd=null;
    Order_Dish_Cook odc=null;
    Order_Customer oc=null;
    Table_Order_Dao tod;
    Table_Order to;
    TableService ts;
    DishService ds=new DishService();
    List<Dish_Cook> list;
    int orderId;
    //创建一个新订单，并且提交给数据库;
    public void createOrder(Date date, double price){
        new RestaurantOrderDao().update("insert into restaurantOrder values(null,?,?,false)",date,price);
    }

    /**
     * 返回最新增加的自增ID
     * @return
     */
    public int incrementId(){
        return ((BigInteger)new RestaurantOrderDao().queryScalar("select LAST_INSERT_ID();")).intValue();
    }

    public RestaurantOrder getOrder(int orderId){
        return (RestaurantOrder) rod.querySingle("select * from restaurantOrder where id=?",RestaurantOrder.class,orderId);
    }
    /**
     * 绑定订单和顾客
     */
    public void bondCustomer(int orderId,String customerPhone){
        new Order_Customer_Dao().update("insert into order_customer values(?,?);",orderId,customerPhone);
    }

    /**
     * 绑定订单和厨师菜肴
     * @param orderId 订单号码
     * @param ds 菜肴和厨师的集合
     */
    public void bondDishAndCook(int orderId, List<Dish_Cook> ds){
        Customer_Dish_CookDao cdcd=  new  Customer_Dish_CookDao();
        for (Dish_Cook d : ds) {
            cdcd.update("insert into order_dish_cook values(?,?,?);",orderId,d.getDishId(),d.getCookId());
        }
    }


    /**
     * 加餐
     */
    public void addDish(){
        boolean dishLoop=true;
        boolean loop=true;
        list=new LinkedList<>();
        int dishId;
        int cookId;
        int addOrderId=0;
        double totalPrice=0;

        //获取订单编号
        while (loop) {
            System.out.println("请输入要加餐的订单号码:");
            scanner = new Scanner(System.in);
            addOrderId = scanner.nextInt();
            totalPrice = this.getTotalPrice(addOrderId);
            if (totalPrice < 0) {
                System.out.println("输入订单标号有误，请重新输入");
                continue;
            }
            loop=false;
        }
        while (dishLoop) {
            System.out.println("请输入用户所选菜编号(输入0表示点菜完成):");
            scanner = new Scanner(System.in);
            dishId = scanner.nextInt();
            if (dishId == 0) {
                System.out.println("点菜完成");
                //更新订单总金额
                rod.update("update restaurantOrder set money =? where id=? ",totalPrice,addOrderId);
                //更新绑定的厨师和菜肴
                this.bondDishAndCook(addOrderId,list);
                break;
            }
            //判断是否有该菜品
            if (ds.isExist(dishId)) {
                boolean cookLoop=true;
                while (cookLoop) {
                    System.out.println("请输入厨师ID(默认是厨师1):");
                    cookId = scanner.nextInt();
                    if (!new CookService().isExist(cookId)) {
                        System.out.println("抱歉输入厨师ID错误，请重新输入！");
                        continue;
                    }
                    cookLoop = false;
                    totalPrice = totalPrice+ ds.getPrice(dishId);
                    list.add(new Dish_Cook(dishId,cookId));
                }
            }else {
                System.out.println("抱歉，菜品名称输入错误，请重新输入!");
            }
        }
    }

    /**
     * 得到订单的总金额
     */
    public double getTotalPrice(int orderId){
        if(rod.queryScalar("select money from restaurantOrder where id=?",orderId)==null){
            return -1;
        }
        return (double) rod.queryScalar("select money from restaurantOrder where id=?",orderId);

    }

    /**
     *展示订单信息
     */
    public void showOrder(RestaurantOrder ro){

        String customerPhone;
        customerPhone=this.getCustomerPhone(ro.getId());
        //TODO 显示顾客名称
        System.out.println(ro +" 顾客电话:"+customerPhone);
        System.out.println("=======订单详情=======");
        System.out.println("菜品              厨师");
        this.showDetail(ro.getId());
    }

    /**
     * 展示订单详情
     * @param orderId 需要展示的订单编号
     */
    public void showDetail(int orderId){
        odcd=new Order_Dish_Cook_Dao();
        odc=new Order_Dish_Cook();

        List<Order_Dish_Cook> list=new LinkedList<>();
        list.addAll(odcd.queryMulti("select * from Order_Dish_Cook where orderid=?",Order_Dish_Cook.class,orderId));
        for (Order_Dish_Cook odc1:list){
            System.out.println(odc1);
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
            rod.update("update restaurantOrder set isPayed=1 where id=?",orderId);
            //修改餐座状态;
            ts.changeTableToIsOrder(this.getTableNum(orderId));
            connection.commit();
            System.out.println("账单结账成功;");
            showOrder(this.getOrder(orderId));
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

    /**
     * 得到订单绑定的餐座号码
     * @param orderId 订单号码
     * @return 餐座号码
     */
    public int getTableNum(int orderId){
        tod=new Table_Order_Dao();
        to= (Table_Order)tod.querySingle("select * from table_order where orderId=?",Table_Order.class,orderId);
        return to.getTableId();
    }

    //根据订单号得到号码
    public String getCustomerPhone(int orderId){
        Order_Customer oc= (Order_Customer) new BasicDao().querySingle("select * from order_customer where orderId=?",Order_Customer.class,orderId);
        return oc.getCustomerPhone();
    }
}
