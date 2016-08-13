package org.demo.dao.insurance;

import org.demo.dao.AbstractDao;
import org.demo.dto.refs.SchemeRiskLookupDto;
import org.demo.entity.insurance.refs.InsuranceScheme;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InsuranceSchemeDao extends AbstractDao<InsuranceScheme> {

    @Override
    public Class getEntityType() {
        return InsuranceScheme.class;
    }



    public List<SchemeRiskLookupDto> getRiskForScheme (int insuranceSchemeId) {
        // todo create view
        List<SchemeRiskLookupDto> objectList =
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
                .setResultTransformer(Transformers.aliasToBean(SchemeRiskLookupDto.class))
                .list();

        return objectList;
    }
}
