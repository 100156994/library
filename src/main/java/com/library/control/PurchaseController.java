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
@Controller
public class PurchaseController {


    private PurchaseService purchaseService;

    @Autowired
    public void setPurchaseService(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @RequestMapping("/querypurchase.html")
    public ModelAndView queryPurchaseDo(HttpServletRequest request, String searchWord){
        boolean exist=purchaseService.matchPurchase(searchWord);
        if (exist){
            ArrayList<Purchase> purchases = purchaseService.querypurchase(searchWord);
            ModelAndView modelAndView = new ModelAndView("admin_purchases");
            modelAndView.addObject("purchases",purchases);
            return modelAndView;
        }
        else{
            return new ModelAndView("admin_purchases","error","没有匹配的采购记录");
        }
    }

    @ResponseBody
    //, method = RequestMethod.POST
    @RequestMapping(value = "/test1")
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
    //, method = RequestMethod.POST
    @RequestMapping(value = "/test")
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
