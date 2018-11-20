package cz.muni.fi.pa165.service.services;

import cz.muni.fi.pa165.dao.HouseDao;
import cz.muni.fi.pa165.entity.Bogeyman;
import cz.muni.fi.pa165.entity.Comment;
import cz.muni.fi.pa165.entity.House;
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

    @Inject
    private HouseDao houseDao;

    @Override
    public House findHouseById(Long id) throws DataAccessException{
        return houseDao.findHouseById(id);
    }

    @Override
    public House findHouseByName(String name) throws DataAccessException{
        return houseDao.findHouseByName(name);
    }

    @Override
    public void createHouse(House house) throws DataAccessException, IllegalArgumentException {
        houseDao.createHouse(house);
    }

    @Override
    public void deleteHouse(House house) throws DataAccessException, IllegalArgumentException {
        houseDao.deleteHouse(house);
    }

    @Override
    public List<House> findAll() throws DataAccessException {
        return houseDao.findAll();
    }

    @Override
    public void updateHouse(House house) throws DataAccessException{
        houseDao.updateHouse(house);
    }

    @Override
    public List<House> getSortedHousesAfterDate(LocalDate date) throws DataAccessException {
        List<House> houses = new ArrayList<>();
        for (House house: houseDao.findAll()) {
            if (house.getDate().isAfter(date)) {
                houses.add(house);
            }
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
    public House findByAddress(String address) throws DataAccessException {
        if ((address == null ) || (address.isEmpty())) {
            throw new IllegalArgumentException("Adress cannot be null or empty.");
        }
        for (House house: houseDao.findAll()) {
            if (house.getAddress().equals(address)) {
                return house;
            }
        }
        return null;
    }

    @Override
    public boolean isHauntedByBogeyman(House house) throws DataAccessException {
        return !(house.getBogeymen().isEmpty());
    }

    @Override
    public List<Comment> getComments(House house) throws DataAccessException {
        return Collections.unmodifiableList(house.getComments());
    }

    @Override
    public List<Bogeyman> getBogeymen(House house) throws DataAccessException {
        return Collections.unmodifiableList(house.getBogeymen());
    }
}
