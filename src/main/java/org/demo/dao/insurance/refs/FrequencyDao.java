package org.demo.dao.insurance.refs;

import org.demo.dao.AbstractDao;
import org.demo.entity.insurance.refs.Frequency;
import org.springframework.stereotype.Repository;

@Repository
public class FrequencyDao extends AbstractDao<Frequency> {

    @Override
    public Class<Frequency> getEntityType() {
        return Frequency.class;
    }
}
