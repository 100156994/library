package com.library.pojo;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookAddCommand {


    private String bookId;
    private String isbn;
    private String classnum;
    private String name;
    private String author;
    private String translator;
    private String editionnum;
    private Date pubdate;
    private String publish;
    private int pages;
    private float price;
    private String keyword;
    private String volumeid;
    private String volumename;
    private Date entrytime;
    private int state;
    private int collection;


    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getClassnum() {
        return classnum;
    }

    public void setClassnum(String classnum) {
        this.classnum = classnum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTranslator() {
        return translator;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }

    public String getEditionnum() {
        return editionnum;
    }

    public void setEditionnum(String editionnum) {
        this.editionnum = editionnum;
    }

    public Date getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

        try{
            Date date=sdf.parse(pubdate);
            this.pubdate=date;
        }catch (ParseException e){
            e.printStackTrace();
        }
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getVolumeid() {
        return volumeid;
    }

    public void setVolumeid(String volumeid) {
        this.volumeid = volumeid;
    }

    public String getVolumename() {
        return volumename;
    }

    public void setVolumename(String volumename) {
        this.volumename = volumename;
    }

    public Date getEntrytime() {
        return entrytime;
    }

    public void setEntrytime(String entrytime) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

        try{
            Date date=sdf.parse(entrytime);
            this.entrytime=date;
        }catch (ParseException e){
            e.printStackTrace();
        }
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getCollection() {
        return collection;
    }

    public void setCollection(int collection) {
        this.collection = collection;
    }
}
