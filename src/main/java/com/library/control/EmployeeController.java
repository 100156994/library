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


    @ResponseBody
    @RequestMapping(value = "/deleteemps", method = RequestMethod.POST)
    public JSONObject deleteEmps(@RequestBody List<Employee> emps) {
        JSONObject object = new JSONObject();
        ArrayList<String> empIds =new ArrayList<>();
        for(int i=0;i<emps.size();i++){
            empIds.add(emps.get(i).getEmp_id());
        }
        //每一个待删除的图书影响的数据库条目 1为成功
        int[] succ = this.employeeService.deleteEmployee(empIds);
        boolean flag=true;
        for(int i=0;i<empIds.size();i++){
            if(succ[i]!=1){
                flag=false;
                break;
            }
        }
        if(flag){
            object.put("succ",succ);
            object.put("message","员工删除成功！");
        }else{
            object.put("succ",succ);
            object.put("message","员工删除失败！");
        }
        return object;
    }


    @ResponseBody
    @RequestMapping(value = "/addemps", method = RequestMethod.POST)
    public  JSONObject addEmps(@RequestBody JSONArray json){
        JSONObject object = new JSONObject();
        int[] succ =employeeService.addEmployee(json);
        if(succ[0]==1){
            object.put("succ",succ);
            object.put("message",1);
        }else{
            object.put("succ",succ);
            object.put("message",0);
        }
        return object;
    }


    @ResponseBody
    @RequestMapping(value = "/editemps", method = RequestMethod.POST)
    public JSONObject editEmps(@RequestBody List<Employee> emps) {
        JSONObject object = new JSONObject();
        int[] succ = this.employeeService.editEmployee(emps);
        boolean flag=true;
        for(int i=0;i<emps.size();i++){
            if(succ[i]!=1){
                flag=false;
                break;
            }
        }
        if(flag){
            object.put("succ",succ);
            object.put("message","员工删除成功！");
        }else{
            object.put("succ",succ);
            object.put("message","员工删除失败！");
        }
        return object;
    }


}
