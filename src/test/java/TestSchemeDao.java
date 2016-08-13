import org.demo.dao.insurance.InsuranceSchemeDao;
import org.demo.dto.refs.SchemeRiskLookupDto;
import org.demo.entity.insurance.refs.InsuranceScheme;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:dispatcher-context.xml"})
@Transactional
public class TestSchemeDao {

    @Autowired
    InsuranceSchemeDao schemeDao;

    @Test
    public void testGetAll() throws Exception {
        List<InsuranceScheme> schemes = schemeDao.getAll();

        Assert.assertNotNull("scheme list is null", schemes);
        Assert.assertTrue ("insurance scheme list is empty", schemes.size() > 0);

    }

    @Test
    public void testGetRiskForScheme() throws Exception {

        List<SchemeRiskLookupDto> risks = schemeDao.getRiskForScheme(1);

        Assert.assertNotNull("risks for scheme is null", risks);
        Assert.assertTrue("risks for scheme is empty", risks.size() > 0);

    }
}
