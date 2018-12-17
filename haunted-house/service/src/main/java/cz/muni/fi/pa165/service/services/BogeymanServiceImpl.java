package cz.muni.fi.pa165.service.services;

import cz.muni.fi.pa165.dao.BogeymanDao;
import cz.muni.fi.pa165.entity.Ability;
import cz.muni.fi.pa165.entity.Bogeyman;
import cz.muni.fi.pa165.entity.BogeymanType;
import cz.muni.fi.pa165.entity.House;
import cz.muni.fi.pa165.service.exception.BogeymanDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Ondrej Krcma 451363
 */
@Service
@Transactional
public class BogeymanServiceImpl implements  BogeymanService {

    private final
    BogeymanDao bogeymanDao;

    @Inject
    public BogeymanServiceImpl(BogeymanDao bogeymanDao) {
        try {
            this.bogeymanDao = bogeymanDao;
        } catch (Exception ex) {
            throw new BogeymanDataAccessException(ex);
        }
    }

    @Override
    public void create(Bogeyman bogeyman) {
        try {
            bogeymanDao.create(bogeyman);
        } catch (Exception ex) {
            throw new BogeymanDataAccessException(ex);
        }
    }

    @Override
    public Bogeyman findById(Long id) {
        try {
            return bogeymanDao.findById(id);
        } catch (Exception ex) {
            throw new BogeymanDataAccessException(ex);
        }
    }

    @Override
    public Bogeyman findByName(String name) {
        try {
            return bogeymanDao.findByName(name);
        } catch (Exception ex) {
            throw new BogeymanDataAccessException(ex);
        }
    }

    @Override
    public List<Bogeyman> findByHouse(House house) {
        try {
            return bogeymanDao.findByHouse(house);
        } catch (Exception ex) {
            throw new BogeymanDataAccessException(ex);
        }
    }

    @Override
    public List<Bogeyman> findByAbility(Ability ability) {
        try {
            return bogeymanDao.findByAbility(ability);
        } catch (Exception ex) {
            throw new BogeymanDataAccessException(ex);
        }
    }

    @Override
    public List<Bogeyman> findByType(BogeymanType type) {
        try {
            return bogeymanDao.findByType(type);
        } catch (Exception ex) {
            throw new BogeymanDataAccessException(ex);
        }
    }

    @Override
    public List<Bogeyman> findAll() {
        try {
           return bogeymanDao.findAll();
        } catch (Exception ex) {
           throw new BogeymanDataAccessException(ex);
        }
    }

    @Override
    public void delete(Bogeyman bogeyman) {
        try {
            bogeymanDao.delete(bogeyman);
        } catch (Exception ex) {
            throw new BogeymanDataAccessException(ex);
        }
    }

    @Override
    public void relocate(Bogeyman bogeyman, House newHouse) {
        try {
            bogeyman.setHouse(newHouse);
            bogeymanDao.update(bogeyman);
        } catch (Exception ex) {
            throw new BogeymanDataAccessException(ex);
        }
    }

    @Override
    public void update(Bogeyman bogeyman) {
        try {
            bogeymanDao.update(bogeyman);
        } catch (Exception ex) {
            throw new BogeymanDataAccessException(ex);
        }
    }

    @Override
    public void swapHouses(Bogeyman bogeyman1, Bogeyman bogeyman2) {
        try {
            House house1 = bogeyman1.getHouse();
            bogeyman1.setHouse(bogeyman2.getHouse());
            bogeyman2.setHouse(house1);

            bogeymanDao.update(bogeyman1);
            bogeymanDao.update(bogeyman2);
        } catch (Exception ex) {
            throw new BogeymanDataAccessException(ex);
        }
    }
}
