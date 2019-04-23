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
    @RequestMapping(value = "/deletepurs", method = RequestMethod.POST)
    public JSONObject deleteUsers(@RequestBody List<Purchase> purs) {
        JSONObject object = new JSONObject();
        ArrayList<String> purIds =new ArrayList<>();
        for(int i=0;i<purs.size();i++){
            purIds.add(purs.get(i).getPurId());
        }
        //每一个待删除的图书影响的数据库条目 1为成功
        int[] succ = this.purchaseService.deletePurchase(purIds);
        boolean flag=true;
        for(int i=0;i<purIds.size();i++){
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



    //测试
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
    @RequestMapping(value = "/test2", method = RequestMethod.POST)
    public JSONObject insert(@RequestBody List<Purchase> purchases) {
        JSONObject object = new JSONObject();
        System.out.println(purchases.get(0).toString());
        //boolean succ = this.purchaseService.addPurchase(purchases);
        if(true){
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
