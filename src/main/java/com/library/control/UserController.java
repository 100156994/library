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
        ArrayList<User> users=userService.getAllUsers();
        object.put("Rows",users);
        object.put("Total",users.size());
        return object;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteusers", method = RequestMethod.POST)
    public JSONObject deleteUsers(@RequestBody List<User> users) {
        JSONObject object = new JSONObject();
        ArrayList<String> userIds =new ArrayList<>();
        for(int i=0;i<users.size();i++){
            userIds.add(users.get(i).getUser_id());
        }
        //每一个待删除的图书影响的数据库条目 1为成功
        int[] succ = this.userService.deleteUser(userIds);
        boolean flag=true;
        for(int i=0;i<userIds.size();i++){
            if(succ[i]!=1){
                flag=false;
                break;
            }
        }
        if(flag){
            object.put("succ",succ);
            object.put("message",1);
        }else{
            object.put("succ",succ);
            object.put("message",0);
        }
        return object;
    }
}
