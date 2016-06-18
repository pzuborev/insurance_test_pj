package org.demo.service;

import org.demo.dao.UserDao;
import org.demo.dao.UserRoleDao;
import org.demo.dto.UserDto;
import org.demo.entity.MyUser;
import org.demo.entity.MyUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class MyUserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void CreateUser (UserDto userDto) {
        MyUser myUser = new MyUser();
        myUser.setUsername(userDto.getUsername());
        myUser.setPassword(userDto.getPassword());
        if (!userDto.getRoles().isEmpty()) {
            Set<MyUserRole> myUserRoles = new HashSet<>(userDto.getRoles().size());
            System.out.println("roles:");
            for (String roleName : userDto.getRoles()) {
                System.out.println(roleName);
                myUserRoles.add(userRoleDao.getRoleByName(roleName));
            }
            myUser.setMyUserRole(myUserRoles);
        }

        userDao.persist(myUser);
        //myUser = userDao.getByUserName(myUser.getUsername());
    }


    public MyUserRole getUserRoleByName(String userRoleName) {
        return userRoleDao.getRoleByName(userRoleName);
    }
}
