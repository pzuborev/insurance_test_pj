package org.demo.service.insurance;

import org.demo.dao.insurance.GenderDao;
import org.demo.entity.insurance.Gender;
import org.demo.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GenderService  extends AbstractService<Gender, GenderDao> {

}
