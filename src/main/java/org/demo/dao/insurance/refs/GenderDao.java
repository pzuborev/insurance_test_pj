package org.demo.dao.insurance.refs;

import org.demo.dao.AbstractDao;
import org.demo.entity.insurance.refs.Gender;
import org.springframework.stereotype.Repository;

@Repository
public class GenderDao extends AbstractDao<Gender> {

    @Override
    public Class<Gender> getEntityType() {
        return Gender.class;
    }
}
