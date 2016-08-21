package org.demo.dao.security;

import org.demo.dao.AbstractDao;
import org.demo.entity.security.InsToken;
import org.springframework.stereotype.Repository;

@Repository
public class TokenDao extends AbstractDao<InsToken> {
    @Override
    public Class<InsToken> getEntityType() {
        return InsToken.class;
    }
}
