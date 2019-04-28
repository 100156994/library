package com.library.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.library.dao.EmployeeDao;
import com.library.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class EmployeeService {

    private EmployeeDao employeeDao;

    @Autowired
    public void setEmployeeDao(EmployeeDao employeeDao) {
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


    public  int[] editEmployee(List<Employee> employees){
        return  employeeDao.editEmployee(employees);
    }

    public int[] addEmployee( JSONArray json){
        ArrayList<Employee> employees =new ArrayList<>();
        for(int i=0;i<json.size();i++){
            JSONObject o = json.getJSONObject(i);
            Employee employee =new Employee();
            employee.setEmp_id(o.getString("emp_id"));
            employee.setPassword("123456");
            employee.setName(o.getString("emp_name"));
            employee.setPhone(o.getString("emp_phone"));
            employee.setEmail(o.getString("emp_email"));
            employee.setBalance((float) 0);
            employee.setLastestlogin(new Date());
            employees.add(employee);
        }
        return employeeDao.addEmployee(employees);
    }
}
