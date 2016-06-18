package org.demo.dao;

import org.demo.entity.UserRole;
import org.springframework.stereotype.Repository;

@Repository
public class UserRoleDao extends AbstractDao {
    public UserRole getRoleByName (String roleName) {
       return (UserRole) getSession().createQuery("from userroles o where o.name = :name")
                .setParameter("name", roleName)
                .list().iterator().next();

    }
}
