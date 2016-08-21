package org.demo.service.security;

import org.demo.dao.security.UserRoleDao;
import org.demo.entity.security.InsUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class InsUserRoleService {
    @Autowired
    private UserRoleDao userRoleDao;

    @Transactional(readOnly = true)
    public InsUserRole getUserRoleByName(String userRoleName) {
        return userRoleDao.getRoleByName(userRoleName);
    }

    public Set<InsUserRole> getInsRolesByName(Set<String> roles) {
        Set<InsUserRole> insUserRoles = new HashSet<>(roles.size());
        System.out.println("roles:");
        for (String roleName : roles) {
            System.out.println(roleName);
            insUserRoles.add(userRoleDao.getRoleByName(roleName));
        }
        return insUserRoles;
    }
}
