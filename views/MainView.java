package com.dltour.manHanRestaurant.views;

import com.dltour.manHanRestaurant.domains.Users;

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
        Scanner scanner;
        Boolean loop=true;
        while (loop) {
            System.out.println("满汉楼管理系统欢迎您！");
            System.out.println("1  登陆系统");
            System.out.println("2  退出系统");
            System.out.println("请输入你的选择:");
            scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                while (true) {
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
                            Users user = new Users(userName, password);
                            if (user.checkUser()) {
                                System.out.println("欢迎回来: " + userName);
                                MenuView menuView = new MenuView(user);
                                menuView.show();
                            } else {
                                //TODO 判断是否是密码还是账号错误
                                System.out.println("账号或者密码错误,请重新输入:");
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
