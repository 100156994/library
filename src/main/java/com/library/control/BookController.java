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

    @RequestMapping("/book_man.html")
    public ModelAndView bookMan(){
        ModelAndView modelAndView=new ModelAndView("book_man");
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

    @ResponseBody
    @RequestMapping(value = "/editbook", method = RequestMethod.POST)
    public JSONObject editBook(@RequestBody Book book) {
        JSONObject object = new JSONObject();
        System.out.println(book.toString());
        boolean succ = this.bookService.editBook(book);
        if(succ){
            object.put("message","数据更新成功！");
        }else{
            object.put("message","数据更新失败！");
        }
        return object;
    }

    @ResponseBody
    @RequestMapping(value = "/editbooks", method = RequestMethod.POST)
    public JSONObject editBooks(@RequestBody List<Book> books) {
        JSONObject object = new JSONObject();
        int[] succ = this.bookService.editBooks(books);
        //处理每一个book的更新情况 todo

        if(succ[1]==1){
            object.put("succ",succ);
            object.put("message","数据插入成功！");
        }else{
            object.put("succ",succ);
            object.put("message","数据插入失败！");
        }
        return object;
    }

    @ResponseBody
    @RequestMapping(value = "/deletebook", method = RequestMethod.POST)
    public JSONObject deleteBook(@RequestBody Book book) {
        JSONObject object = new JSONObject();

        int succ = this.bookService.deleteBook(book.getBookId());
        //处理每一个book的更新情况 todo

        if(succ==1){
            object.put("succ",succ);
            object.put("message","数据删除成功！");
        }else{
            object.put("succ",succ);
            object.put("message","数据删除失败！");
        }
        return object;
    }

    @ResponseBody
    @RequestMapping(value = "/deletebooks", method = RequestMethod.POST)
    public JSONObject deleteBooks(@RequestBody List<Book> books) {
        JSONObject object = new JSONObject();
        ArrayList<String> bookIds =new ArrayList<String>();
        for(int i=0;i<books.size();i++){
            bookIds.add(books.get(i).getBookId());
        }
        int[] succ = this.bookService.deleteBook(bookIds);
        //处理每一个book的更新情况 todo

        if(succ[1]==1){
            object.put("succ",succ);
            object.put("message","数据删除成功！");
        }else{
            object.put("succ",succ);
            object.put("message","数据删除失败！");
        }
        return object;
    }

}
