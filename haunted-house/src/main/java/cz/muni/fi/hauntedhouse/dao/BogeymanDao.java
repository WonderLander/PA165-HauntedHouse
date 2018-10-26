package cz.muni.fi.hauntedhouse.dao;

import cz.muni.fi.hauntedhouse.entity.Ability;
import cz.muni.fi.hauntedhouse.entity.Bogeyman;
import cz.muni.fi.hauntedhouse.entity.BogeymanType;
import cz.muni.fi.hauntedhouse.entity.House;

import java.util.List;

/**
 * Interface for Bogeyman DAO.
 *
 * @author Ondrej Krcma 451363
 */
public interface BogeymanDao {

    /**
     * Adds a bogeyman to the database.
     *
     * @param bogeyman the bogeyman to be added to the database
     */
    void create(Bogeyman bogeyman);

    /**
     * Finds a bogeyman in the database by id.
     *
     * @param id the id of the sought bogeyman
     * @return the found bogeyman or null if there is no bogeyman with the given id
     */
    Bogeyman findById(Long id);

    /**
     * Finds a bogeyman in the database by name.
     *
     * @param name the name of the sought bogeyman
     * @return the found bogeyman or null if there is no bogeyman with the given name
     */
    Bogeyman findByName(String name);

    /**
     * Finds bogeymen in the database by house.
     *
     * @param house the house of the sought bogeymen
     * @return list of all bogeymen in the given house
     */
    List<Bogeyman> findByHouse(House house);

    /**
     * Finds bogeymen in the database by ability.
     *
     * @param ability the ability of the sought bogeymen
     * @return list of all bogeymen with the given ability
     */
    List<Bogeyman> findByAbility(Ability ability);

    /**
     * Finds bogeymen in the database by type.
     *
     * @param type the type of the sought bogeymen
     * @return list of all bogeymen of the given type
     */
    List<Bogeyman> findByType(BogeymanType type);

    /**
     * Finds all bogeymen in the database.
     *
     * @return list of all bogeymen in the database
     */
    List<Bogeyman> findAll();

    /**
     * Deletes a bogeyman from the database.
     *
     * @param bogeyman the bogeyman to be deleted from the database
     */
    void delete(Bogeyman bogeyman);

}
