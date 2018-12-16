package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.BogeymanDto;
import cz.muni.fi.pa165.dto.CommentDto;
import cz.muni.fi.pa165.dto.HouseCreateDto;
import cz.muni.fi.pa165.dto.HouseDto;
import cz.muni.fi.pa165.entity.Bogeyman;
import cz.muni.fi.pa165.entity.Comment;
import cz.muni.fi.pa165.entity.House;
import cz.muni.fi.pa165.service.config.ServiceConfig;
import cz.muni.fi.pa165.service.services.BeanMappingService;
import cz.muni.fi.pa165.service.services.HouseService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Ondrej Stursa
 */
@ContextConfiguration(classes = ServiceConfig.class)
public class HouseFacadeTest {
    @InjectMocks
    @Autowired
    private HouseFacadeImpl houseFacade;

    private HouseDto houseDto;
    private HouseDto houseDto2;

    @Mock
    private HouseCreateDto houseCreateDto;

    @Mock
    private HouseService houseService;

    @Mock
    private BeanMappingService beanMappingService;

    @Mock
    private Comment comment;

    @Mock
    private CommentDto commentDto;

    @Mock
    private House house;

    @Mock
    private Bogeyman bogeyman;

    @Mock
    private BogeymanDto bogeymanDto;

    private List<HouseDto> houseDtos = new ArrayList<>();
    private List<House> houses;
    private List<Comment> comments;
    private List<CommentDto>commentDtos;
    private List<Bogeyman> bogeymen;
    private List<BogeymanDto> bogeymanDtos;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        houseDto=new HouseDto();
        houseDto.setName("House one");
        houseDto.setAddress("Address one");
        houseDto.setDate(LocalDate.MAX);
        houseDto.setHistory("History one");

        houseDto2=new HouseDto();
        houseDto2.setName("House two");
        houseDto2.setAddress("Address two");
        houseDto2.setDate(LocalDate.now());
        houseDto2.setHistory("History two");

        houseDtos.add(houseDto);
        houseDtos.add(houseDto2);

        houses= Collections.singletonList(house);
        comments=Collections.singletonList(comment);
        commentDtos=Collections.singletonList(commentDto);
        bogeymen=Collections.singletonList(bogeyman);
        bogeymanDtos=Collections.singletonList(bogeymanDto);
    }

    @Test
    public void findByIdTest(){
        long id = 1l;
        when(houseService.findHouseById(id)).thenReturn(house);
        when(beanMappingService.mapTo(house,HouseDto.class)).thenReturn(houseDto);

        HouseDto h = houseFacade.findHouseById(id);

        Assert.assertEquals(h,houseDto);

        verify(houseService).findHouseById(id);
    }

    @Test
    public void findByNameTest(){
        String name = "House one";
        when(houseService.findHouseByName(name)).thenReturn(house);
        when(beanMappingService.mapTo(house,HouseDto.class)).thenReturn(houseDto);

        HouseDto h = houseFacade.findHouseByName(name);

        Assert.assertEquals(h,houseDto);
        verify(houseService).findHouseByName(name);

    }

    @Test
    public void createTest(){
        when(beanMappingService.mapTo(houseCreateDto, House.class)).thenReturn(house);
        houseFacade.createHouse(houseCreateDto);
        verify(houseService).createHouse(house);
    }

    @Test
    public void deleteTest(){
        when(beanMappingService.mapTo(houseDto, House.class)).thenReturn(house);
        houseFacade.deleteHouse(houseDto);
        verify(houseService).deleteHouse(house);
    }

    @Test
    public void findAllTest(){
        when(houseService.findAll()).thenReturn(houses);
        when(beanMappingService.mapTo(houses,HouseDto.class)).thenReturn(houseDtos);

        List<HouseDto>h=houseFacade.findAll();

        Assert.assertEquals(h.size(),2);
        Assert.assertEquals(h,houseDtos);
        verify(houseService).findAll();
    }

    @Test
    public void updateTest(){
        when(beanMappingService.mapTo(houseDto, House.class)).thenReturn(house);
        houseFacade.updateHouse(houseDto);
        verify(houseService).updateHouse(house);
    }

    @Test
    public void getSortedHousesAfterDateTest(){
        when(houseService.getSortedHousesAfterDate(LocalDate.now())).thenReturn(houses);
        when(beanMappingService.mapTo(houses,HouseDto.class)).thenReturn(houseDtos);
        List<HouseDto>h=houseFacade.getSortedHousesAfterDate(LocalDate.now());

        Assert.assertEquals(h.size(),2);
        Assert.assertEquals(h.get(1),houseDto2);
        verify(houseService).getSortedHousesAfterDate(LocalDate.now());
    }

    @Test
    public void findByAddressTest(){
        String address = "Address two";
        when(houseService.findByAddress(address)).thenReturn(house);
        when(beanMappingService.mapTo(house,HouseDto.class)).thenReturn(houseDto2);

        HouseDto h = houseFacade.findByAddress(address);

        Assert.assertEquals(h,houseDto2);
        verify(houseService).findByAddress(address);
    }

    @Test
    public void isHauntedByBogeymanTest(){
        when(houseService.isHauntedByBogeyman(house)).thenReturn(true);
        when(beanMappingService.mapTo(houseDto,House.class)).thenReturn(house);

        Assert.assertTrue(houseFacade.isHauntedByBogeyman(houseDto));
        verify(houseService).isHauntedByBogeyman(house);
    }

    @Test
    public void getCommentsTest(){
        when(houseService.getComments(house)).thenReturn(comments);
        when(beanMappingService.mapTo(comments,CommentDto.class)).thenReturn(commentDtos);
        when(beanMappingService.mapTo(houseDto,House.class)).thenReturn(house);

        List<CommentDto>c=houseFacade.getComments(houseDto);
        Assert.assertEquals(c.size(),1);
        Assert.assertEquals(c.get(0),commentDto);
        verify(houseService).getComments(house);
    }

    @Test
    public void getBogeymenTest(){
        when(houseService.getBogeymen(house)).thenReturn(bogeymen);
        when(beanMappingService.mapTo(bogeymen,BogeymanDto.class)).thenReturn(bogeymanDtos);
        when(beanMappingService.mapTo(houseDto,House.class)).thenReturn(house);

        List<BogeymanDto>b=houseFacade.getBogeymen(houseDto);
        Assert.assertEquals(b.size(),1);
        Assert.assertEquals(b.get(0),bogeymanDto);
        verify(houseService).getBogeymen(house);
    }

    @Test
    public void commentHouseTest(){
        when(beanMappingService.mapTo(houseDto,House.class)).thenReturn(house);
        when(beanMappingService.mapTo(commentDto,Comment.class)).thenReturn(comment);
        houseFacade.commentHouse(houseDto,commentDto);
        verify(houseService).commentHouse(house,comment);
    }
}
