package org.demo.dao.insurance;

import org.demo.dao.AbstractDao;
import org.demo.dto.lookup.SchemeRiskDto;
import org.demo.entity.insurance.InsuranceScheme;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InsuranceSchemeDao extends AbstractDao<InsuranceScheme> {

    @Override
    public Class getEntityType() {
        return InsuranceScheme.class;
    }



    public List<SchemeRiskDto> getRiskForScheme (int insuranceSchemeId) {
        // todo create view
        List<SchemeRiskDto> objectList =
                getSession().createSQLQuery(
                "select sr.insuranceschemeid, sr.insurancerisktypeid, sr.forindividualtypeid, " +
                        "      r.name as insurancerisktypename, r.code as insurancerisktypecode, " +
                        "      f.name as forindividualtypename " +
                        "    from insuranceschemerisk sr " +
                        "      join insuracerisktype r on r.id = sr.insurancerisktypeid " +
                        "      join forindividualtype f on f.id = sr.forindividualtypeid " +
                        "    where sr.insuranceschemeid = :insuranceschemeid"
                )
                .setParameter("insuranceschemeid", insuranceSchemeId)
                .setResultTransformer(Transformers.aliasToBean(SchemeRiskDto.class))
                .list();

        return objectList;
    }
}
