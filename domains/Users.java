package com.dltour.manHanRestaurant.domains;

import com.dltour.manHanRestaurant.daos.UsersDao;
//已经创建数据库
public class Users {
    String userName;
    String password;

    public Users() {
    }

    public Users(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean checkUser(){
        UsersDao userDao=new UsersDao();
        Users user= (Users) userDao.querySingle("select * from users where name=? and password=?", Users.class,userName,password);
        return user!=null;
    }
}
