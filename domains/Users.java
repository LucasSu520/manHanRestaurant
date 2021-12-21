package com.dltour.manHanRestaurant.domains;

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
      //通过配置文件查看用户密码是否正确；
        return true;
    }
}
