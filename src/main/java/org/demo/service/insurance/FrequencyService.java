package org.demo.service.insurance;

import org.demo.dao.insurance.refs.FrequencyDao;
import org.demo.entity.insurance.refs.Frequency;
import org.demo.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FrequencyService  extends AbstractService<Frequency, FrequencyDao> {

}
