package com.library.dao;

import com.library.pojo.User;
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
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final static String QUERY_ALL_USERS_SQL="SELECT * FROM users ";

    public ArrayList<User> getAllUsers(){
        final ArrayList<User> users=new ArrayList<>();

        jdbcTemplate.query(QUERY_ALL_USERS_SQL, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()){
                    User user =new User();
                      user.setUser_id(resultSet.getString("user_id"));
                      user.setPassword(resultSet.getString("user_password"));
                      user.setName(resultSet.getString("user_name"));
                      user.setBalance(resultSet.getFloat("user_balance"));
                      user.setBorrow(resultSet.getInt("user_borrow"));
                      user.setOrder(resultSet.getInt("user_order"));
                      user.setBorrowed(resultSet.getInt("user_borrowed"));
                      user.setOrdered(resultSet.getInt("user_ordered"));
                    users.add(user);
                }
            }
        });
        return users;
    }
}
