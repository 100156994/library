package com.library.dao;

import com.library.pojo.Employee;
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

@Repository
public class EmployeeDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final static String QUERY_ALL_EMPLOYEES_SQL="SELECT * FROM employee ";
    private final static String DELETE_EMPLOYEE_SQL="delete from employee where emp_id = ?  ";

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

    public int deleteEmployee(String empId) {
        return jdbcTemplate.update(DELETE_EMPLOYEE_SQL, empId);

    }

    public int[] deleteEmployee(final ArrayList<String> empIds){
        BatchPreparedStatementSetter setter= new BatchPreparedStatementSetter(){

            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, empIds.get(i));
            }

            public int getBatchSize() {
                return empIds.size();
            }

        };
        return jdbcTemplate.batchUpdate(DELETE_EMPLOYEE_SQL,setter);
    }

}
