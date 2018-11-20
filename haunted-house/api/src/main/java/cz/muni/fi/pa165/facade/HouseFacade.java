package cz.muni.fi.pa165.facade;


import cz.muni.fi.pa165.dto.BogeymanDto;
import cz.muni.fi.pa165.dto.CommentDto;
import cz.muni.fi.pa165.dto.HouseDto;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Lukas Sadlek
 */
public interface HouseFacade {
    /**
     * Finds house with a specified id.
     * @param id id of a house to be find
     * @return found house or null
     */
    HouseDto findHouseById(Long id);

    /**
     * Find house with a specified name.
     * @param name name of a house to be find
     * @returnfound house or null
     */
    HouseDto findHouseByName(String name);

    /**
     * Creates house in the database.
     * @param house house to be created
     */
    void createHouse(HouseDto house);

    /**
     * Deletes house from the database
     * @param house house to be deleted
     */
    void deleteHouse(HouseDto house);

    /**
     * Finds all houses in the database and returns them.
     * @return list of all houses in the database
     */
    List<HouseDto> findAll();

    /**
     * Updates house.
     * @param house house to be updated with new values
     */
    void updateHouse(HouseDto house);

    /**
     * This function gets only houses where bogeymen started to haunt after the parameter date.
     * Moreover, it sort the list such that the newest houses according to the date when haunting started
     * are first in the sorted list.
     * @param date date to be used for comparison
     * @return
     */
    List<HouseDto> getSortedHousesAfterDate(LocalDate date);

    /**
     * Finds house on the specified address.
     * @param address address
     * @return
     */
    HouseDto findByAddress(String address);

    /**
     * Tests whether there is at least one beogeymen in the house
     * @return
     */
    boolean isHauntedByBogeyman(HouseDto house);

    /**
     * Get all comments associated to this house
     * @return
     */
    List<CommentDto> getComments(HouseDto house);

    /**
     * Get all bogeymen that live in the house
     * @return
     */
    List<BogeymanDto> getBogeymen(HouseDto house);
}