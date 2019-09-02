package com.bupt.service;

import com.bupt.canstants.Canstants;
import com.bupt.dao.UserDao;
import com.bupt.domain.LoginLog;
import com.bupt.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;

/**
 */

@Service
public class UserService {
    private UserDao userDao;



    public String registerPerson(User person) {

        if(null ==person.getUsername() || null ==person.getPassword() || null ==person.getEmail()){
            return Canstants.regFail;
        }

        return null;
    }


    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

}
