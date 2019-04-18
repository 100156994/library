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
        ArrayList<Book> books=bookService.getAllBooks();
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





    @RequestMapping("/querybook.html")
    public ModelAndView queryBookDo(HttpServletRequest request, String searchWord){
        boolean exist=bookService.matchBook(searchWord);
        if (exist){
            ArrayList<Book> books = bookService.queryBook(searchWord);
            ModelAndView modelAndView = new ModelAndView("admin_books");
            modelAndView.addObject("books",books);
            return modelAndView;
        }
        else{
            return new ModelAndView("admin_books","error","没有匹配的图书");
        }
    }
    @RequestMapping("/reader_querybook.html")
    public ModelAndView readerQueryBook(){
       return new ModelAndView("reader_book_query");

    }
    @RequestMapping("/reader_querybook_do.html")
    public String readerQueryBookDo(HttpServletRequest request, String searchWord, RedirectAttributes redirectAttributes){
        boolean exist=bookService.matchBook(searchWord);
        if (exist){
            ArrayList<Book> books = bookService.queryBook(searchWord);
            redirectAttributes.addFlashAttribute("books", books);
            return "redirect:/reader_querybook.html";
        }
        else{
            redirectAttributes.addFlashAttribute("error", "没有匹配的图书！");
            return "redirect:/reader_querybook.html";
        }
    }



    @RequestMapping("/deletebook.html")
    public String deleteBook(HttpServletRequest request, RedirectAttributes redirectAttributes){
        String bookId=request.getParameter("bookId");
        int res=bookService.deleteBook(bookId);

        if (res==1){
            redirectAttributes.addFlashAttribute("succ", "图书删除成功！");
            return "redirect:/allbooks.html";
        }else {
            redirectAttributes.addFlashAttribute("error", "图书删除失败！");
            return "redirect:/allbooks.html";
        }
    }

    @RequestMapping("/book_add.html")
    public ModelAndView addBook(HttpServletRequest request){

            return new ModelAndView("admin_book_add");

    }

    @RequestMapping("/book_add_do.html")
    public String addBookDo(BookAddCommand bookAddCommand,RedirectAttributes redirectAttributes){
        Book book=new Book();
        //book.setBookId(0);

        book.setBookId(bookAddCommand.getBookId());
        book.setIsbn(bookAddCommand.getIsbn());
        book.setClassnum(bookAddCommand.getClassnum());
        book.setName(bookAddCommand.getName());
        book.setAuthor(bookAddCommand.getAuthor());
        book.setTranslator(bookAddCommand.getTranslator());
        book.setEditionnum(bookAddCommand.getEditionnum());
        book.setPubdate(bookAddCommand.getPubdate());
        book.setPublish(bookAddCommand.getPublish());
        book.setPages(bookAddCommand.getPages());
        book.setPrice(bookAddCommand.getPrice());
        book.setKeyword(bookAddCommand.getKeyword());
        book.setVolumeid(bookAddCommand.getVolumeid());
        book.setVolumename(bookAddCommand.getVolumename());
        book.setEntrytime(bookAddCommand.getEntrytime());
        book.setState(bookAddCommand.getState());
        book.setCollection(bookAddCommand.getCollection());


        boolean succ=bookService.addBook(book);
        ArrayList<Book> books=bookService.getAllBooks();
        if (succ){
            redirectAttributes.addFlashAttribute("succ", "图书添加成功！");
            return "redirect:/allbooks.html";
        }
        else {
            redirectAttributes.addFlashAttribute("succ", "图书添加失败！");
            return "redirect:/allbooks.html";
        }
    }

    @RequestMapping("/updatebook.html")
    public ModelAndView bookEdit(HttpServletRequest request){
        String bookId=request.getParameter("bookId");
        Book book=bookService.getBook(bookId);
        ModelAndView modelAndView=new ModelAndView("admin_book_edit");
        modelAndView.addObject("detail",book);
        return modelAndView;
    }



    @RequestMapping("/book_edit_do.html")
    public String bookEditDo(HttpServletRequest request, BookAddCommand bookAddCommand, RedirectAttributes redirectAttributes){
        //String bookId= request.getParameter("id");
        Book book=new Book();

        book.setBookId(bookAddCommand.getBookId());
        book.setIsbn(bookAddCommand.getIsbn());
        book.setClassnum(bookAddCommand.getClassnum());
        book.setName(bookAddCommand.getName());
        book.setAuthor(bookAddCommand.getAuthor());
        book.setTranslator(bookAddCommand.getTranslator());
        book.setEditionnum(bookAddCommand.getEditionnum());
        book.setPubdate(bookAddCommand.getPubdate());
        book.setPublish(bookAddCommand.getPublish());
        book.setPages(bookAddCommand.getPages());
        book.setPrice(bookAddCommand.getPrice());
        book.setKeyword(bookAddCommand.getKeyword());
        book.setVolumeid(bookAddCommand.getVolumeid());
        book.setVolumename(bookAddCommand.getVolumename());
        book.setEntrytime(bookAddCommand.getEntrytime());
        book.setState(bookAddCommand.getState());
        book.setCollection(bookAddCommand.getCollection());


        boolean succ=bookService.editBook(book);
        if (succ){
            redirectAttributes.addFlashAttribute("succ", "图书修改成功！");
            return "redirect:/allbooks.html";
        }
        else {
            redirectAttributes.addFlashAttribute("error", "图书修改失败！");
            return "redirect:/allbooks.html";
        }
    }


    @RequestMapping("/bookdetail.html")
    public ModelAndView bookDetail(HttpServletRequest request){
        String bookId=request.getParameter("bookId");
        Book book=bookService.getBook(bookId);
        ModelAndView modelAndView=new ModelAndView("admin_book_detail");
        modelAndView.addObject("detail",book);
        return modelAndView;
    }



    @RequestMapping("/readerbookdetail.html")
    public ModelAndView readerBookDetail(HttpServletRequest request){
        String bookId=request.getParameter("bookId");
        Book book=bookService.getBook(bookId);
        ModelAndView modelAndView=new ModelAndView("reader_book_detail");
        modelAndView.addObject("detail",book);
        return modelAndView;
    }



}
