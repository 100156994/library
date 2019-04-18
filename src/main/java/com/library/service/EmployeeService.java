package com.library.service;

import com.library.dao.EmployeeDao;
import com.library.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
@Service
public class EmployeeService {

    private EmployeeDao employeeDao;

    @Autowired
    public void setUserDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public ArrayList<Employee> getAllEmployees(){
        return employeeDao.getAllEmployees();
    }

    public int deleteEmployee(String empId){
        return employeeDao.deleteEmployee(empId);
    }

    public int[] deleteEmployee(ArrayList<String> empIds){
        return employeeDao.deleteEmployee(empIds);
    }

}
