package com.dltour.manHanRestaurant.services;

import com.dltour.manHanRestaurant.daoImpl.CustomerDaoImpl;
import com.dltour.manHanRestaurant.domains.Customer;

import java.util.Scanner;

/**
 * 关于顾客的服务
 */
public class CustomerService {

    Customer customer=null;
    Scanner scanner=null;
    DishService ds;
    CustomerDaoImpl cdi;
    TableService tableService=new TableService();

    int tableNum=0;

    //返回创建的用户
    public  Customer getCustomer(){
        return this.customer;
    }

    public int createCustomer(String name,String phoneNum){
        cdi=new CustomerDaoImpl();
        return cdi.createCustomer(name,phoneNum);
    }

    //用户选择桌子
    public int selectTable(String customerPhone){
        boolean loop=true;
        ds= new DishService();
        while (loop) {
            System.out.println("请输入用户选择的桌子");
            scanner = new Scanner(System.in);
            if (scanner.hasNextInt()){
            tableNum = scanner.nextInt();
            if (!tableService.isEmpty(tableNum)) {
                System.out.println("抱歉，所选桌子已被预定，请重新选择！");
                continue;
                }
            loop=false;
            }else {
                System.out.println("输入错误，请重新输入，请输入桌子序号；");
            }
        }
        return tableNum;
    }
}
