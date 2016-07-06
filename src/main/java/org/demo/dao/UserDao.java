package org.demo.dao;

import org.demo.entity.MyUser;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends AbstractDao <MyUser> {
    public MyUser getByUserName(String username) {
        return (MyUser) getSession().get(MyUser.class, username);
    }

    @Override
    public Class<MyUser> getEntityType() {
        return MyUser.class;
    }
}