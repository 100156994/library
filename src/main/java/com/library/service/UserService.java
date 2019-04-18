package com.library.service;

import com.library.dao.UserDao;
import com.library.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
@Service
public class UserService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public ArrayList<User> getAllUsers(){
            return userDao.getAllUsers();
    }


    public int deleteUser(String userId){
        return userDao.deleteUser(userId);
    }

    public int[] deleteUser(ArrayList<String> userIds){
        return userDao.deleteUser(userIds);
    }
}
