package com.library.control;

import com.alibaba.fastjson.JSONObject;;
import com.library.pojo.Employee;
import com.library.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping("/allemployees")
    @ResponseBody
    public JSONObject allEmployees(){
        JSONObject object = new JSONObject();
        ArrayList<Employee> employees=employeeService.getAllEmployees();
        object.put("Rows",employees);
        object.put("Total",employees.size());
        return object;
    }


}
