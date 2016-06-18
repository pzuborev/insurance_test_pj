package org.demo.dao;

import org.demo.entity.MyUser;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends AbstractDao {
    public MyUser getByUserName(String username) {
        return (MyUser) getSession().get(MyUser.class, username);
    }
}