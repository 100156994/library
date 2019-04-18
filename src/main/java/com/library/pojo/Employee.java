package com.library.pojo;
import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable{

    private  String emp_id;
    private  String password;
    private  String name;
    private  String phone;
    private  String email;
    private  Float balance;
    private Date lastestlogin;

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Date getLastestlogin() {
        return lastestlogin;
    }

    public void setLastestlogin(Date lastestlogin) {
        this.lastestlogin = lastestlogin;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "emp_id='" + emp_id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", balance=" + balance +
                ", lastestlogin=" + lastestlogin +
                '}';
    }
}
