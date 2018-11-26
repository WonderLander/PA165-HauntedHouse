package cz.muni.fi.pa165.service.services;

import cz.muni.fi.pa165.dao.HouseDao;
import cz.muni.fi.pa165.entity.Bogeyman;
import cz.muni.fi.pa165.entity.Comment;
import cz.muni.fi.pa165.entity.House;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.testng.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.RecoverableDataAccessException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Ondrej Stursa
 */

public class HouseServiceTest
{
    @Mock
    private HouseDao houseDao;

    @InjectMocks
    @Autowired
    private HouseServiceImpl houseService;



    private House h1;
    private House h2;

    @Mock
    private House h3;

    @Mock
    private Bogeyman bogeyman;

    @Mock
    private Comment comment;

    private List<House> houses;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        h1= new House();
        h1.setName("House one");
        h1.setAddress("Address one");
        h1.setDate(LocalDate.MAX);
        h1.setHistory("House one history");
        h2 = new House();
        h2.setName("House two");
        h2.setAddress("Address two");
        h2.setDate(LocalDate.now());
        h2.setHistory("History two");


        houses=new ArrayList<>();
        houses.add(h1);
        houses.add(h2);
    }

    @Test
    public void findByIdTest(){
        long id = 1l;
        when(houseDao.findHouseById(id)).thenReturn(h1);
        House house = houseService.findHouseById(id);
        Assert.assertEquals(house,h1);
        verify(houseDao).findHouseById(id);
    }

    @Test
    public void findByNameTest(){
        String name = "House one";
        when(houseDao.findHouseByName(name)).thenReturn(h1);
        House house = houseService.findHouseByName(name);
        Assert.assertEquals(house,h1);
        verify(houseDao).findHouseByName(name);
    }

    @Test
    public void createTest(){
        houseService.createHouse(h1);
        verify(houseDao).createHouse(h1);
    }

    @Test
    public void deleteTest(){
        houseService.deleteHouse(h1);
        verify(houseDao).deleteHouse(h1);
    }

    @Test
    public void findAll(){
        when(houseDao.findAll()).thenReturn(houses);

        List<House>h=houseService.findAll();
        Assert.assertEquals(h,houses);
        verify(houseDao).findAll();
    }

    @Test
    public void updateTest(){
        houseService.createHouse(h1);
        verify(houseDao).createHouse(h1);

        h1.setName("New name one");

        houseService.updateHouse(h1);
        verify(houseDao).updateHouse(h1);
    }

    @Test
    public void getSortedHousesAfterDateTest(){
        when(houseDao.findAll()).thenReturn(houses);

        List<House>h=houseService.getSortedHousesAfterDate(LocalDate.MIN);
        Assert.assertEquals(h.size(),2);
        Assert.assertEquals(h.get(1),h2);

        verify(houseDao).findAll();

        h=houseService.getSortedHousesAfterDate(LocalDate.now());
        Assert.assertEquals(h.size(),1);
        Assert.assertEquals(h.get(0),h1);


    }

    @Test
    public void findByAddressTest(){
        String address = "Address two";
        when(houseDao.findAll()).thenReturn(houses);
        House house = houseService.findByAddress(address);
        Assert.assertEquals(house,h2);
        verify(houseDao).findAll();
    }

    @Test
    public void isHauntedByBogeymanTest(){
        when(h3.getBogeymen()).thenReturn(Collections.singletonList(bogeyman));

        Assert.assertTrue(houseService.isHauntedByBogeyman(h3));
    }

    @Test
    public void getCommentsTest(){
        when(h3.getComments()).thenReturn(Collections.singletonList(comment));

        List<Comment>c=houseService.getComments(h3);

        Assert.assertEquals(c.size(),1);
        Assert.assertEquals(c.get(0),comment);
    }

    @Test
    public void getBogeyman(){
        when(h3.getBogeymen()).thenReturn(Collections.singletonList(bogeyman));

        List<Bogeyman>b = houseService.getBogeymen(h3);

        Assert.assertEquals(b.size(),1);
        Assert.assertEquals(b.get(0),bogeyman);
    }

    @Test
    public void commentHouse(){
        houseService.commentHouse(h3,comment);
        verify(h3).addComment(comment);
    }

    @Test
    public void testExceptions(){
        when(houseDao.findHouseById(0l)).thenThrow(new IllegalArgumentException());
        when(houseDao.findHouseByName("Not name")).thenThrow(new IllegalArgumentException());
        when(houseDao.findAll()).thenThrow(new RecoverableDataAccessException(""));

        Assert.assertThrows(DataAccessException.class, () -> houseService.findHouseById(0l));
        Assert.assertThrows(DataAccessException.class, () -> houseService.findAll());
        Assert.assertThrows(DataAccessException.class, () -> houseService.findHouseByName("Not name"));
        Assert.assertThrows(DataAccessException.class, () -> houseService.getSortedHousesAfterDate(LocalDate.now()));
    }
}
