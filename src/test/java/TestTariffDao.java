import org.demo.dao.insurance.tariff.TableTariffDao;
import org.demo.entity.insurance.refs.InsuranceScheme;
import org.demo.entity.insurance.refs.Region;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:dispatcher-context.xml"})
@Transactional
public class TestTariffDao {

    @Autowired
    TableTariffDao tableTariffDao;

    @Test
    public void testGetTariff() throws Exception {
        java.util.Date now = new java.util.Date();
        ArrayList<Integer> riskIds = new ArrayList<Integer>();
        riskIds.add(1);
        riskIds.add(2);
        riskIds.add(3);
        riskIds.add(4);
        riskIds.add(5);


        HashMap<Integer, BigDecimal> h =
                tableTariffDao.getTableTariff(
                        new InsuranceScheme(1),
                        new Region(1),
                        new Date(now.getTime()),
                        riskIds,
                        "M",
                        60
                );
        System.out.println("************************** " + h.get(1));
        System.out.println("************************** " + h.get(2));
        System.out.println("************************** " + h.get(3));
        System.out.println("************************** " + h.get(4));
        System.out.println("************************** " + h.get(5));
    }
}
