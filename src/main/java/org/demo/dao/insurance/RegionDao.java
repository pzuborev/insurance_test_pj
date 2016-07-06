package org.demo.dao.insurance;

import org.demo.dao.AbstractDao;
import org.demo.entity.insurance.Region;
import org.springframework.stereotype.Repository;

@Repository
public class RegionDao extends AbstractDao<Region> {
    @Override
    public Class<Region> getEntityType() {
        return Region.class;
    }
}
