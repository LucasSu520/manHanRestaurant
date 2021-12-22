package com.dltour.manHanRestaurant.views;

import com.dltour.manHanRestaurant.domains.Users;
import com.dltour.manHanRestaurant.services.UserService;

import java.util.Scanner;

/**
 * @author Sino灬多多
 * @version 1.0
 * 系统登陆系统（主界面）
 */
public class MainView {
    public static void main(String[] args) {
        show();//防止检验的时候输入过多变量
//        new MenuView().show();

    }

    public static void show(){
        UserService userService=new UserService();
        Scanner scanner;
        Boolean loop=true;
        while (loop) {
            System.out.println("满汉楼管理系统欢迎您！");
            System.out.println("1  登陆系统");
            System.out.println("2  退出系统");
            System.out.println("请输入你的选择:");
            scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                while (true) {
                    int choice = scanner.nextInt();
                    switch (choice) {
                        case 1 -> {
                            System.out.println("请输入登陆账号（输入-1退出）:");
                            scanner = new Scanner(System.in);
                            String userName = scanner.next();
                            if (userName.equals("-1")) {
                                System.exit(0);
                            }
                            System.out.println("请输入密码:");
                            scanner = new Scanner(System.in);
                            String password = scanner.next();
                            if (userService.checkUser(userName,password)) {
                                System.out.println("欢迎回来: " + userName);
                                MenuView menuView = new MenuView();
                                menuView.show();
                            }else {
                                System.out.println("请重新选择服务！");
                            }
                        }
                        case 2 -> {
                            System.exit(0);
                        }
                        default -> {
                            System.out.println("输入错误，请重新输入!");
                        }
                    }
                }
            }else {
                System.out.println("请输入对应的序号");
            }
        }
    }
}
