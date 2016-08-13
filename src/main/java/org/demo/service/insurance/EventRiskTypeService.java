package org.demo.service.insurance;

import org.demo.dao.insurance.EventRiskTypeDao;
import org.demo.entity.insurance.refs.EventRiskType;
import org.demo.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EventRiskTypeService  extends AbstractService<EventRiskType, EventRiskTypeDao> {

}
