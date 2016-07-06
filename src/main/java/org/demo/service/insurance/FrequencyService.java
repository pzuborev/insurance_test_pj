package org.demo.service.insurance;

import org.demo.dao.insurance.FrequencyDao;
import org.demo.entity.insurance.Frequency;
import org.demo.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FrequencyService  extends AbstractService<Frequency, FrequencyDao> {

}
