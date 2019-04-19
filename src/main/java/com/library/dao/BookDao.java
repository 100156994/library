package com.library.dao;

import com.library.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class BookDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final static String ADD_BOOK_SQL="INSERT INTO book VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private final static String DELETE_BOOK_SQL="delete from book where book_id = ?  ";
    private final static String EDIT_BOOK_SQL="update book set isbn=?,book_classnum=?,book_name=?,book_author=?,book_translator=?" +
            ",book_editionnum=?,book_publicdate=?,book_publisher=?,book_pages=?,book_price=?,book_keyword=?,book_volumeid=?,book_volumename=?," +
            "book_entrytime=?,book_status=?,book_collection=? where book_id= ? ;";
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

    public int[] deleteBook(final ArrayList<String> bookIds){
        BatchPreparedStatementSetter setter= new BatchPreparedStatementSetter(){

            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, bookIds.get(i));
            }

            public int getBatchSize() {
                return bookIds.size();
            }

        };
        return jdbcTemplate.batchUpdate(DELETE_BOOK_SQL,setter);
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

    public int[] editBooks(final List<Book> books){
        BatchPreparedStatementSetter setter= new BatchPreparedStatementSetter(){

            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Book book = books.get(i);


                Date pubdate=book.getPubdate();
                java.sql.Date sDate =new java.sql.Date(pubdate.getTime());
                java.sql.Date esDate =new java.sql.Date(book.getEntrytime().getTime());
                ps.setString(1,book.getIsbn());
                ps.setString(2,book.getClassnum());
                ps.setString(3,book.getName());
                ps.setString(4,book.getAuthor());
                ps.setString(5,book.getTranslator());
                ps.setString(6,book.getEditionnum());
                ps.setDate(7,sDate);
                ps.setString(8,book.getPublish());
                ps.setInt(9,book.getPages());
                ps.setFloat(10,book.getPrice());
                ps.setString(11,book.getKeyword());
                ps.setString(12,book.getVolumeid());
                ps.setString(13,book.getVolumename());
                ps.setDate(14,esDate);
                ps.setInt(15,book.getState());
                ps.setInt(16,book.getCollection());
                ps.setString(17,book.getBookId());

            }

            public int getBatchSize() {
                return books.size();
            }

        };
        return jdbcTemplate.batchUpdate(EDIT_BOOK_SQL,setter);
    }

}
