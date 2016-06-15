package org.demo.service;

import org.demo.dao.UserRoleDao;
import org.demo.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserRoleService {
    @Autowired

    private UserRoleDao userRoleDao;
    public UserRole getUserRoleByName (String userRoleName) {
        return userRoleDao.getRoleByName(userRoleName);
    }
}
