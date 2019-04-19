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

    @RequestMapping("/hello.html")
    public ModelAndView hello(){

        return new ModelAndView("hello");
    }

    @RequestMapping("/admin/index.html")
    public ModelAndView adminBookInf(){

        return new ModelAndView("admin_book_inf");
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
        object.put("Rows",books);
        object.put("Total",books.size());
        return object;
    }

    //暂时没有用到
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
    public JSONObject editBooks(@RequestBody ArrayList<Book> books) {
        JSONObject object = new JSONObject();
        int[] succ = this.bookService.editBooks(books);

        System.out.println(books.get(0).toString());
        if(succ[0]==1){
            object.put("succ",succ);
            object.put("message","数据插入成功！");
        }else{
            object.put("succ",succ);
            object.put("message","数据插入失败！");
        }
        return object;
    }

    //暂时没有用到
    @ResponseBody
    @RequestMapping(value = "/deletebook", method = RequestMethod.POST)
    public JSONObject deleteBook(@RequestBody Book book) {
        JSONObject object = new JSONObject();

        int succ = this.bookService.deleteBook(book.getBookId());
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
        ArrayList<String> bookIds =new ArrayList<>();
        for(int i=0;i<books.size();i++){
            bookIds.add(books.get(i).getBookId());
        }
        //每一个待删除的图书影响的数据库条目 1为成功
        int[] succ = this.bookService.deleteBook(bookIds);
        boolean flag=true;
        for(int i=0;i<bookIds.size();i++){
            if(succ[i]!=1){
                flag=false;
                break;
            }
        }
        if(flag){
            object.put("succ",succ);
            object.put("message","图书删除成功！");
        }else{
            object.put("succ",succ);
            object.put("message","图书删除失败！");
        }
        return object;
    }

}
