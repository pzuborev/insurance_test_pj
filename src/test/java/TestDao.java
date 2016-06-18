import org.demo.dao.UserRoleDao;
import org.demo.entity.MyUserRole;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:dispatcher-context.xml"})
@Transactional
public class TestDao {
    private static ApplicationContext context;

    @Autowired
    private UserRoleDao userRoleDao;

    @Test
    public void testGetAllUserRoles() throws Exception {
        System.out.println("************************ testGetAllUserRoles");
        context = new ClassPathXmlApplicationContext("dispatcher-context.xml");
        userRoleDao = (UserRoleDao) context.getBean("userRoleDao");

        Assert.assertNotNull("userRoleDao is null", userRoleDao);
        List<MyUserRole> userRoles =
                userRoleDao.getAll(MyUserRole.class);

        Assert.assertNotNull("user role list is null", userRoles);
        Assert.assertTrue("user role list empty", (userRoles.size() > 0));
    }
}
