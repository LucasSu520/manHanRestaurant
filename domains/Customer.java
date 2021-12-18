package com.dltour.manHanRestaurant.domains;

public class Customer {
    private int id;
    private String name;
    private String phoneNum;

    public Customer() {
    }

    public Customer(String name, String phoneNum) {
        this.name = name;
        this.phoneNum = phoneNum;
    }

    public Customer(int id, String name, String phoneNum) {
        this.id = id;
        this.name = name;
        this.phoneNum = phoneNum;
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

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
