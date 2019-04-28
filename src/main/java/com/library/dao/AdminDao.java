package com.library.dao;

import com.library.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Repository
public class AdminDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final static String ADMIN_SQL ="select * from admin";
    private final static String UPDATE_SQL="update admin set admin_name=?,admin_password=? where admin_id =0001";

    public Admin getAdmin(){
        final Admin admin =new Admin();
        jdbcTemplate.query(ADMIN_SQL,  new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {

                admin.setName(resultSet.getString("admin_name"));
                admin.setAdmin_id(resultSet.getString("admin_id"));
                admin.setPassword(resultSet.getString("admin_password"));
                admin.setEmail(resultSet.getString("admin_email"));
                admin.setPhone(resultSet.getString("admin_phone"));
                admin.setLastestlogin(resultSet.getDate("admin_lastestlogin"));
            }

        });
        return admin;
    }

    public int update(Admin admin){

        return jdbcTemplate.update(UPDATE_SQL,new Object[]{admin.getName(),admin.getPassword()});
    }
}
