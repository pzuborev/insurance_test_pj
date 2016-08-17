package org.demo.dao.insurance.tariff;

import org.demo.dao.AbstractDao;
import org.demo.entity.insurance.TableTariff;
import org.demo.entity.insurance.refs.EventRiskType;
import org.demo.entity.insurance.refs.InsuranceScheme;
import org.demo.entity.insurance.refs.Region;
import org.hibernate.SQLQuery;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;

@Repository
public class TableTariffDao extends AbstractDao<TableTariff> {

    @Override
    public Class<TableTariff> getEntityType() {
        return TableTariff.class;
    }

    public HashMap<Integer, BigDecimal> getTableTariff(
            InsuranceScheme insuranceScheme,
            Region region,
            Date arcDate,
            List<Integer> riskIds,
            String genderCode,
            int age
    ) {
        HashMap<Integer, BigDecimal> result = new HashMap<>();

        SQLQuery query = getSession().createSQLQuery(
                new StringBuilder()
                .append(" select st.insuracerisktypeid,  tv.value ")
                .append(" from schemetabletariff st ")
                .append(" join tabletariff tt on tt.id = st.tabletariffid ")
                .append(" join tariffvalue tv on tv.tabletariffid = tt.id ")
                .append(" where st.insuranceschemeid = :insuranceschemeid ")
                .append("   and st.regionid = :region ")
                .append("   and :arcdate BETWEEN st.validfrom and st.validtill ")
                .append("   and st.insuracerisktypeid in (:riskIds) ")
                .append("   and tv.genderCode = :gender ")
                .append("   and tv.age = :age ")
                .append(" order by st.insuracerisktypeid, tv.genderCode, tv.age").toString()
        );
        query.addScalar("insuracerisktypeid");
        query.addScalar("value");

        query.setParameter("insuranceschemeid", insuranceScheme.getId());
        query.setParameter("region", region.getId());
        query.setParameter("arcdate", arcDate);
        query.setParameter("gender", genderCode);
        query.setParameter("age", age);
        query.setParameterList("riskIds", riskIds);

        query.setResultTransformer(new ResultTransformer() {
            @Override
            public Object transformTuple(Object[] tuple, String[] aliases) {
                result.put( (Integer)tuple[0], (BigDecimal)tuple[1]);
                return null;
            }

            @Override
            public List transformList(List collection) {
                return collection;
            }
        });
        query.list();

        return result;

    }
}
