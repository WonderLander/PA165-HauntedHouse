package cz.muni.fi.hauntedhouse.dao;

import cz.muni.fi.hauntedhouse.entity.Ability;

import javax.xml.bind.ValidationException;
import java.util.List;

/**
 * Interface for DAO for {@link cz.muni.fi.hauntedhouse.entity.Ability} entity.
 *
 * @author Martin Wenzl
 */
public interface IAbilityDao {
    /**
     * Persists Ability entity to database.
     *
     * @param ability ability entity to be persisted.
     * @throws IllegalArgumentException when argument is null.
     */
    void createAbility(Ability ability) throws IllegalArgumentException;

    /**
     * Retrieves ability with chosen id from database.
     *
     * @param id id
     * @return Ability with chosen id or null if there is no such ability in database.
     * @throws IllegalArgumentException when id is null or negative number.
     */
    Ability findAbilityById(Long id) throws IllegalArgumentException;

    /**
     * Retrieves ability with chosen name from database.
     *
     * @param name name of the ability
     * @return Ability with chosen name or null if there is no such ability in database.
     * @throws IllegalArgumentException when name is null.
     */
    Ability findAbilityByName(String name) throws IllegalArgumentException;

    /**
     * Retrieves all abilities from database
     *
     * @return List of all abilities
     */
    List<Ability> findAll();

    /**
     * Retrieves abilities for chosen bogeyman, if there is no such bogeyman in database
     * returns null.
     *
     * //@param Bogeymen
     * @return List of abilities.
     * @throws IllegalArgumentException when bogeyman is null.
     */
    List<Ability> findByBogeymen(/*TODO: Parameter is boogeyman class*/) throws IllegalArgumentException, ValidationException;

    /**
     * Removes ability from database.
     *
     * @param ability abitlity to be removed
     */
    public void remove(Ability ability);

}
