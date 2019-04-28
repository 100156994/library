package com.library.dao;

import com.library.pojo.Purchase;
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

@Repository
public class PurchaseDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private final static String ADD_PURCHASE_SQL="INSERT INTO purchase VALUES(?,?,?,?,?,?,?,?,?,?,?)";
    private final static String DELETE_PURCHASE_SQL="delete from purchase where pur_id = ?  ";
    private final static String EDIT_PURCHASE_SQL="update purchase set isbn=?,pur_bookname =?,pur_bookauthor=?,pur_bookpublicdate=?," +
            "pur_bookpublisher=?num=?,unitprice=?,time=?,buyer=?,status=? where pur_id= ? ";
    private final static String QUERY_ALL_PURCHASES_SQL="SELECT * FROM purchase ";
    private final static String QUERY_PURCHASE_SQL="SELECT * FROM purchase WHERE pur_id like  ?  or isbn like ?";
    private final static String MATCH_PURCHASE_SQL="SELECT count(*) FROM purchase WHERE pur_id like ?  or isbn like ?  ";
    private final static String GET_PURCHASE_SQL="SELECT * FROM purchase where pur_id = ? ";


    public int matchPurchase(String searchWord){
        String swcx="%"+searchWord+"%";
        return jdbcTemplate.queryForObject(MATCH_PURCHASE_SQL,new Object[]{swcx,swcx},Integer.class);
    }

    public ArrayList<Purchase> queryPurchase(String sw){
        String swcx="%"+sw+"%";
        final ArrayList<Purchase> purchases=new ArrayList<Purchase>();
        jdbcTemplate.query(QUERY_PURCHASE_SQL, new Object[]{swcx,swcx}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()){
                    Purchase purchase =new Purchase();

                    purchase.setPurId(resultSet.getString("pur_id"));
                    purchase.setIsbn(resultSet.getString("isbn"));
                    purchase.setBuyer(resultSet.getString("pur_buyer"));
                    purchase.setNum(resultSet.getInt("pur_num"));
                    purchase.setUnitprice(resultSet.getFloat("pur_unitprice"));
                    purchase.setTime(resultSet.getDate("pur_time"));
                    purchase.setStatus(resultSet.getInt("status"));
                    purchase.setBook_author(resultSet.getString("pur_bookauthor"));
                    purchase.setBookpublicdate(resultSet.getDate("pur_bookpublicdate"));
                    purchase.setBook_name(resultSet.getString("pur_bookname"));
                    purchase.setBookpublisher(resultSet.getString("pur_bookpublisher"));

                    purchases.add(purchase);
                }
            }
        });
        return purchases;
    }

    public ArrayList<Purchase> getAllPurchases(){
        final ArrayList<Purchase> purchases=new ArrayList<Purchase>();

        jdbcTemplate.query(QUERY_ALL_PURCHASES_SQL, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()){
                    Purchase purchase =new Purchase();

                    purchase.setPurId(resultSet.getString("pur_id"));
                    purchase.setIsbn(resultSet.getString("isbn"));
                    purchase.setBuyer(resultSet.getString("pur_buyer"));
                    purchase.setNum(resultSet.getInt("pur_num"));
                    purchase.setUnitprice(resultSet.getFloat("pur_unitprice"));
                    purchase.setTime(resultSet.getDate("pur_time"));
                    purchase.setStatus(resultSet.getInt("pur_status"));
                    purchase.setBook_author(resultSet.getString("pur_bookauthor"));
                    purchase.setBookpublicdate(resultSet.getDate("pur_bookpublicdate"));
                    purchase.setBook_name(resultSet.getString("pur_bookname"));
                    purchase.setBookpublisher(resultSet.getString("pur_bookpublisher"));
                    purchases.add(purchase);
                }
            }
        });
        return purchases;

    }

    public int deletePurchase(String purId){

        return jdbcTemplate.update(DELETE_PURCHASE_SQL,purId);
    }
    public int[] deletePurchase(final ArrayList<String> purIds){
        BatchPreparedStatementSetter setter= new BatchPreparedStatementSetter(){

            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, purIds.get(i));
            }

            public int getBatchSize() {
                return purIds.size();
            }

        };
        return jdbcTemplate.batchUpdate(DELETE_PURCHASE_SQL,setter);
    }

    public int addPurchase(Purchase purchase){

        String purId=purchase.getPurId();
        String isbn=purchase.getIsbn();
        int num=purchase.getNum();
        float unitprice=purchase.getUnitprice();
        Date time=purchase.getTime();
        String buyer=purchase.getBuyer();
        int status = purchase.getStatus();
        String book_author =purchase.getBook_author();
        String book_pub =purchase.getBookpublisher();
        String book_name =purchase.getBook_name();
        java.sql.Date  date =new java.sql.Date(purchase.getBookpublicdate().getTime());

        return jdbcTemplate.update(ADD_PURCHASE_SQL,new Object[]{purId,isbn,book_name,book_author,date,book_pub,num,unitprice,time,buyer,status});
    }

    public Purchase getPurchase(String purchaseId){
        final Purchase purchase =new Purchase();
        jdbcTemplate.query(GET_PURCHASE_SQL, new Object[]{purchaseId}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                purchase.setPurId(resultSet.getString("pur_id"));
                purchase.setIsbn(resultSet.getString("isbn"));
                purchase.setBuyer(resultSet.getString("pur_buyer"));
                purchase.setNum(resultSet.getInt("pur_num"));
                purchase.setUnitprice(resultSet.getFloat("pur_unitprice"));
                purchase.setTime(resultSet.getDate("pur_time"));
                purchase.setStatus(resultSet.getInt("status"));
                purchase.setBook_author(resultSet.getString("pur_bookauthor"));
                purchase.setBookpublicdate(resultSet.getDate("pur_bookpublicdate"));
                purchase.setBook_name(resultSet.getString("pur_bookname"));
                purchase.setBookpublisher(resultSet.getString("pur_bookpublisher"));

            }

        });
        return purchase;
    }
    public int editPurchase(Purchase purchase){
        String purId=purchase.getPurId();
        String isbn=purchase.getIsbn();
        int num=purchase.getNum();
        float unitprice=purchase.getUnitprice();
        Date time=purchase.getTime();
        String buyer=purchase.getBuyer();
        int status = purchase.getStatus();
        String book_author =purchase.getBook_author();
        String book_pub =purchase.getBookpublisher();
        String book_name =purchase.getBook_name();
        java.sql.Date  date =new java.sql.Date(purchase.getBookpublicdate().getTime());
        return jdbcTemplate.update(EDIT_PURCHASE_SQL,new Object[]{purId,isbn,book_name,book_author,date,book_pub,num,unitprice,time,buyer,status});
    }

}