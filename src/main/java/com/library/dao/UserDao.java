package com.library.dao;

import com.library.pojo.User;
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
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final static String QUERY_ALL_USERS_SQL="SELECT * FROM users ";
    private final static String DELETE_USER_SQL="delete from users where user_id = ?  ";
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


    public int deleteUser(String userId) {
        return jdbcTemplate.update(DELETE_USER_SQL, userId);

    }

    public int[] deleteUser(final ArrayList<String> userIds){
        BatchPreparedStatementSetter setter= new BatchPreparedStatementSetter(){

            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, userIds.get(i));
            }

            public int getBatchSize() {
                return userIds.size();
            }

        };
        return jdbcTemplate.batchUpdate(DELETE_USER_SQL,setter);
    }
}
