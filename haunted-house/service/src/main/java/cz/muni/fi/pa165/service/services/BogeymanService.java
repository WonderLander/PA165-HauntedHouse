package cz.muni.fi.pa165.service.services;

import cz.muni.fi.pa165.entity.Ability;
import cz.muni.fi.pa165.entity.Bogeyman;
import cz.muni.fi.pa165.entity.BogeymanType;
import cz.muni.fi.pa165.entity.House;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @author Ondrej Krcma 451363
 */
public interface BogeymanService {

    /**
     * Adds a bogeyman to the database.
     *
     * @param bogeyman the bogeyman to be added to the database
     * @throws DataAccessException if DAO layer throws any exception
     */
    void create(Bogeyman bogeyman) throws DataAccessException;

    /**
     * Finds a bogeyman in the database by id.
     *
     * @param id the id of the sought bogeyman
     * @return the found bogeyman or null if there is no bogeyman with the given id
     * @throws DataAccessException if DAO layer throws any exception
     */
    Bogeyman findById(Long id) throws DataAccessException;

    /**
     * Finds a bogeyman in the database by name.
     *
     * @param name the name of the sought bogeyman
     * @return the found bogeyman or null if there is no bogeyman with the given name
     * @throws DataAccessException if DAO layer throws any exception
     */
    Bogeyman findByName(String name) throws DataAccessException;

    /**
     * Finds bogeymen in the database by house.
     *
     * @param house the house of the sought bogeymen
     * @return list of all bogeymen in the given house
     * @throws DataAccessException if DAO layer throws any exception
     */
    List<Bogeyman> findByHouse(House house) throws DataAccessException;

    /**
     * Finds bogeymen in the database by ability.
     *
     * @param ability the ability of the sought bogeymen
     * @return list of all bogeymen with the given ability
     * @throws DataAccessException if DAO layer throws any exception
     */
    List<Bogeyman> findByAbility(Ability ability) throws DataAccessException;

    /**
     * Finds bogeymen in the database by type.
     *
     * @param type the type of the sought bogeymen
     * @return list of all bogeymen of the given type
     * @throws DataAccessException if DAO layer throws any exception
     */
    List<Bogeyman> findByType(BogeymanType type) throws DataAccessException;

    /**
     * Finds all bogeymen in the database.
     *
     * @return list of all bogeymen in the database
     * @throws DataAccessException if DAO layer throws any exception
     */
    List<Bogeyman> findAll() throws DataAccessException;

    /**
     * Deletes a bogeyman from the database.
     *
     * @param bogeyman the bogeyman to be deleted from the database
     * @throws DataAccessException if DAO layer throws any exception
     */
    void delete(Bogeyman bogeyman) throws DataAccessException;

    /**
     * Changes the house of the bogeyman to the given house.
     *
     * @param bogeyman the bogeyman whose house is to be changed
     * @param newHouse the new house of the bogeyman
     * @throws DataAccessException if DAO layer throws any exception
     */
    void relocate(Bogeyman bogeyman, House newHouse) throws DataAccessException;

}
