package cz.muni.fi.hauntedhouse.dao;

import cz.muni.fi.hauntedhouse.config.PersistenceSampleApplicationContext;
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

import java.time.LocalDate;


/**
 * @author Ondřej Štursa
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class HouseDaoImplTest extends AbstractTestNGSpringContextTests
{
    @Autowired
    public HouseDao houseDao;

    private House h1;
    private House h2;

    @BeforeMethod
    public void init()
    {
        h1=new House();
        h1.setAddress("Address1");
        h1.setName("house1");
        h1.setHistory("house 1 history");
        h1.setDate(LocalDate.now());

        h2=new House();
        h2.setAddress("Address2");
        h2.setName("house2");
        h2.setHistory("house 2 history");
        h2.setDate(LocalDate.now());

        houseDao.createHouse(h1);
        houseDao.createHouse(h2);
    }

    @Test
    public void findById(){
        House house = houseDao.findHouseById(h1.getId());
        Assert.assertEquals(house,h1);
    }

    @Test
    public void findByIdNotInDb(){
        House house = houseDao.findHouseById(-1l);
        Assert.assertNull(house);
    }

    @Test
    public void findByName(){
        House house = houseDao.findHouseByName("house1");
        Assert.assertEquals(house,h1);
    }

    @Test
    public void findByNameNotInDb(){
        House house = houseDao.findHouseByName("not in db");
        Assert.assertNull(house);
    }

    @Test
    public void createHouse()
    {
        House house = new House();
        house.setAddress("Address");
        house.setName("house");
        house.setHistory("house history");
        house.setDate(LocalDate.now());
        houseDao.createHouse(house);

        House find = houseDao.findHouseById(house.getId());

        Assert.assertEquals(house,find);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createHouseNull(){
        houseDao.createHouse(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createHouseNullProperty(){
        House house = new House();
        house.setAddress("Address");
        house.setName(null);
        houseDao.createHouse(house);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createHouseAlreadyInDd()
    {
        House house = new House();
        house.setAddress("Address1");
        house.setName("house1");
        house.setHistory("house 1 history");
        house.setDate(LocalDate.now());

        houseDao.createHouse(house);
    }

    @Test
    public void deleteHouse(){
        houseDao.deleteHouse(h1);

        Assert.assertNull(houseDao.findHouseByName("house1"));

    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteHouseNull(){
        houseDao.deleteHouse(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteHouseNotInDb(){
        House house = new House();
        house.setAddress("Address1");
        house.setName("house not in db");
        house.setHistory("house history");
        house.setDate(LocalDate.now());

        houseDao.deleteHouse(house);
    }

}

