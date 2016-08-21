package org.demo.dao.security;

import org.demo.dao.AbstractDao;
import org.demo.entity.security.InsUser;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao extends AbstractDao<InsUser> {
    public InsUser getByUserName(String username) {
        InsUser insUser = (InsUser) getSession().get(InsUser.class, username);
        if (insUser == null)
            throw new UsernameNotFoundException(username);
        return insUser;
    }

    @Override
    public Class<InsUser> getEntityType() {
        return InsUser.class;
    }

    public InsUser getByToken(String token) {
        List<InsUser> insUsers = getSession().createSQLQuery(
                "select u.* from users u\n" +
                        "join tokens t on t.username = u.username\n" +
                        "where t.token = :token")
                .addEntity(InsUser.class)
                .setParameter("token", token)
                .list();
        if (insUsers.size() > 0) return insUsers.iterator().next();
        else return null;
    }
}