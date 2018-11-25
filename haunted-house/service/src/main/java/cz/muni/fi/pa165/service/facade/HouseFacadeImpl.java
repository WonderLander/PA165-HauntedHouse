package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.BogeymanDto;
import cz.muni.fi.pa165.dto.CommentDto;
import cz.muni.fi.pa165.dto.HouseCreateDto;
import cz.muni.fi.pa165.dto.HouseDto;
import cz.muni.fi.pa165.entity.Bogeyman;
import cz.muni.fi.pa165.entity.Comment;
import cz.muni.fi.pa165.entity.House;
import cz.muni.fi.pa165.facade.HouseFacade;
import cz.muni.fi.pa165.service.services.BeanMappingService;
import cz.muni.fi.pa165.service.services.HouseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Lukas Sadlek
 */
@Service
@Transactional
public class HouseFacadeImpl implements HouseFacade {
    private final HouseService houseService;

    private final BeanMappingService beanMappingService;

    @Inject
    public HouseFacadeImpl(HouseService houseService, BeanMappingService beanMappingService) {
        this.houseService = houseService;
        this.beanMappingService = beanMappingService;
    }

    @Override
    public HouseDto findHouseById(Long id) {
        return beanMappingService.mapTo(houseService.findHouseById(id), HouseDto.class);
    }

    @Override
    public HouseDto findHouseByName(String name) {
        return beanMappingService.mapTo(houseService.findHouseByName(name), HouseDto.class);
    }

    @Override
    public void createHouse(HouseCreateDto house) {
        houseService.createHouse(beanMappingService.mapTo(house, House.class));
    }

    @Override
    public void deleteHouse(HouseDto house) {
        houseService.deleteHouse(beanMappingService.mapTo(house, House.class));
    }

    @Override
    public List<HouseDto> findAll() {
        List<House> houses = houseService.findAll();
        if (houses == null) {
            return null;
        } else {
            return beanMappingService.mapTo(houses, HouseDto.class);
        }
    }

    @Override
    public void updateHouse(HouseDto house) {
        houseService.updateHouse(beanMappingService.mapTo(house, House.class));
    }

    @Override
    public List<HouseDto> getSortedHousesAfterDate(LocalDate date) {
        List<House> houses = houseService.getSortedHousesAfterDate(date);
        if (houses == null) {
            return null;
        } else {
            return beanMappingService.mapTo(houses, HouseDto.class);
        }
    }

    @Override
    public HouseDto findByAddress(String address) {
        return beanMappingService.mapTo(houseService.findByAddress(address), HouseDto.class);
    }

    @Override
    public boolean isHauntedByBogeyman(HouseDto house) {
        return houseService.isHauntedByBogeyman(beanMappingService.mapTo(house, House.class));
    }

    @Override
    public List<CommentDto> getComments(HouseDto house) {
        List<Comment> comments = houseService.getComments(beanMappingService.mapTo(house, House.class));
        if (comments == null) {
            return null;
        } else {
            return beanMappingService.mapTo(comments, CommentDto.class);
        }
    }

    @Override
    public List<BogeymanDto> getBogeymen(HouseDto house) {
        List<Bogeyman> bogeymen = houseService.getBogeymen(beanMappingService.mapTo(house, House.class));
        if (bogeymen == null) {
            return null;
        } else {
            return beanMappingService.mapTo(bogeymen, BogeymanDto.class);
        }
    }

    @Override
    public void commentHouse(HouseDto house, CommentDto comment) {
        houseService.commentHouse(
                beanMappingService.mapTo(house, House.class),
                beanMappingService.mapTo(comment, Comment.class));
    }
}
