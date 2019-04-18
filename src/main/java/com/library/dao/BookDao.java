package com.library.dao;

import com.library.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

@Repository
public class BookDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final static String ADD_BOOK_SQL="INSERT INTO book VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private final static String DELETE_BOOK_SQL="delete from book where book_id = ?  ";
    private final static String EDIT_BOOK_SQL="update book set isbn=?,classnum=?,name=?,author=?,translator=?,editionnum=?,pubdate=?,publish=?,pages=?,price=?,keyword=?,volumeid=?,volumename=?,entrytime=?,state=?,collection=? where book_id= ? ;";
    private final static String QUERY_ALL_BOOKS_SQL="SELECT * FROM book ";
    private final static String QUERY_BOOK_SQL="SELECT * FROM book WHERE book_id like  ?  or name like ?   ";
    //查询匹配图书的个数
    private final static String MATCH_BOOK_SQL="SELECT count(*) FROM book WHERE book_id like ?  or name like ?  ";
    //根据书号查询图书
    private final static String GET_BOOK_SQL="SELECT * FROM book where book_id = ? ";

    public int matchBook(String searchWord){
        String swcx="%"+searchWord+"%";
        return jdbcTemplate.queryForObject(MATCH_BOOK_SQL,new Object[]{swcx,swcx},Integer.class);
    }

    public ArrayList<Book> queryBook(String sw){
        String swcx="%"+sw+"%";
        final ArrayList<Book> books=new ArrayList<Book>();
        jdbcTemplate.query(QUERY_BOOK_SQL, new Object[]{swcx,swcx}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()){
                    Book book =new Book();

                    book.setBookId(resultSet.getString("book_id"));
                    book.setIsbn(resultSet.getString("isbn"));
                    book.setClassnum(resultSet.getString("classnum"));
                    book.setName(resultSet.getString("name"));
                    book.setAuthor(resultSet.getString("author"));
                    book.setTranslator(resultSet.getString("translator"));
                    book.setEditionnum(resultSet.getString("editionnum"));
                    book.setPubdate(resultSet.getDate("pubdate"));
                    book.setPublish(resultSet.getString("publish"));
                    book.setPages(resultSet.getInt("pages"));
                    book.setPrice(resultSet.getFloat("price"));
                    book.setKeyword(resultSet.getString("keyword"));
                    book.setVolumeid(resultSet.getString("volumeid"));
                    book.setVolumename(resultSet.getString("volumename"));
                    book.setEntrytime(resultSet.getDate("entrytime"));
                    book.setState(resultSet.getInt("state"));
                    book.setCollection(resultSet.getInt("collection"));

                    books.add(book);
                }
            }
        });
        return books;
    }

    public ArrayList<Book> getAllBooks(){
        final ArrayList<Book> books=new ArrayList<Book>();

        jdbcTemplate.query(QUERY_ALL_BOOKS_SQL, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                    while (resultSet.next()){
                        Book book =new Book();
                        book.setBookId(resultSet.getString("book_id"));
                        book.setIsbn(resultSet.getString("isbn"));
                        book.setClassnum(resultSet.getString("book_classnum"));
                        book.setName(resultSet.getString("book_name"));
                        book.setAuthor(resultSet.getString("book_author"));
                        book.setTranslator(resultSet.getString("book_translator"));
                        book.setEditionnum(resultSet.getString("book_editionnum"));
                        book.setPubdate(resultSet.getDate("book_publicdate"));
                        book.setPublish(resultSet.getString("book_publisher"));
                        book.setPages(resultSet.getInt("book_pages"));
                        book.setPrice(resultSet.getFloat("book_price"));
                        book.setKeyword(resultSet.getString("book_keyword"));
                        book.setVolumeid(resultSet.getString("book_volumeid"));
                        book.setVolumename(resultSet.getString("book_volumename"));
                        book.setEntrytime(resultSet.getDate("book_entrytime"));
                        book.setState(resultSet.getInt("book_status"));
                        book.setCollection(resultSet.getInt("book_collection"));
                        System.out.println(book.getAuthor());
//                        System.console(book.getAuthor());
                        books.add(book);
                    }
            }
        });
        return books;

    }

    public int deleteBook(String bookId){

        return jdbcTemplate.update(DELETE_BOOK_SQL,bookId);
    }

    public int addBook(Book book){

        String bookId=book.getBookId();
        String isbn=book.getIsbn();
        String classnum=book.getClassnum();
        String name=book.getName();
        String author=book.getAuthor();
        String translator=book.getTranslator();
        String editionnum=book.getEditionnum();
        Date pubdate=book.getPubdate();
        String publish=book.getPublish();
        int pages=book.getPages();
        float price=book.getPrice();
        String keyword=book.getKeyword();
        String volumeid=book.getVolumeid();
        String volumename=book.getVolumename();
        Date entrytime=book.getEntrytime();
        int state=book.getState();
        int collection=book.getCollection();


        return jdbcTemplate.update(ADD_BOOK_SQL,new Object[]{bookId,isbn,classnum,name,author,translator,editionnum,pubdate,publish,pages,price,keyword,volumeid,volumename,entrytime,state,collection});
    }

    public Book getBook(String bookId){
        final Book book =new Book();
        jdbcTemplate.query(GET_BOOK_SQL, new Object[]{bookId}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                book.setBookId(resultSet.getString("book_id"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setClassnum(resultSet.getString("classnum"));
                book.setName(resultSet.getString("name"));
                book.setAuthor(resultSet.getString("author"));
                book.setTranslator(resultSet.getString("translator"));
                book.setEditionnum(resultSet.getString("editionnum"));
                book.setPubdate(resultSet.getDate("pubdate"));
                book.setPublish(resultSet.getString("publish"));
                book.setPages(resultSet.getInt("pages"));
                book.setPrice(resultSet.getFloat("price"));
                book.setKeyword(resultSet.getString("keyword"));
                book.setVolumeid(resultSet.getString("volumeid"));
                book.setVolumename(resultSet.getString("volumename"));
                book.setEntrytime(resultSet.getDate("entrytime"));
                book.setState(resultSet.getInt("state"));
                book.setCollection(resultSet.getInt("collection"));
            }

        });
        return book;
    }
    public int editBook(Book book){
        String bookId=book.getBookId();
        String isbn=book.getIsbn();
        String classnum=book.getClassnum();
        String name=book.getName();
        String author=book.getAuthor();
        String translator=book.getTranslator();
        String editionnum=book.getEditionnum();
        Date pubdate=book.getPubdate();
        String publish=book.getPublish();
        int pages=book.getPages();
        float price=book.getPrice();
        String keyword=book.getKeyword();
        String volumeid=book.getVolumeid();
        String volumename=book.getVolumename();
        Date entrytime=book.getEntrytime();
        int state=book.getState();
        int collection=book.getCollection();

        return jdbcTemplate.update(EDIT_BOOK_SQL,new Object[]{isbn,classnum,name,author,translator,editionnum,pubdate,publish,pages,price,keyword,volumeid,volumename,entrytime,state,collection,bookId});
    }


}
