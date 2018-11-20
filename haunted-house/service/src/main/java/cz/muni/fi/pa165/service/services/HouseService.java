package cz.muni.fi.pa165.service.services;

import cz.muni.fi.pa165.entity.Bogeyman;
import cz.muni.fi.pa165.entity.Comment;
import cz.muni.fi.pa165.entity.House;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Lukas Sadlek
 */
@Service
public interface HouseService {

    /**
     * Finds house with a specified id.
     * @param id id of a house to be find
     * @return found house or null
     * @throws DataAccessException in a case when error occurred in the persistence layer
     */
    House findHouseById(Long id) throws DataAccessException;

    /**
     * Find house with a specified name.
     * @param name name of a house to be find
     * @returnfound house or null
     * @throws DataAccessException in a case when error occurred in the persistence layer
     */
    House findHouseByName(String name) throws DataAccessException;

    /**
     * Creates house in the database.
     * @param house house to be created
     * @throws DataAccessException in a case when error occurred in the persistence layer
     * @throws IllegalArgumentException when house or any of its attributes is null
     */
    void createHouse(House house) throws DataAccessException, IllegalArgumentException;

    /**
     * Deletes house from the database
     * @param house house to be deleted
     * @throws DataAccessException in a case when error occurred in the persistence layer
     * @throws IllegalArgumentException when house or any of its attributes is null
     */
    void deleteHouse(House house) throws DataAccessException, IllegalArgumentException;

    /**
     * Finds all houses in the database and returns them.
     * @return list of all houses in the database
     * @throws DataAccessException in a case when error occurred in the persistence layer.
     */
    List<House> findAll() throws DataAccessException;

    /**
     * Updates house.
     * @param house house to be updated with new values
     * @throws DataAccessException in a case when error occurred in the persistence layer
     */
    void updateHouse(House house) throws DataAccessException;

    /**
     * This function gets only houses where bogeymen started to haunt after the parameter date.
     * Moreover, it sort the list such that the newest houses according to the date when haunting started
     * are first in the sorted list.
     * @param date date to be used for comparison
     * @return
     */
    List<House> getSortedHousesAfterDate(LocalDate date) throws DataAccessException;

    /**
     * Finds house on the specified address.
     * @param address address
     * @return
     */
    House findByAddress(String address) throws DataAccessException;

    /**
     * Tests whether there is at least one beogeymen in the house
     * @return
     */
    boolean isHauntedByBogeyman(House house) throws DataAccessException;

    /**
     * Get all comments associated to this house
     * @return
     */
    List<Comment> getComments(House house) throws DataAccessException;

    /**
     * Get all bogeymen that live in the house
     * @return
     */
    List<Bogeyman> getBogeymen(House house) throws DataAccessException;
}