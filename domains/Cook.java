package com.dltour.manHanRestaurant.domains;

//已经创建mysql数据库
public class Cook {
    private int id;
    private String name;

    public Cook() {
    }

    public Cook(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
