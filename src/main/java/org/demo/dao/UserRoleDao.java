package org.demo.dao;

import org.demo.entity.MyUserRole;
import org.springframework.stereotype.Repository;

@Repository
public class UserRoleDao extends AbstractDao {
    public MyUserRole getRoleByName (String roleName) {
       return (MyUserRole) getSession().createQuery("from userroles o where o.name = :name")
                .setParameter("name", roleName)
                .list().iterator().next();

    }


}
