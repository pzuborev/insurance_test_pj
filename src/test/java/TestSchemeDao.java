import org.demo.dao.InsuranceSchemeDao;
import org.demo.entity.InsuranceScheme;
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
        List<InsuranceScheme> schemes = schemeDao.getAll(InsuranceScheme.class);

        Assert.assertNotNull("scheme list is null", schemes);
        Assert.assertTrue ("insurance scheme list is empty", schemes.size() > 0);

    }
}
