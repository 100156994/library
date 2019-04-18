package com.library.service;

import com.library.dao.BookDao;
import com.library.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;

@Service
public class BookService {
    private BookDao bookDao;

    @Autowired
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public ArrayList<Book> queryBook(String searchWord){
        return  bookDao.queryBook(searchWord);
    }

    public ArrayList<Book> getAllBooks(){
        return bookDao.getAllBooks();
    }

    public ArrayList<Book> getAllBooksInStorage(){
            ArrayList<Book> books=bookDao.getAllBooks();
            Iterator<Book> iter = books.iterator();
            while(iter.hasNext()) {
                if (iter.next().getState() == 1) {//已出库的图书
                    iter.remove();
                }
            }
            return books;
    }

    public ArrayList<Book> getAllBooksOutStorage(){
            ArrayList<Book> books=bookDao.getAllBooks();
            Iterator<Book> iter = books.iterator();
            while(iter.hasNext()){
                if(iter.next().getState()==0){//已入库的图书
                    iter.remove();
                }
            }
            return books;
    }

    public int deleteBook(String bookId){
        return bookDao.deleteBook(bookId);
    }

    public boolean matchBook(String searchWord){
        return bookDao.matchBook(searchWord)>0;
    }

    public boolean addBook(Book book){
        return bookDao.addBook(book)>0;
    }

    public Book getBook(String bookId){
        Book book=bookDao.getBook(bookId);
        return book;
    }
    public boolean editBook(Book book){
        return bookDao.editBook(book)>0;
    }

}
