package org.demo.dao.security;

import org.demo.dao.AbstractDao;
import org.demo.entity.security.MyUser;
import org.hibernate.transform.Transformers;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao extends AbstractDao<MyUser> {
    public MyUser getByUserName(String username) {
        MyUser myUser = (MyUser) getSession().get(MyUser.class, username);
        if (myUser == null)
            throw new UsernameNotFoundException(username);
        return myUser;
    }

    @Override
    public Class<MyUser> getEntityType() {
        return MyUser.class;
    }

    public MyUser getByToken(String token) {
        List<MyUser> myUsers = getSession().createSQLQuery(
                "select u.* from users u\n" +
                        "join tokens t on t.username = u.username\n" +
                        "where t.token = :token")
                .addEntity(MyUser.class)
                .setParameter("token", token)
                .list();
        if (myUsers.size() > 0) return myUsers.iterator().next();
        else return null;
    }
}