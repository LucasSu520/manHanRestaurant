package com.dltour.manHanRestaurant.services;

import com.dltour.manHanRestaurant.daos.CustomerDao;
import com.dltour.manHanRestaurant.domains.Customer;
import com.dltour.manHanRestaurant.domains.Dish_Cook;


import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 关于顾客的服务
 */
public class CustomerService {

    Customer customer=null;
    Scanner scanner=null;
    TableService tableService=new TableService();
    List<Dish_Cook> dish_cookList=new LinkedList<>();
    int tableNum=0;

    //返回创建的用户
    public  Customer getCustomer(){
        return this.customer;
    }

    //根据用户输入来创建一个新的顾客
    public void createCustomer(){
        System.out.println("请输入顾客姓名:");
        scanner=new Scanner(System.in);
        String name=scanner.next();
        System.out.println("请输入顾客电话:");
        scanner=new Scanner(System.in);
        String phoneNum=scanner.next();
        customer=new Customer(name,phoneNum);
        //提交到数据库中
        new CustomerDao().update("insert into customer values(null,?,?);",name,phoneNum);
    }

    //用户来选择桌子
    public void selectTable(String customerPhone){
        boolean loop=true;
        while (loop) {
            System.out.println("请输入用户选择的桌子");
            scanner = new Scanner(System.in);
            if (scanner.hasNextInt()){
            tableNum = scanner.nextInt();
            if (!tableService.isTableEmpty(tableNum)) {
                System.out.println("抱歉，所选桌子已被预定，请重新选择！");
                continue;
            }
            //TODO接下来点菜
            //点菜的时候自动生产一个订单
            new DishService().order(customerPhone,tableNum);
            new TableService().changeTableToIsOrder(tableNum);
            loop=false;
            }else {
                System.out.println("输入错误，请重新输入，请输入桌子序号；");
            }
        }
    }
}
