package org.demo.dao.insurance;

import org.demo.dao.AbstractDao;
import org.demo.entity.insurance.Frequency;
import org.springframework.stereotype.Repository;

@Repository
public class FrequencyDao extends AbstractDao<Frequency> {

    @Override
    public Class<Frequency> getEntityType() {
        return Frequency.class;
    }
}
