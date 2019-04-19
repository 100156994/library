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
import java.util.List;

@Repository
public class EmployeeDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final static String QUERY_ALL_EMPLOYEES_SQL="SELECT * FROM employee ";
    private final static String DELETE_EMPLOYEE_SQL="delete from employee where emp_id = ?  ";
    private final static String EDIT_BOOK_SQL="update employee set emp_name=?,emp_phone=?,emp_email=?,emp_balance=?,emp_lastestlogin=? where emp_id= ? ;";
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

   public  int[] editEmployee(final List<Employee> employees){
        BatchPreparedStatementSetter setter= new BatchPreparedStatementSetter(){
            public void setValues(PreparedStatement ps, int i) throws SQLException {
            Employee employee = employees.get(i);
            java.sql.Date lastDate =new java.sql.Date(employee.getLastestlogin().getTime());
            ps.setString(1,employee.getName());
            ps.setString(2,employee.getPhone());
            ps.setString(3,employee.getName());
            ps.setString(4,employee.getEmail());
            ps.setFloat(5,employee.getBalance());
            ps.setDate(6,lastDate);
            ps.setString(7,employee.getEmp_id());

        }

            public int getBatchSize() {
            return employees.size();
            }

        };
        return jdbcTemplate.batchUpdate(EDIT_BOOK_SQL,setter);
    }

}

