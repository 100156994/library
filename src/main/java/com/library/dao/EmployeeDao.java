package com.library.dao;

import com.library.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

@Repository
public class EmployeeDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final static String QUERY_ALL_EMPLOYEES_SQL="SELECT * FROM employee ";

    public ArrayList<Employee> getAllEmployees(){
        final ArrayList<Employee> employees=new ArrayList<Employee>();

        jdbcTemplate.query(QUERY_ALL_EMPLOYEES_SQL, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()){
                    Employee employee =new Employee();
                    employee.setEmp_id(resultSet.getString("emp_id"));
                    employee.setPassword(resultSet.getString("emp_password"));
                    employee.setName(resultSet.getString("emp_name"));
                    employee.setPhone(resultSet.getString("emp_phone"));
                    employee.setEmail(resultSet.getString("emp_email"));
                    employee.setBalance(resultSet.getFloat("emp_balance"));
                    employee.setLastestlogin(resultSet.getDate("emp_lastestlogin"));

                    employees.add(employee);
                }
            }
        });
        return employees;
    }

}

