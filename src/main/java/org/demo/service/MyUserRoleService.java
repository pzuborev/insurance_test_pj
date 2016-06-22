package org.demo.service;

import org.demo.dao.UserRoleDao;
import org.demo.entity.MyUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class MyUserRoleService {
    @Autowired
    private UserRoleDao userRoleDao;

    @Transactional(readOnly = true)
    public MyUserRole getUserRoleByName(String userRoleName) {
        return userRoleDao.getRoleByName(userRoleName);
    }

    public Set<MyUserRole> getMyRolesByName (Set<String> roles) {
        Set<MyUserRole> myUserRoles = new HashSet<>(roles.size());
        System.out.println("roles:");
        for (String roleName : roles) {
            System.out.println(roleName);
            myUserRoles.add(userRoleDao.getRoleByName(roleName));
        }
        return myUserRoles;
    }
}