package com.dltour.manHanRestaurant.views;

import com.dltour.manHanRestaurant.domains.Customer;
import com.dltour.manHanRestaurant.domains.Users;
import com.dltour.manHanRestaurant.services.CustomerService;
import com.dltour.manHanRestaurant.services.DishService;
import com.dltour.manHanRestaurant.services.OrderService;
import com.dltour.manHanRestaurant.services.TableService;
import com.dltour.manHanRestaurant.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuView {
    Users user=null;
    TableService ts=new TableService();
    CustomerService customerService=new CustomerService();
    Connection connection;

    public MenuView() {
    }

    public MenuView(Users user) {
        this.user = user;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    public void show(){
        Boolean loop=true;
        Scanner scanner=null;
        OrderService os=new OrderService();
        DishService ds=new DishService();
        while (loop) {
            System.out.println("=====二级管理菜单=====");
            System.out.println("1  查询餐桌状态");
            System.out.println("2  预定餐桌");
            System.out.println("3  显示所有菜品");
            System.out.println("4  加餐服务");
            System.out.println("5  查看账单");
            System.out.println("6  结账");
            System.out.println("8  切换账号");
            System.out.println("9  退出满汉楼系统");
            System.out.println("请输入你的选择:");
            scanner=new Scanner(System.in);
            //TODO 限制只能输入数字
            int choice=scanner.nextInt();
            switch (choice){
                case 1->{
                    ts.showAllTables();
                }
                case 2->{
                    System.out.println("=====预定餐桌====");
                    //1、先判断是否有空余餐桌，先创建一个顾客；
                    if (!ts.hasEmptyTable()){
                        System.out.println("抱歉，目前没有空座位，无法预定餐桌，请等待有空位;");
                        continue;
                    }
                    try {
                        connection= JDBCUtils.getConnection();
                        connection.setAutoCommit(false);
                        customerService.createCustomer();
                        Customer customer=customerService.getCustomer();
                        String customerPhone=customer.getPhoneNum();
                        //2、让顾客选择餐桌，若餐座不空闲，则无法预定；
                        customerService.selectTable(customerPhone);
                        //3、如果餐座选定，则开始点菜，选厨师,更改餐座状态
                        //4、选完就一直等到结账接触餐座状态了；
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            connection.commit();
                            connection.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
                case 3->{
                    System.out.println("========菜品列表===========");
                    System.out.println("ID      名称           价格");
                    ds.showAll();
                }
                case 4->{
                    System.out.println("=====加餐服务=====");
                    os.addDish();
                }
                case 5->{
                    System.out.println("=====查看账单=====");
                    os.showOrder();
                }
                case 6->{
                    System.out.println("=====结账=====");

                }
                case 8->{
                    System.out.println("=====切换账号=====");
                }
                case 9->{
                    System.out.println("退出系统成功");
                    System.exit(0);
                }
                default -> {
                    System.out.println("输入错误，请重新输入；");
                }
            }
        }
    }
}
