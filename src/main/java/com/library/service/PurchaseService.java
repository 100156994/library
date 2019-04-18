package com.library.service;

import com.library.dao.PurchaseDao;
import com.library.pojo.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;

@Service
public class PurchaseService {
    private PurchaseDao purchaseDao;

    @Autowired
    public void setpurchaseDao(PurchaseDao purchaseDao) {
        this.purchaseDao = purchaseDao;
    }

    public ArrayList<Purchase> querypurchase(String searchWord){
        return  purchaseDao.queryPurchase(searchWord);
    }

    public ArrayList<Purchase> getAllpurchases(){
        return purchaseDao.getAllPurchases();
    }

    public int deletePurchase(String purchaseId){
        return purchaseDao.deletePurchase(purchaseId);
    }

    public int[] deletePurchase(ArrayList<String> purchaseIds){
        return purchaseDao.deletePurchase(purchaseIds);
    }

    public boolean matchPurchase(String searchWord){
        return purchaseDao.matchPurchase(searchWord)>0;
    }

    public boolean addPurchase(Purchase purchase){
        return purchaseDao.addPurchase(purchase)>0;
    }

    public Purchase getpurchase(String purchaseId){
        Purchase purchase=purchaseDao.getPurchase(purchaseId);
        return purchase;
    }

    public boolean editpurchase(Purchase purchase){
        return purchaseDao.editPurchase(purchase)>0;
    }
}
