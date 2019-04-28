package com.library.service;

import com.library.dao.AdminDao;
import com.library.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
@Service

public class AdminService {

    private AdminDao adminDao;

    @Autowired
    public void setAdminDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    public Admin getAdmin(){
        return adminDao.getAdmin();
    }

}
