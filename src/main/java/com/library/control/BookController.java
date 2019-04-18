package com.library.control;

import com.alibaba.fastjson.JSONObject;
import com.library.pojo.Book;
import com.library.pojo.BookAddCommand;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.alibaba.fastjson.JSONArray;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BookController {
    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/test.html")
    public ModelAndView test(){
        ArrayList<Book> books=bookService.getAllBooks();
        ModelAndView modelAndView=new ModelAndView("test");
        return modelAndView;
    }

    @RequestMapping("/allbooks.html")
    public ModelAndView allBook(){
        ModelAndView modelAndView=new ModelAndView("hellow");
        return modelAndView;
    }

    @RequestMapping("/allbooks")
    @ResponseBody
    public JSONObject allBooks(){
        JSONObject object = new JSONObject();
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        ArrayList<Book> books=bookService.getAllBooks();
       // String nvrsjson = JSONArray.toJSONString(books);
        object.put("Rows",books);
        object.put("Total",books.size());
        return object;
    }

    @RequestMapping("/booksinstorage")
    @ResponseBody
    public JSONObject allBookInStorage(){
        JSONObject object = new JSONObject();
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        ArrayList<Book> books=bookService.getAllBooksInStorage();
        // String nvrsjson = JSONArray.toJSONString(books);
        object.put("Rows",books);
        object.put("Total",books.size());
        return object;
    }

    @RequestMapping("/booksoutstorage")
    @ResponseBody
    public JSONObject allBookOutStorage(){
        JSONObject object = new JSONObject();
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        ArrayList<Book> books=bookService.getAllBooksOutStorage();
        // String nvrsjson = JSONArray.toJSONString(books);
        object.put("Rows",books);
        object.put("Total",books.size());
        return object;
    }






}
