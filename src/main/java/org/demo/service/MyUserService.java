package org.demo.service;

import org.demo.dao.UserDao;
import org.demo.dao.UserRoleDao;
import org.demo.dto.UserDto;
import org.demo.entity.MyUser;
import org.demo.entity.MyUserRole;
import org.demo.exception.ApiException;
import org.demo.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service

public class MyUserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private MyUserRoleService userRoleService;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }



    @Transactional
    public void create(UserDto userDto) {
        MyUser myUser = new MyUser();
        myUser.setUsername(userDto.getUsername());
        myUser.setPassword(userDto.getPassword());
        if (userDto.getRoles() != null)
            myUser.setMyUserRole(userRoleService.getMyRolesByName (userDto.getRoles()));

        userDao.persist(myUser);
    }

    @Transactional
    public void update(String userName, UserDto userDto) {
        if (userDto.getUsername() == "") userDto.setUsername(userName);
        else if (userDto.getUsername() != userName)
                throw new ApiException(String.format("You can not change username. Old username '%s'. New username '%s'",
                        userName, userDto.getUsername()));

        MyUser user = userDao.getByUserName(userName);

        if (user == null)
            throw new UserNotFoundException(userName);

        if (userDto.getRoles() != null)
            user.setMyUserRole(userRoleService.getMyRolesByName(userDto.getRoles()));

        if (userDto.getPassword() != null)
            user.setPassword(userDto.getPassword());

        userDao.update(user);
    }
    @Transactional(readOnly = true)
    public  MyUser getByUserName(String username) {
        return userDao.getByUserName(username);
    }

}
