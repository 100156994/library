package com.library.control;

import com.alibaba.fastjson.JSONObject;;
import com.library.pojo.User;
import com.library.service.UserService;
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
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/allusers")
    @ResponseBody
    public JSONObject allUsers(){
        JSONObject object = new JSONObject();
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        ArrayList<User> users=userService.getAllUsers();
        // String nvrsjson = JSONArray.toJSONString(books);
        object.put("Rows",users);
        object.put("Total",users.size());
        return object;
    }


}
