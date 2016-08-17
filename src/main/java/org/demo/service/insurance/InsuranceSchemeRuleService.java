package org.demo.service.insurance;

import org.demo.dao.insurance.refs.InsuranceSchemeRuleDao;
import org.demo.entity.insurance.refs.InsuranceSchemeRule;
import org.demo.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InsuranceSchemeRuleService  extends AbstractService<InsuranceSchemeRule, InsuranceSchemeRuleDao> {

}
