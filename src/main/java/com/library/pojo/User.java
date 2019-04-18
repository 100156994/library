package com.library.pojo;

import java.io.Serializable;

public class User implements Serializable {

    private  String user_id;
    private  String password;
    private  String name;
    private  Float balance;
    private  int borrow;
    private  int order;
    private  int borrowed;
    private  int ordered;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public int getBorrow() {
        return borrow;
    }

    public void setBorrow(int borrow) {
        this.borrow = borrow;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(int borrowed) {
        this.borrowed = borrowed;
    }

    public int getOrdered() {
        return ordered;
    }


    public void setOrdered(int ordered) {
        this.ordered = ordered;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id='" + user_id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", borrow=" + borrow +
                ", order=" + order +
                ", borrowed=" + borrowed +
                ", ordered=" + ordered +
                '}';
    }

}
