package org.demo.service.insurance;

import org.demo.dao.insurance.InsuranceSchemeDao;
import org.demo.dto.lookup.SchemeRiskDto;
import org.demo.entity.insurance.InsuranceScheme;
import org.demo.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InsuranceSchemeService extends AbstractService<InsuranceScheme, InsuranceSchemeDao> {
    public List<SchemeRiskDto> getRiskForScheme (int insuranceSchemeId) {
        return dao.getRiskForScheme(insuranceSchemeId);
    }
}
