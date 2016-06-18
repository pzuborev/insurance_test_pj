package org.demo.service;

import org.demo.dao.UserDao;
import org.demo.dto.UserDto;
import org.demo.entity.MyUser;
import org.demo.entity.UserRole;
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

    public void CreateUser (UserDto userDto) {
        MyUser myUser = new MyUser();
        myUser.setUsername(userDto.getUsername());
        myUser.setPassword(userDto.getPassword());
        userDao.persist(myUser);
        //myUser = userDao.getByUserName(myUser.getUsername());
    }


}
