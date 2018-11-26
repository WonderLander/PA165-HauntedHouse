package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dao.BogeymanDao;
import cz.muni.fi.pa165.dto.BogeymanCreateDto;
import cz.muni.fi.pa165.dto.BogeymanDto;
import cz.muni.fi.pa165.dto.HouseDto;
import cz.muni.fi.pa165.entity.Bogeyman;
import cz.muni.fi.pa165.entity.BogeymanType;
import cz.muni.fi.pa165.entity.House;
import cz.muni.fi.pa165.facade.BogeymanFacade;
import cz.muni.fi.pa165.factory.EntityFactory;
import cz.muni.fi.pa165.service.services.BeanMappingService;
import cz.muni.fi.pa165.service.services.BogeymanService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Martin Wenzl
 */

public class BogeymanFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private BogeymanService bogeymanService;

    @Mock
    private BeanMappingService bmp;

    private BogeymanFacade bogeymanFacade;

    private Bogeyman bogeyman;
    private BogeymanCreateDto bogeymanCreateDto;
    private House house;
    private HouseDto houseDto;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
        bogeymanFacade = new BogeymanFacadeImpl();
    }

    @BeforeMethod
    private void createBogeyman() {
        house = new House();
        houseDto = new HouseDto();
        bogeyman = new Bogeyman();
        bogeymanCreateDto = new BogeymanCreateDto();
        String address = "Fakestreet 123";
        LocalDate date = LocalDate.now();
        String history = "Once upon a time someone came with this brief history";
        String name = "Soul Sanctum";

        house.setAddress(address);
        house.setDate(date);
        house.setHistory(history);
        house.setName(name);

        houseDto.setAddress(address);
        houseDto.setDate(date);
        houseDto.setHistory(history);
        houseDto.setName(name);

        bogeyman.setHouse(house);
        bogeymanCreateDto.setHouse(houseDto);

        String bname = "qweasdsdfdhf";
        bogeyman.setName(bname);
        bogeymanCreateDto.setName(bname);

        bogeyman.setType(BogeymanType.BANSHEE);
        bogeymanCreateDto.setType(cz.muni.fi.pa165.enums.BogeymanType.BANSHEE);
    }

    @Test
    public void testCreate() {
        when(bmp.mapTo(bogeymanCreateDto, Bogeyman.class)).thenReturn(bogeyman);
        bogeymanFacade.create(bogeymanCreateDto);
        verify(bogeymanService).create(bogeyman);
    }

}
