package cz.muni.fi.pa165.service.services;

import cz.muni.fi.pa165.dao.HouseDao;
import cz.muni.fi.pa165.entity.Bogeyman;
import cz.muni.fi.pa165.entity.Comment;
import cz.muni.fi.pa165.entity.House;
import cz.muni.fi.pa165.service.exception.HauntedHouseException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Lukas Sadlek
 */
@Service
public class HouseServiceImpl implements HouseService {

    private final HouseDao houseDao;

    @Inject
    public HouseServiceImpl(HouseDao houseDao) {
        this.houseDao = houseDao;
    }

    @Override
    public House findHouseById(Long id) throws DataAccessException{
        try {
            return houseDao.findHouseById(id);
        } catch (Exception exc) {
            throw new HauntedHouseException("findHouseById() encountered error", exc);
        }
    }

    @Override
    public House findHouseByName(String name) throws DataAccessException{
        try {
            return houseDao.findHouseByName(name);
        } catch (Exception exc) {
            throw new HauntedHouseException("findHouseByName() encountered error", exc);
        }
    }

    @Override
    public void createHouse(House house) throws DataAccessException {
        try {
            houseDao.createHouse(house);
        } catch (Exception exc) {
            throw new HauntedHouseException("findHouseById() encountered error", exc);
        }
    }

    @Override
    public void deleteHouse(House house) throws DataAccessException {
        try {
            houseDao.deleteHouse(house);
        } catch (Exception exc) {
            throw new HauntedHouseException("deleteHouse() encountered error", exc);
        }
    }

    @Override
    public List<House> findAll() throws DataAccessException {
        try {
            return houseDao.findAll();
        } catch (Exception exc) {
            throw new HauntedHouseException("findAll() encountered error", exc);
        }
    }

    @Override
    public void updateHouse(House house) throws DataAccessException{
        try {
            houseDao.updateHouse(house);
        } catch (Exception exc) {
            throw new HauntedHouseException("updateHouse() encountered error", exc);
        }
    }

    @Override
    public List<House> getSortedHousesAfterDate(LocalDate date) throws DataAccessException {
        List<House> houses = new ArrayList<>();
        try {
            for (House house : houseDao.findAll()) {
                if (house.getDate().isAfter(date)) {
                    houses.add(house);
                }
            }
        } catch (Exception exc) {
            throw new HauntedHouseException("getSortedHousesAfterDate() encountered error", exc);
        }
        houses.sort(Collections.reverseOrder(new Comparator<House>() {
            @Override
            public int compare(House o1, House o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        }));
        return Collections.unmodifiableList(houses);
    }

    @Override
    public House findByAddress(String address) throws DataAccessException, IllegalArgumentException {
        if ((address == null ) || (address.isEmpty())) {
            throw new IllegalArgumentException("Adress cannot be null or empty.");
        }
        try {
            for (House house : houseDao.findAll()) {
                if (house.getAddress().equals(address)) {
                    return house;
                }
            }
            return null;
        } catch (Exception exc) {
            throw new HauntedHouseException("findByAddress() encountered error", exc);
        }
    }

    @Override
    public boolean isHauntedByBogeyman(House house) throws DataAccessException {
        try {
            return !(house.getBogeymen().isEmpty());
        } catch (Exception exc) {
            throw new HauntedHouseException("isHauntedByBogeyman() encountered error", exc);
        }
    }

    @Override
    public List<Comment> getComments(House house) throws DataAccessException {
        try {
            return Collections.unmodifiableList(house.getComments());
        } catch (Exception exc) {
            throw new HauntedHouseException("getComments() encountered error", exc);
        }
    }

    @Override
    public List<Bogeyman> getBogeymen(House house) throws DataAccessException {
        try {
            return Collections.unmodifiableList(house.getBogeymen());
        } catch (Exception exc) {
            throw new HauntedHouseException("getBogeymen() encountered error", exc);
        }
    }

    @Override
    public void commentHouse(House house, Comment comment) throws DataAccessException, IllegalArgumentException {
        if (house == null || comment == null) {
            throw new IllegalArgumentException("Method commentHouse was called with null argument");
        }
        try {
            House h = houseDao.findHouseByName(house.getName());
            h.addComment(comment);
        } catch (Exception exc) {
            throw new HauntedHouseException("commentHouse() encountered error", exc);
        }
    }
}
