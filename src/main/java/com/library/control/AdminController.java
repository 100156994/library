package com.library.control;

import com.alibaba.fastjson.JSONObject;
import com.library.pojo.Admin;
import com.library.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.alibaba.fastjson.JSONArray;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {
    private AdminService adminService;

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping("/getAdmin")
    @ResponseBody
    public JSONObject getAdmin(){
        JSONObject object = new JSONObject();
        Admin admin = adminService.getAdmin();
        object.put("id",admin.getAdmin_id());
        object.put("username",admin.getName());
        return object;
    }

    @RequestMapping("/admin_rectify")
    @ResponseBody
    public JSONObject change(@RequestBody JSONObject jsonObject){
        System.out.println(jsonObject);
        JSONObject object =new JSONObject();
        Admin admin =new Admin();
        admin.setPassword(jsonObject.getString("password"));
        admin.setName(jsonObject.getString("username"));
        int succ =adminService.change(admin);
        if(succ==1){
            object.put("message",1);
        }else {
            object.put("message",0);
        }
        return object;
    }
}