package org.demo.dao;

import org.demo.entity.MyUser;

public class UserDao extends AbstractDao {
    public MyUser getByUserName(String username) {
        return (MyUser) getSession().get(MyUser.class, username);
    }
}