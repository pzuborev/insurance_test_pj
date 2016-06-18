import org.demo.config.HibernateConfiguration;
import org.demo.config.MyAppConfig;
import org.demo.dao.UserRoleDao;
import org.demo.entity.MyUserRole;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MyAppConfig.class, HibernateConfiguration.class})
public class TestDao {

    @Autowired
    private UserRoleDao userRoleDao;

    @Test
    public void testGetAllUserRoles() throws Exception {
        List<MyUserRole> userRoles =
                userRoleDao.getAll(MyUserRole.class);

        Assert.assertNotNull("user role list is null", userRoles);
        Assert.assertTrue("user role list empty", (userRoles.size() > 0));
    }
}
