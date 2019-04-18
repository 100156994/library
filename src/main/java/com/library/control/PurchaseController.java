package com.library.control;

import com.alibaba.fastjson.JSONObject;
import com.library.pojo.Purchase;
import com.library.pojo.BookAddCommand;
import com.library.pojo.User;
import com.library.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.alibaba.fastjson.JSONArray;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class PurchaseController {


    private PurchaseService purchaseService;

    @Autowired
    public void setPurchaseService(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @RequestMapping("/allpurs")
    @ResponseBody
    public JSONObject allPurchases(){
        JSONObject object = new JSONObject();
        ArrayList<Purchase> purchases=purchaseService.getAllpurchases();
        // String nvrsjson = JSONArray.toJSONString(books);
        object.put("Rows",purchases);
        object.put("Total",purchases.size());
        return object;
    }

    @ResponseBody

    @RequestMapping(value = "/test1", method = RequestMethod.POST)
    public JSONObject insert(@RequestBody Purchase purchase) {
        JSONObject object = new JSONObject();
        System.out.println(purchase.toString());
        boolean succ = this.purchaseService.addPurchase(purchase);
        if(succ){
            object.put("message","数据插入成功！");
        }else{
            object.put("message","数据插入失败！");
        }
        return object;
    }

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public JSONObject test(@RequestBody User user) {
        JSONObject object = new JSONObject();
        System.out.println(user.toString());
        boolean succ=true;
        //boolean succ = this.purchaseService.addPurchase(purchase);
        if(succ){
            object.put("message","数据插入成功！");
        }else{
            object.put("message","数据插入失败！");
        }
        return object;
    }
}
