package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.config.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Ondrej Krcma
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class UserDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    UserDao userDao;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setPasswordHash("hashash");
        user.setEmail("mail@gmail.com");
        user.setAdmin(true);

        userDao.create(user);

        Assert.assertEquals(userDao.findAll().size(), 1);
    }

}
