package com.library.pojo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Purchase implements Serializable{
    private String purId;
    private String isbn;
    private String book_name;
    private String book_author;
    private String bookpublisher;
    private Date bookpublicdate;
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

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public String getBookpublisher() {
        return bookpublisher;
    }

    public void setBookpublisher(String bookpublisher) {
        this.bookpublisher = bookpublisher;
    }

    public Date getBookpublicdate() {
        return bookpublicdate;
    }

    public void setBookpublicdate(Date bookpublicdate) {
        this.bookpublicdate = bookpublicdate;
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


