import org.demo.dao.UserDao;
import org.demo.dao.UserRoleDao;
import org.demo.entity.MyUser;
import org.demo.entity.MyUserRole;
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
    //private static ApplicationContext context;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private UserDao userDao;

    @Test
    public void testGetAllUserRoles() throws Exception {
        System.out.println("************************ testGetAllUserRoles");
        // context = new ClassPathXmlApplicationContext("dispatcher-context.xml");
        // userRoleDao = (UserRoleDao) context.getBean("userRoleDao");

        Assert.assertNotNull("userRoleDao is null", userRoleDao);
        List<MyUserRole> userRoles = userRoleDao.getAll(MyUserRole.class);

        Assert.assertNotNull("user role list is null", userRoles);
        Assert.assertTrue("user role list empty", (userRoles.size() > 0));
    }

    private MyUser getFakeUser(String username, String role) {
        MyUser user = new MyUser();
        user.setUsername(username);
        user.setPassword(username + "_psw");
        HashSet<MyUserRole> myUserRoles = new HashSet<>(1);
        myUserRoles.add(userRoleDao.getRoleByName(role));
        user.setMyUserRole(myUserRoles);
        return user;
    }

    @Test
    public void testCreateUser() throws Exception {
        System.out.println("************************* testCreateUser");
        Assert.assertNotNull("userDao is null", userDao);
        MyUser user = getFakeUser("test", "ADMIN");

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
        MyUser user = getFakeUser(testUserName, "ADMIN");
        userDao.update(user);


        user = userDao.getByUserName(testUserName);
        Assert.assertNotNull("user is null", user);
        Assert.assertEquals("user name password", user.getPassword(), testUserName + "_psw");
        userDao.flush();
        ArrayList<MyUser> myUsers = (ArrayList<MyUser>) userDao.getAll(MyUser.class);
        for (MyUser u : myUsers) {
            System.out.println(u.getUsername() + " " + u.getPassword());
        }
    }

    @Test
    public void testDeleteUser() throws Exception {
        System.out.println("************************* testDeleteUser");
        MyUser user = getFakeUser("admin", "ADMIN");
        userDao.delete(user);
        userDao.flush();
        ArrayList<MyUser> myUsers = (ArrayList<MyUser>) userDao.getAll(MyUser.class);
        for (MyUser u : myUsers) {
            System.out.println(u.getUsername() + " " + u.getPassword());
        }

    }
}
