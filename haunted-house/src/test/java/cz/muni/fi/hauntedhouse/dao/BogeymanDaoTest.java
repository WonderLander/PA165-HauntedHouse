package cz.muni.fi.hauntedhouse.dao;


import cz.muni.fi.hauntedhouse.EntityFactory;
import cz.muni.fi.hauntedhouse.config.PersistenceSampleApplicationContext;
import cz.muni.fi.hauntedhouse.entity.Bogeyman;
import cz.muni.fi.hauntedhouse.entity.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ContextConfiguration(classes= PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class BogeymanDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private BogeymanDao bDao;

    @Autowired
    private HouseDao hDao;


    @PersistenceContext
    private EntityManager em;

    private House h1;
    private House h2;
    private Bogeyman b1;
    private Bogeyman b2;
    private Bogeyman b3;

    @BeforeMethod
    public void setup() {
        h1 = EntityFactory.createCompulsoryHouse();
        h2 = EntityFactory.createCompulsoryHouse();
        b1 = EntityFactory.createCompulsoryBogeyman(h1);
        b2 = EntityFactory.createCompulsoryBogeyman(h1);
        b3 = EntityFactory.createCompulsoryBogeyman(h2);

        //hDao.createHouse(h1);
        //hDao.createHouse(h2);

        //bDao.create(b1);
        //bDao.create(b2);
        //bDao.create(b3);
    }


    /*@Test

    public void createTest() {
        List<Bogeyman> bList = em.createQuery
                ("select b from Bogeyman b", Bogeyman.class).getResultList();

        Assert.assertTrue(bList.contains(b1));
        Assert.assertTrue(bList.contains(b2));
        Assert.assertTrue(bList.contains(b3));
    }*/
}
