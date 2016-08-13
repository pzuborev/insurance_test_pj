package org.demo.dao.insurance;

import org.demo.dao.AbstractDao;
import org.demo.entity.insurance.refs.EventRiskType;
import org.springframework.stereotype.Repository;

@Repository
public class EventRiskTypeDao extends AbstractDao<EventRiskType> {
    @Override
    public Class<EventRiskType> getEntityType() {
        return EventRiskType.class;
    }
}
