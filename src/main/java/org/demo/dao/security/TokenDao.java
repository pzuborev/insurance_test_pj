package org.demo.dao.security;

import org.demo.dao.AbstractDao;
import org.demo.entity.security.MyToken;
import org.springframework.stereotype.Repository;

@Repository
public class TokenDao extends AbstractDao<MyToken> {
    @Override
    public Class<MyToken> getEntityType() {
        return MyToken.class;
    }
}
