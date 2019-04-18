package com.library.pojo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Purchase implements Serializable{
    private String purId;
    private String isbn;
    private int num;
    private float unitprice;
    private Date time;
    private String buyer;
    private int status;

    public String getPurId() {
        return purId;
    }

    public void setPurId(String purId) {
        this.purId = purId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public float getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(float unitprice) {
        this.unitprice = unitprice;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

//    public void setTime(String time) {
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        try{
//            Date date=sdf.parse(time);
//            this.time=date;
//        }catch (ParseException e){
//            e.printStackTrace();
//        }
//    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "purId='" + purId + '\'' +
                ", isbn='" + isbn + '\'' +
                ", num=" + num +
                ", unitprice=" + unitprice +
                ", time=" + time +
                ", buyer='" + buyer + '\'' +
                ", status=" + status +
                '}';
    }
}


