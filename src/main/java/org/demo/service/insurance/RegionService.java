package org.demo.service.insurance;

import org.demo.dao.insurance.refs.RegionDao;
import org.demo.entity.insurance.refs.Region;
import org.demo.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegionService  extends AbstractService<Region, RegionDao> {

}
