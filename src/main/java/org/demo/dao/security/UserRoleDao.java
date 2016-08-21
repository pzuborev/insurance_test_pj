package org.demo.dao.security;

import org.demo.dao.AbstractDao;
import org.demo.entity.security.InsUserRole;
import org.springframework.stereotype.Repository;

@Repository
public class UserRoleDao extends AbstractDao<InsUserRole> {
    public InsUserRole getRoleByName (String roleName) {
       return (InsUserRole) getSession().createQuery("from userroles o where o.name = :name")
                .setParameter("name", roleName)
                .list().iterator().next();

    }


    @Override
    public Class<InsUserRole> getEntityType() {
        return InsUserRole.class;
    }
}
