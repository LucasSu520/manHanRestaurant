package com.dltour.manHanRestaurant.services;

import com.dltour.manHanRestaurant.daos.DishDao;
import com.dltour.manHanRestaurant.domains.Dish;
import com.dltour.manHanRestaurant.domains.Dish_Cook;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

//菜品服务，包括点菜，添加菜品等等；
public class DishService {
    double totalPrice=0;
    Scanner scanner = null;
    DishDao dd = new DishDao();
    CustomerService cs=new CustomerService();
    OrderService os=new OrderService();
    TableService ts=new TableService();

    //判断菜品是否存在
    public boolean isExist(int id) {
        Dish dish = (Dish) dd.querySingle("select * from dish where id=?",Dish.class, id);
        return dish != null;
    }

    /**
     * 点菜，然后绑定订单--菜肴--厨师 和 绑定订单--座号  和  订单和顾客电话
     * @param tableNum 顾客所选的座号
     */
    public void order(String customerPhone,int tableNum) {
        int cookId = 1;
        int dishId = 1;
        boolean dishLoop = true;
        boolean cookLoop = true;
        List<Dish_Cook> dishAndCookList =new LinkedList<>();
        //TODO 只能输入数字
        //TODO 在点菜点完后可以删除所点的菜品
        while (dishLoop) {
            System.out.println("请输入用户所选菜编号(输入0表示点菜完成):");
            scanner = new Scanner(System.in);
            dishId = scanner.nextInt();
            if (dishId == 0) {
                System.out.println("点菜完成");
                Date date= new Date(System.currentTimeMillis());
                //创建一个订单
                os.createOrder(date,totalPrice);
                //get the lastest id of the order and give it to order_dish_cook,
                //向customerDishCook数据库中添加数据；
                int orderId= os.incrementId();
                //在这里绑定订单和顾客
                os.bondCustomer(orderId,customerPhone);
                //绑定餐座和订单
                ts.bondOrder(tableNum,orderId);
                //绑定订单和菜肴和厨师
                os.bondDishAndCook(orderId,dishAndCookList);
                break;
            }
            //判断是否有该菜品
            if (isExist(dishId)) {
                cookLoop=true;
                while (cookLoop) {
                    System.out.println("请输入厨师ID(默认是厨师1):");
                    cookId = scanner.nextInt();
                    if (!new CookService().isExist(cookId)) {
                        System.out.println("抱歉输入厨师ID错误，请重新输入！");
                        continue;
                    }
                    cookLoop = false;
                    totalPrice = totalPrice+ this.getPrice(dishId);
                    dishAndCookList.add(new Dish_Cook(dishId,cookId));
                }
            }else {
                System.out.println("抱歉，菜品名称输入错误，请重新输入!");
            }
        }
    }

    //计算菜品价格
    public double getPrice(int id){
        Dish dish = (Dish) dd.querySingle("select * from dish where id=?", Dish.class, id);
        return dish.getPrice();
    }
}
