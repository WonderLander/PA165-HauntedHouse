package cz.muni.fi.hauntedhouse.dao;


import cz.muni.fi.hauntedhouse.EntityFactory;
import cz.muni.fi.hauntedhouse.config.PersistenceSampleApplicationContext;
import cz.muni.fi.hauntedhouse.entity.Ability;
import cz.muni.fi.hauntedhouse.entity.Bogeyman;
import cz.muni.fi.hauntedhouse.entity.BogeymanType;
import cz.muni.fi.hauntedhouse.entity.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Martin Wenzl.
 */
@ContextConfiguration(classes= PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class BogeymanDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private BogeymanDao bDao;

    @Autowired
    private HouseDao hDao;

    @Autowired
    private AbilityDao aDao;


    @PersistenceContext
    private EntityManager em;

    private House h1;
    private House h2;
    private Bogeyman b1;
    private Bogeyman b2;
    private Bogeyman b3;
    private Ability a1;
    private Ability a2;

    @BeforeMethod
    public void setup() {
        h1 = EntityFactory.createCompulsoryHouse();
        h2 = EntityFactory.createCompulsoryHouse();
        a1 = EntityFactory.createCompulsoryAbility();
        a2 = EntityFactory.createCompulsoryAbility();
        b1 = EntityFactory.createCompulsoryBogeyman(h1, a1);
        b2 = EntityFactory.createCompulsoryBogeyman(h1, a2);
        b3 = EntityFactory.createCompulsoryBogeyman(h2, a1);

        hDao.createHouse(h1);
        hDao.createHouse(h2);

        aDao.createAbility(a1);
        aDao.createAbility(a2);

        bDao.create(b1);
        bDao.create(b2);
        bDao.create(b3);
    }


    @Test
    public void createTest() {
        List<Bogeyman> found = em.createQuery
                ("select b from Bogeyman b", Bogeyman.class).getResultList();

        Assert.assertEquals(3, found.size());

        Assert.assertTrue(found.contains(b1));
        Assert.assertTrue(found.contains(b2));
        Assert.assertTrue(found.contains(b3));
    }

    @Test
    public void findByIdTest() {
        Bogeyman found = bDao.findById(b1.getId());
        Assert.assertEquals(b1, found);
    }

    @Test
    public void findByNameTest() {
        Bogeyman found = bDao.findByName(b2.getName());
        Assert.assertEquals(b2, found);
    }

    @Test
    public void findByHouseTest() {
        List<Bogeyman> found = bDao.findByHouse(h1);

        Assert.assertEquals(2, found.size());

        Assert.assertTrue(found.contains(b1));
        Assert.assertTrue(found.contains(b2));
    }

    @Test
    public void findByAbilityTest() {
        List<Bogeyman> found = bDao.findByAbility(a1);

        Assert.assertEquals(2, found.size());

        Assert.assertTrue(found.contains(b1));
        Assert.assertTrue(found.contains(b3));
    }

    @Test
    public void findByTypeTest() {
        for (BogeymanType t : BogeymanType.values()) {
            int typeCount = 0;
            List<Bogeyman> found = bDao.findByType(t);
            if (b1.getType() == t) {
                Assert.assertTrue(found.contains(b1));
                typeCount++;
            }
            if (b2.getType() == t) {
                Assert.assertTrue(found.contains(b2));
                typeCount++;
            }
            if (b3.getType() == t) {
                Assert.assertTrue(found.contains(b3));
                typeCount++;
            }
            Assert.assertEquals(found.size(), typeCount);
        }
    }

    @Test
    public void findAllTest() {
        List<Bogeyman> found = bDao.findAll();
        Assert.assertEquals(found.size(), 3);
        Assert.assertTrue(found.contains(b1));
        Assert.assertTrue(found.contains(b2));
        Assert.assertTrue(found.contains(b3));
    }

    @Test
    public void deleteTest() {
        String name = b1.getName();
        bDao.delete(b1);
        Assert.assertTrue(bDao.findByName(name) == null);
    }


}
