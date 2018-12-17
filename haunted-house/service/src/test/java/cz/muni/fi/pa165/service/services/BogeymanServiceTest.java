package cz.muni.fi.pa165.service.services;

import cz.muni.fi.pa165.dao.BogeymanDao;
import cz.muni.fi.pa165.entity.Ability;
import cz.muni.fi.pa165.entity.Bogeyman;
import cz.muni.fi.pa165.entity.BogeymanType;
import cz.muni.fi.pa165.entity.House;
import cz.muni.fi.pa165.service.config.ServiceConfig;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @author Martin Wenzl
 */
@ContextConfiguration(classes = ServiceConfig.class)
public class BogeymanServiceTest {

    @InjectMocks
    @Autowired
    private BogeymanServiceImpl bogeymanService;

    @Mock
    private BogeymanDao dao;

    private House h1;
    private House h2;
    private Bogeyman b1;
    private Bogeyman b2;
    private Ability a1;
    private Ability a2;
    private List<Bogeyman> bogeymen;

    @Mock
    private Bogeyman b3;

    @BeforeClass
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setup() {
        h1 = new House();
        a1 = new Ability();
        b1 = new Bogeyman();
        h2 = new House();
        a2 = new Ability();
        b2 = new Bogeyman();
        bogeymen = new ArrayList<>();

        String h1Name = "Letni pole";
        String h1History = "School haunted by ghosts of its bloody past";
        String h1Address = "Somewhere around Summer Field";
        LocalDate h1Date = LocalDate.now();

        h1.setName(h1Name);
        h1.setHistory(h1History);
        h1.setAddress(h1Address);
        h1.setDate(Date.valueOf(h1Date));

        String a1Name = "Third eye";
        a1.setName(a1Name);

        String b1Name = "Ludva";
        BogeymanType b1Type = BogeymanType.WITCH;

        b1.setName(b1Name);
        b1.setType(b1Type);
        b1.setHouse(h1);
        b1.addAbility(a1);

        String h2Name = "Jaroska";
        String h2History = "School haunted by ghosts of its even bloodier past";
        String h2Address = "CapJar Strasse ";
        LocalDate h2Date = LocalDate.now();

        h2.setName(h2Name);
        h2.setHistory(h2History);
        h2.setAddress(h2Address);
        h2.setDate(Date.valueOf(h2Date));

        String a2Name = "Table breaker";
        a2.setName(a2Name);

        String b2Name = "Hanakov";
        BogeymanType b2Type = BogeymanType.LICH;

        b2.setName(b2Name);
        b2.setType(b2Type);
        b2.setHouse(h2);
        b2.addAbility(a2);

        bogeymen.add(b1);
        bogeymen.add(b2);
    }

    @Test
    public void createTest() {
        bogeymanService.create(b1);
        verify(dao).create(b1);
    }

    @Test
    public void deleteTest() {
        bogeymanService.delete(b1);
        verify(dao).delete(b1);
    }

    @Test
    public void updateTest() {
        b3.setHouse(h2);
        bogeymanService.update(b3);
        verify(dao).update(b3);
    }

    @Test
    public void findAllTest() {
        when(dao.findAll()).thenReturn(bogeymen);
        List<Bogeyman> found = bogeymanService.findAll();
        verify(dao).findAll();
        Assert.assertEquals(found.size(), 2);
        Assert.assertTrue(bogeymen.contains(found.get(0)));
        Assert.assertTrue(bogeymen.contains(found.get(1)));
    }

    @Test
    public void findByIdTest() {
        when(dao.findById(1L)).thenReturn(b1);
        Bogeyman bogeyman = bogeymanService.findById(1L);
        verify(dao).findById(1L);
        Assert.assertEquals(bogeyman, b1);
    }

    @Test
    public void findByNameTest() {
        when(dao.findByName("Ludva")).thenReturn(b1);
        Bogeyman bogeyman = bogeymanService.findByName("Ludva");
        verify(dao).findByName("Ludva");
        Assert.assertEquals(bogeyman, b1);
    }

    @Test
    public void findByAbilityTest() {
        when(dao.findByAbility(a1)).thenReturn(Collections.singletonList(b1));
        List<Bogeyman> bogeymanList = bogeymanService.findByAbility(a1);
        verify(dao).findByAbility(a1);
        Assert.assertEquals(bogeymanList.size(), 1);
        Assert.assertTrue(bogeymanList.contains(b1));
    }


    @Test
    public void findByHouseTest() {
        when(dao.findByHouse(h1)).thenReturn(Collections.singletonList(b1));
        List<Bogeyman> bogeymanList = bogeymanService.findByHouse(h1);
        verify(dao).findByHouse(h1);
        Assert.assertEquals(bogeymanList.size(), 1);
        Assert.assertTrue(bogeymanList.contains(b1));
    }

    @Test
    public void findByTypeTest() {
        when(dao.findByType(BogeymanType.WITCH)).thenReturn(Collections.singletonList(b1));
        List<Bogeyman> bogeymanList = bogeymanService.findByType(BogeymanType.WITCH);
        verify(dao).findByType(BogeymanType.WITCH);
        Assert.assertEquals(bogeymanList.size(), 1);
        Assert.assertTrue(bogeymanList.contains(b1));
    }

    @Test
    public void relocateTest() {
        bogeymanService.relocate(b1, h2);
        doAnswer(invocation -> {
            Bogeyman arg0 = invocation.getArgumentAt(0, Bogeyman.class);
            arg0.setHouse(h2);
            return null;
        }).when(dao).update(b1);

        verify(dao).update(b1);
        Assert.assertEquals(b1.getHouse(), h2);
    }

    @Test
    public void swapTest() {
        bogeymanService.swapHouses(b1, b2);
        doAnswer(invocation -> {
            Bogeyman arg0 = invocation.getArgumentAt(0, Bogeyman.class);
            arg0.setHouse(h2);
            return null;
        }).when(dao).update(b1);

        doAnswer(invocation -> {
            Bogeyman arg0 = invocation.getArgumentAt(0, Bogeyman.class);
            arg0.setHouse(h1);
            return null;
        }).when(dao).update(b2);

        Assert.assertEquals(b1.getHouse(), h2);
        Assert.assertEquals(b2.getHouse(), h1);
    }

    @Test
    public void testExceptions() {
        doThrow(QueryTimeoutException.class).when(dao).create(b1);
        doThrow(QueryTimeoutException.class).when(dao).delete(b1);
        doThrow(QueryTimeoutException.class).when(dao).update(b1);
        doThrow(QueryTimeoutException.class).when(dao).findAll();
        doThrow(QueryTimeoutException.class).when(dao).findById(1L);
        doThrow(QueryTimeoutException.class).when(dao).findByName("");
        doThrow(QueryTimeoutException.class).when(dao).findByHouse(h1);
        doThrow(QueryTimeoutException.class).when(dao).findByAbility(a1);
        doThrow(QueryTimeoutException.class).when(dao).findByType(BogeymanType.BANSHEE);

        Assert.assertThrows(DataAccessException.class, () -> bogeymanService.create(b1));
        Assert.assertThrows(DataAccessException.class, () -> bogeymanService.update(b1));
        Assert.assertThrows(DataAccessException.class, () -> bogeymanService.delete(b1));
        Assert.assertThrows(DataAccessException.class, () -> bogeymanService.findAll());
        Assert.assertThrows(DataAccessException.class, () -> bogeymanService.findById(1L));
        Assert.assertThrows(DataAccessException.class, () -> bogeymanService.findByName(""));
        Assert.assertThrows(DataAccessException.class, () -> bogeymanService.findByHouse(h1));
        Assert.assertThrows(DataAccessException.class, () -> bogeymanService.findByAbility(a1));
        Assert.assertThrows(DataAccessException.class, () -> bogeymanService.findByType(BogeymanType.BANSHEE));



    }



}
