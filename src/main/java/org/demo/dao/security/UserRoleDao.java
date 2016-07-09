package org.demo.dao.security;

import org.demo.dao.AbstractDao;
import org.demo.entity.security.MyUserRole;
import org.springframework.stereotype.Repository;

@Repository
public class UserRoleDao extends AbstractDao<MyUserRole> {
    public MyUserRole getRoleByName (String roleName) {
       return (MyUserRole) getSession().createQuery("from userroles o where o.name = :name")
                .setParameter("name", roleName)
                .list().iterator().next();

    }


    @Override
    public Class<MyUserRole> getEntityType() {
        return MyUserRole.class;
    }
}
