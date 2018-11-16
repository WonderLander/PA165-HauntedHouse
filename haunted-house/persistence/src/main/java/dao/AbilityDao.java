package dao;

import entity.Ability;

import java.util.List;

/**
 * Interface for DAO for {@link Ability} entity.
 *
 * @author Martin Wenzl
 */
public interface AbilityDao {
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
     * Retrieves all abilities from database. If there is none then returns empty list.
     *
     * @return List of all abilities
     */
    List<Ability> findAll();

    /**
     * Removes ability from database.
     *
     * @param ability abitlity to be removed
     * @throws IllegalArgumentException when ability is null
     */
    void remove(Ability ability) throws IllegalArgumentException;

}
