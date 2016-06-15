package org.demo.service;

import org.demo.dao.UserDao;
import org.demo.entity.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MyUserService {
    @Autowired
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public MyUser CreateUser (MyUser myUser) {
        userDao.persist(myUser);
        return userDao.getByUserName(myUser.getUsername());
    }

}
