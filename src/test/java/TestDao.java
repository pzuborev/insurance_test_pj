import org.demo.dao.security.UserDao;
import org.demo.dao.security.UserRoleDao;
import org.demo.entity.security.InsUser;
import org.demo.entity.security.InsUserRole;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:dispatcher-context.xml"})
@Transactional
public class TestDao {
    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private UserDao userDao;

    @Test
    public void testGetAllUserRoles() throws Exception {
        System.out.println("************************ testGetAllUserRoles");

        Assert.assertNotNull("userRoleDao is null", userRoleDao);
        List<InsUserRole> userRoles = userRoleDao.getAll();

        Assert.assertNotNull("user role list is null", userRoles);
        Assert.assertTrue("user role list empty", (userRoles.size() > 0));
    }

    private InsUser getFakeUser(String username, String role) {
        InsUser user = new InsUser();
        user.setUsername(username);
        user.setPassword(username + "_psw");
        HashSet<InsUserRole> insUserRoles = new HashSet<>(1);
        insUserRoles.add(userRoleDao.getRoleByName(role));
        user.setInsUserRole(insUserRoles);
        return user;
    }

    @Test
    public void testCreateUser() throws Exception {
        System.out.println("************************* testCreateUser");
        Assert.assertNotNull("userDao is null", userDao);
        InsUser user = getFakeUser("test", "ADMIN");

        userDao.persist(user);
        userDao.flush();
        user = userDao.getByUserName("test");
        Assert.assertNotNull("user is null", user);
        Assert.assertEquals("user name password", user.getPassword(), "test_psw");
    }

    @Test
    public void testUpdateUser() throws Exception {
        String testUserName = "admin";
        System.out.println("************************ testUpdateUser");
        InsUser user = getFakeUser(testUserName, "ADMIN");
        userDao.update(user);


        user = userDao.getByUserName(testUserName);
        Assert.assertNotNull("user is null", user);
        Assert.assertEquals("user name password", user.getPassword(), testUserName + "_psw");
        userDao.flush();
        ArrayList<InsUser> insUsers = (ArrayList<InsUser>) userDao.getAll();
        for (InsUser u : insUsers) {
            System.out.println(u.getUsername() + " " + u.getPassword());
        }
    }

    @Test
    public void testDeleteUser() throws Exception {
        System.out.println("************************* testDeleteUser");
        InsUser user = getFakeUser("admin", "ADMIN");
        userDao.delete(user);
        userDao.flush();
        ArrayList<InsUser> insUsers = (ArrayList<InsUser>) userDao.getAll();
        for (InsUser u : insUsers) {
            System.out.println(u.getUsername() + " " + u.getPassword());
        }

    }


    @Test
    public void testGetUserByName() throws Exception {
        InsUser insUser = userDao.getByUserName("admin");
        Assert.assertNotNull("insUser is null", insUser);
        System.out.println("********* "+ insUser.getUsername());
    }

    @Test
    public void testGetUserByToken() throws Exception {
        InsUser insUser = userDao.getByToken("1");
        Assert.assertNotNull("insUser is null", insUser);
        System.out.println("********* "+ insUser.getUsername());

    }
}
