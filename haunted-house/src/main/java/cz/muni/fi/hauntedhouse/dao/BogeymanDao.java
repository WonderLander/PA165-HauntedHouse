package cz.muni.fi.hauntedhouse.dao;

import cz.muni.fi.hauntedhouse.entity.Bogeyman;

import javax.xml.bind.ValidationException;
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
     * Finds all bogeymen in the database.
     *
     * @return List of all bogeymen in the database.
     */
    List<Bogeyman> findAll();

    /**
     * Deletes a bogeyman from the database.
     *
     * @param bogeyman the bogeyman to be deleted from the database
     */
    void delete(Bogeyman bogeyman);

}
