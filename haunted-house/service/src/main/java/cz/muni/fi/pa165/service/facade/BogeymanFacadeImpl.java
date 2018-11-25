package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.AbilityDto;
import cz.muni.fi.pa165.dto.BogeymanCreateDto;
import cz.muni.fi.pa165.dto.BogeymanDto;
import cz.muni.fi.pa165.dto.HouseDto;
import cz.muni.fi.pa165.entity.Ability;
import cz.muni.fi.pa165.entity.Bogeyman;
import cz.muni.fi.pa165.entity.House;
import cz.muni.fi.pa165.enums.BogeymanType;
import cz.muni.fi.pa165.facade.BogeymanFacade;
import cz.muni.fi.pa165.service.services.BeanMappingService;
import cz.muni.fi.pa165.service.services.BogeymanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Ondrej Krcma 451363
 */

@Service
@Transactional
public class BogeymanFacadeImpl implements BogeymanFacade {


    private final BogeymanService bs;
    private final BeanMappingService bmp;

    @Inject
    public BogeymanFacadeImpl(BogeymanService bogeymanService, BeanMappingService beanMappingService) {
        this.bs = bogeymanService;
        this.bmp = beanMappingService;
    }

    @Override
    public void create(BogeymanCreateDto bogeyman) {
        bs.create(bmp.mapTo(bogeyman, Bogeyman.class));
    }

    @Override
    public BogeymanDto findById(Long id) {
        Bogeyman foundBogeyman = bs.findById(id);
        return foundBogeyman == null ? null : bmp.mapTo(foundBogeyman, BogeymanDto.class);
    }

    @Override
    public BogeymanDto findByName(String name) {
        Bogeyman foundBogeyman = bs.findByName(name);
        return foundBogeyman == null ? null : bmp.mapTo(foundBogeyman, BogeymanDto.class);
    }

    @Override
    public List<BogeymanDto> findByHouse(HouseDto house) {
        return bmp.mapTo(bs.findByHouse(bmp.mapTo(house, House.class)), BogeymanDto.class);
    }

    @Override
    public List<BogeymanDto> findByAbility(AbilityDto ability) {
        return bmp.mapTo(bs.findByAbility(bmp.mapTo(ability, Ability.class)), BogeymanDto.class);
    }

    @Override
    public List<BogeymanDto> findByType(BogeymanType type) {
        return bmp.mapTo(bs.findByType(bmp.mapTo(type, cz.muni.fi.pa165.entity.BogeymanType.class)), BogeymanDto.class);
    }

    @Override
    public List<BogeymanDto> findAll() {
        return bmp.mapTo(bs.findAll(), BogeymanDto.class);
    }

    @Override
    public void delete(BogeymanDto bogeyman) {
        bs.delete(bmp.mapTo(bogeyman, Bogeyman.class));
    }

    @Override
    public void relocate(BogeymanDto bogeyman, HouseDto newHouse) {
        bs.relocate(bmp.mapTo(bogeyman, Bogeyman.class), bmp.mapTo(newHouse, House.class));
    }

    @Override
    public void update(BogeymanDto bogeyman) {
        bs.update(bmp.mapTo(bogeyman, Bogeyman.class));
    }

    @Override
    public void swapHouses(BogeymanDto bogeyman1, BogeymanDto bogeyman2) {
        bs.swapHouses(bmp.mapTo(bogeyman1, Bogeyman.class), bmp.mapTo(bogeyman2, Bogeyman.class));
    }
}
