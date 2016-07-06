package org.demo.dao.insurance;

import org.demo.dao.AbstractDao;
import org.demo.entity.insurance.InsuranceSchemeRule;
import org.springframework.stereotype.Repository;

@Repository
public class InsuranceSchemeRuleDao extends AbstractDao<InsuranceSchemeRule> {
    @Override
    public Class<InsuranceSchemeRule> getEntityType() {
        return InsuranceSchemeRule.class;
    }
}