package org.demo.service.insurance;

import org.demo.dao.insurance.refs.InsuranceSchemeDao;
import org.demo.dto.refs.SchemeRiskLookupDto;
import org.demo.entity.insurance.refs.InsuranceScheme;
import org.demo.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InsuranceSchemeService extends AbstractService<InsuranceScheme, InsuranceSchemeDao> {
    public List<SchemeRiskLookupDto> getRiskForScheme (int insuranceSchemeId) {
        return dao.getRiskForScheme(insuranceSchemeId);
    }
}
