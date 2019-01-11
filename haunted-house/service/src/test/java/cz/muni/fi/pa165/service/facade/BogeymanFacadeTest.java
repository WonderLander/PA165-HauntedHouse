package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.AbilityDto;
import cz.muni.fi.pa165.dto.BogeymanCreateDto;
import cz.muni.fi.pa165.dto.BogeymanDto;
import cz.muni.fi.pa165.dto.HouseDto;
import cz.muni.fi.pa165.entity.Ability;
import cz.muni.fi.pa165.entity.Bogeyman;
import cz.muni.fi.pa165.entity.BogeymanType;
import cz.muni.fi.pa165.entity.House;
import cz.muni.fi.pa165.facade.BogeymanFacade;
import cz.muni.fi.pa165.service.config.ServiceConfig;
import cz.muni.fi.pa165.service.services.BeanMappingService;
import cz.muni.fi.pa165.service.services.BogeymanService;
import cz.muni.fi.pa165.service.services.HouseService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Martin Wenzl
 */
@ContextConfiguration(classes = ServiceConfig.class)
public class BogeymanFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private BogeymanService bogeymanService;

    @Mock
    private BeanMappingService bmp;

    @Mock
    private HouseService houseService;

    private BogeymanFacade bogeymanFacade;

    private Bogeyman bogeyman;
    private List<Bogeyman> bogeymanList;
    private BogeymanCreateDto bogeymanCreateDto;
    private BogeymanDto bogeymanDto;
    private List<BogeymanDto> bogeymanDtoList;
    private House house;
    private HouseDto houseDto;
    private Ability ability;
    private AbilityDto abilityDto;
    private cz.muni.fi.pa165.enums.BogeymanType bogeymanDtoType;
    private BogeymanType bogeymanType;
    private String bname;
    private House newHouse;
    private HouseDto newHouseDto;
    private Bogeyman bogeyman2;
    private BogeymanDto bogeymanDto2;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
        bogeymanFacade = new BogeymanFacadeImpl(bogeymanService, bmp, houseService);
    }

    @BeforeMethod
    private void createBogeyman() {
        house = new House();
        houseDto = new HouseDto();
        bogeyman = new Bogeyman();
        bogeymanCreateDto = new BogeymanCreateDto();
        bogeymanDto = new BogeymanDto();
        bogeymanList = new ArrayList<>();
        bogeymanDtoList = new ArrayList<>();
        ability = new Ability();
        abilityDto = new AbilityDto();
        bogeymanDtoType = cz.muni.fi.pa165.enums.BogeymanType.BANSHEE;
        bogeymanType = BogeymanType.BANSHEE;
        newHouse = new House();
        newHouseDto = new HouseDto();
        bogeyman2 = new Bogeyman();
        bogeymanDto2 = new BogeymanDto();

        String address = "Fakestreet 123";
        Date date = Date.valueOf(LocalDate.now());
        String history = "Once upon a time someone came with this brief history";
        String houseName = "Soul Sanctum";

        String newAddress = "Botanicka 68a";
        Date newDate = Date.valueOf(LocalDate.now());
        String newHistory = "When you enter you realize you did HUGE mistake";
        String newHouseName = "Asylum";

        newHouse.setAddress(newAddress);
        newHouse.setDate(newDate);
        newHouse.setHistory(newHistory);
        newHouse.setName(newHouseName);

        newHouseDto.setAddress(newAddress);
        newHouseDto.setDate(newDate);
        newHouseDto.setHistory(newHistory);
        newHouseDto.setName(newHouseName);

        house.setAddress(address);
        house.setDate(date);
        house.setHistory(history);
        house.setName(houseName);

        houseDto.setAddress(address);
        houseDto.setDate(date);
        houseDto.setHistory(history);
        houseDto.setName(houseName);

        String abilityName = "Desolate dive";
        ability.setName(abilityName);
        abilityDto.setName(abilityName);

        bogeyman.setHouse(house);
        bogeymanCreateDto.setHouse(houseDto);
        bogeymanDto.setHouse(houseDto);
        bogeyman2.setHouse(house);
        bogeymanDto2.setHouse(houseDto);

        bogeyman.addAbility(ability);
        bogeymanDto.addAbility(abilityDto);
        bogeymanCreateDto.addAbility(abilityDto);
        ability.addBogeyman(bogeyman);

        bname = "qweasdsdfdhf";
        String bname2 = "poikljkjh";

        bogeyman.setName(bname);
        bogeymanCreateDto.setName(bname);
        bogeymanDto.setName(bname);
        bogeyman2.setName(bname2);
        bogeymanDto2.setName(bname2);

        bogeyman.setType(bogeymanType);
        bogeymanCreateDto.setType(bogeymanDtoType);
        bogeymanDto.setType(bogeymanDtoType);
        bogeyman2.setType(bogeymanType);
        bogeymanDto2.setType(bogeymanDtoType);
        bogeymanList.add(bogeyman);
        bogeymanDtoList.add(bogeymanDto);
    }

    @Test
    public void testCreate() {
        when(bmp.mapTo(bogeymanCreateDto, Bogeyman.class)).thenReturn(bogeyman);
        bogeymanFacade.create(bogeymanCreateDto);
        verify(bogeymanService).create(bogeyman);
    }

    @Test
    public void testDelete() {
        when(bmp.mapTo(bogeymanDto, Bogeyman.class)).thenReturn(bogeyman);
        bogeymanFacade.delete(bogeymanDto);
        verify(bogeymanService).delete(bogeyman);
    }


    @Test
    public void testUpdate() {
        when(bmp.mapTo(bogeymanDto, Bogeyman.class)).thenReturn(bogeyman);
        bogeymanFacade.update(bogeymanDto);
        verify(bogeymanService).update(bogeyman);
    }

    @Test
    public void testFindAll() {
        when(bogeymanService.findAll()).thenReturn(bogeymanList);
        when(bmp.mapTo(bogeymanList, BogeymanDto.class)).thenReturn(bogeymanDtoList);

        List<BogeymanDto> bogeymanDtos = bogeymanFacade.findAll();
        verify(bogeymanService).findAll();
        Assert.assertEquals(bogeymanDtos.size(), 1);
        Assert.assertEquals(bogeymanDtos.get(0), bogeymanDto);
    }

    @Test
    public void testFindById() {
        when(bogeymanService.findById(1L)).thenReturn(bogeyman);
        when(bmp.mapTo(bogeyman, BogeymanDto.class)).thenReturn(bogeymanDto);

        BogeymanDto dto = bogeymanFacade.findById(1L);
        verify(bogeymanService).findById(1L);
        Assert.assertEquals(dto, bogeymanDto);
    }

    @Test
    public void testFindByName() {
        when(bogeymanService.findByName(bname)).thenReturn(bogeyman);
        when(bmp.mapTo(bogeyman, BogeymanDto.class)).thenReturn(bogeymanDto);

        BogeymanDto dto = bogeymanFacade.findByName(bname);
        verify(bogeymanService).findByName(bname);
        Assert.assertEquals(dto, bogeymanDto);
    }

    @Test
    public void testFindByHouse() {
        when(bogeymanService.findByHouse(house)).thenReturn(bogeymanList);
        when(bmp.mapTo(houseDto, House.class)).thenReturn(house);
        when(bmp.mapTo(bogeymanList, BogeymanDto.class)).thenReturn(bogeymanDtoList);

        List<BogeymanDto> dtos = bogeymanFacade.findByHouse(houseDto);
        verify(bogeymanService).findByHouse(house);
        Assert.assertEquals(dtos.size(), 1);
        Assert.assertEquals(dtos.get(0), bogeymanDto);
    }

    @Test
    public void testFindByAbility() {
        when(bmp.mapTo(abilityDto, Ability.class)).thenReturn(ability);
        when(bogeymanService.findByAbility(ability)).thenReturn(bogeymanList);
        when(bmp.mapTo(bogeymanList, BogeymanDto.class)).thenReturn(bogeymanDtoList);
        List<BogeymanDto> dtos = bogeymanFacade.findByAbility(abilityDto);
        verify(bogeymanService).findByAbility(ability);
        Assert.assertEquals(dtos.size(), 1);
        Assert.assertEquals(dtos.get(0), bogeymanDto);
    }

    @Test
    public void testFindByType() {
        when(bmp.mapTo(bogeymanDtoType, BogeymanType.class)).thenReturn(bogeymanType);
        when(bogeymanService.findByType(bogeymanType)).thenReturn(bogeymanList);
        when(bmp.mapTo(bogeymanList, BogeymanDto.class)).thenReturn(bogeymanDtoList);

        List<BogeymanDto> dtos = bogeymanFacade.findByType(bogeymanDtoType);
        verify(bogeymanService).findByType(bogeymanType);
        Assert.assertEquals(dtos.size(), 1);
        Assert.assertEquals(dtos.get(0), bogeymanDto);
    }

    @Test
    public void testRelocate() {
        when(bmp.mapTo(bogeymanDto, Bogeyman.class)).thenReturn(bogeyman);
        when(bmp.mapTo(newHouseDto, House.class)).thenReturn(newHouse);
        bogeymanFacade.relocate(bogeymanDto, newHouseDto);
        verify(bogeymanService).relocate(bogeyman, newHouse);
    }

    @Test
    public void swapHouses() {
        when(bmp.mapTo(bogeymanDto, Bogeyman.class)).thenReturn(bogeyman);
        when(bmp.mapTo(bogeymanDto2, Bogeyman.class)).thenReturn(bogeyman2);
        when(bmp.mapTo(newHouseDto, House.class)).thenReturn(newHouse);
        when(bmp.mapTo(houseDto, House.class)).thenReturn(house);

        bogeymanFacade.swapHouses(bogeymanDto, bogeymanDto2);
        verify(bogeymanService).swapHouses(bogeyman, bogeyman2);
    }

}
