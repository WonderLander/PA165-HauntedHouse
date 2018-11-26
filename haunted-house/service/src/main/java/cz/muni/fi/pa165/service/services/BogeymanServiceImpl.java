package cz.muni.fi.pa165.service.services;

import cz.muni.fi.pa165.dao.BogeymanDao;
import cz.muni.fi.pa165.entity.Ability;
import cz.muni.fi.pa165.entity.Bogeyman;
import cz.muni.fi.pa165.entity.BogeymanType;
import cz.muni.fi.pa165.entity.House;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Ondrej Krcma 451363
 */
public class BogeymanServiceImpl implements  BogeymanService {

    private final
    BogeymanDao bogeymanDao;

    @Inject
    public BogeymanServiceImpl(BogeymanDao bogeymanDao) {
        this.bogeymanDao = bogeymanDao;
    }

    @Override
    public void create(Bogeyman bogeyman) {
        bogeymanDao.create(bogeyman);
    }

    @Override
    public Bogeyman findById(Long id) {
        return bogeymanDao.findById(id);
    }

    @Override
    public Bogeyman findByName(String name) {
        return bogeymanDao.findByName(name);
    }

    @Override
    public List<Bogeyman> findByHouse(House house) {
        return bogeymanDao.findByHouse(house);
    }

    @Override
    public List<Bogeyman> findByAbility(Ability ability) {
        return bogeymanDao.findByAbility(ability);
    }

    @Override
    public List<Bogeyman> findByType(BogeymanType type) {
        return bogeymanDao.findByType(type);
    }

    @Override
    public List<Bogeyman> findAll() {
        return bogeymanDao.findAll();
    }

    @Override
    public void delete(Bogeyman bogeyman) {
        bogeymanDao.delete(bogeyman);
    }

    @Override
    public void relocate(Bogeyman bogeyman, House newHouse) {
        bogeyman.setHouse(newHouse);
        bogeymanDao.update(bogeyman);
    }

    @Override
    public void update(Bogeyman bogeyman) {
        bogeymanDao.update(bogeyman);
    }

    @Override
    public void swapHouses(Bogeyman bogeyman1, Bogeyman bogeyman2) {
        House house1 = bogeyman1.getHouse();
        bogeyman1.setHouse(bogeyman2.getHouse());
        bogeyman2.setHouse(house1);

        bogeymanDao.update(bogeyman1);
        bogeymanDao.update(bogeyman2);
    }
}
