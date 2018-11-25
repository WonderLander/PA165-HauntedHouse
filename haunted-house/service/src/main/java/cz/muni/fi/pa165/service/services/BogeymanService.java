package cz.muni.fi.pa165.service.services;

import cz.muni.fi.pa165.entity.Ability;
import cz.muni.fi.pa165.entity.Bogeyman;
import cz.muni.fi.pa165.entity.BogeymanType;
import cz.muni.fi.pa165.entity.House;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ondrej Krcma 451363
 */

@Service
public interface BogeymanService {

    /**
     * Adds a bogeyman to the database.
     *
     * @param bogeyman the bogeyman to be added to the database
     */
    void create(Bogeyman bogeyman) throws DataAccessException;

    /**
     * Finds a bogeyman in the database by id.
     *
     * @param id the id of the sought bogeyman
     * @return the found bogeyman or null if there is no bogeyman with the given id
     */
    Bogeyman findById(Long id) throws DataAccessException;

    /**
     * Finds a bogeyman in the database by name.
     *
     * @param name the name of the sought bogeyman
     * @return the found bogeyman or null if there is no bogeyman with the given name
     */
    Bogeyman findByName(String name) throws DataAccessException;

    /**
     * Finds bogeymen in the database by house.
     *
     * @param house the house of the sought bogeymen
     * @return list of all bogeymen in the given house
     */
    List<Bogeyman> findByHouse(House house) throws DataAccessException;

    /**
     * Finds bogeymen in the database by ability.
     *
     * @param ability the ability of the sought bogeymen
     * @return list of all bogeymen with the given ability
     */
    List<Bogeyman> findByAbility(Ability ability) throws DataAccessException;

    /**
     * Finds bogeymen in the database by type.
     *
     * @param type the type of the sought bogeymen
     * @return list of all bogeymen of the given type
     */
    List<Bogeyman> findByType(BogeymanType type) throws DataAccessException;

    /**
     * Finds all bogeymen in the database.
     *
     * @return list of all bogeymen in the database
     */
    List<Bogeyman> findAll() throws DataAccessException;

    /**
     * Deletes a bogeyman from the database.
     *
     * @param bogeyman the bogeyman to be deleted from the database
     */
    void delete(Bogeyman bogeyman) throws DataAccessException;

    /**
     * Changes the house of the bogeyman to the given house.
     *
     * @param bogeyman the bogeyman whose house is to be changed
     * @param newHouse the new house of the bogeyman
     */
    void relocate(Bogeyman bogeyman, House newHouse) throws DataAccessException;

    /**
     * Updates the database to contain the given bogeyman.
     *
     * @param bogeyman the bogeyman to be updated (with the changed fields)
     */
    void update(Bogeyman bogeyman);

    /**
     * Swaps the houses of the two given bogeymen.
     *
     * @param bogeyman1 the first bogeyman
     * @param bogeyman2 the second bogeyman
     */
    void swapHouses(Bogeyman bogeyman1, Bogeyman bogeyman2);

}
