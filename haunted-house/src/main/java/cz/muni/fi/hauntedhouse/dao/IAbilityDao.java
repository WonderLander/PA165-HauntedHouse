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
     * @throws IllegalArgumentException when argument is null or ability has null name property.
     * @throws ValidationException when there is already ability with same name in database.
     */
    void createAbility(Ability ability) throws IllegalArgumentException, ValidationException;

    /**
     * Retrieves ability with chosen id from database.
     *
     * @param id
     * @return Ability with chosen id or null if there is no such ability in database.
     * @throws IllegalArgumentException when id is null or negative number.
     */
    Ability findAbilityById(Long id) throws IllegalArgumentException;

    /**
     * Retrieves ability with chosen name from database.
     *
     * @param name
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

    // TODO: bogeymen has one or more abilities ?
    /**
     * Retrieves abilities for chosen bogeyman.
     *
     * //@param Bogeymen
     * @return List of abilities.
     * @throws IllegalArgumentException when bogeyman is null.
     * @throws ValidationException when there is no such bogeyman in database.
     */
    List<Ability> findByBogeymen(/*TODO: Parameter is boogeyman class*/) throws IllegalArgumentException, ValidationException;
    public void remove(Ability ability);

}
