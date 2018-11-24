package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.AbilityDto;
import cz.muni.fi.pa165.dto.BogeymanCreateDto;
import cz.muni.fi.pa165.dto.BogeymanDto;
import cz.muni.fi.pa165.dto.HouseDto;
import cz.muni.fi.pa165.enums.BogeymanType;

import java.util.List;

/**
 * @author Ondrej Krcma 451363
 */
public interface BogeymanFacade {

    /**
     * Adds a bogeyman to the database.
     *
     * @param bogeyman the bogeyman to be added to the database
     */
    void create(BogeymanCreateDto bogeyman);

    /**
     * Finds a bogeyman in the database by id.
     *
     * @param id the id of the sought bogeyman
     * @return the found bogeyman or null if there is no bogeyman with the given id
     */
    BogeymanDto findById(Long id);

    /**
     * Finds a bogeyman in the database by name.
     *
     * @param name the name of the sought bogeyman
     * @return the found bogeyman or null if there is no bogeyman with the given name
     */
    BogeymanDto findByName(String name);

    /**
     * Finds bogeymen in the database by house.
     *
     * @param house the house of the sought bogeymen
     * @return list of all bogeymen in the given house
     */
    List<BogeymanDto> findByHouse(HouseDto house);

    /**
     * Finds bogeymen in the database by ability.
     *
     * @param ability the ability of the sought bogeymen
     * @return list of all bogeymen with the given ability
     */
    List<BogeymanDto> findByAbility(AbilityDto ability);

    /**
     * Finds bogeymen in the database by type.
     *
     * @param type the type of the sought bogeymen
     * @return list of all bogeymen of the given type
     */
    List<BogeymanDto> findByType(BogeymanType type);

    /**
     * Finds all bogeymen in the database.
     *
     * @return list of all bogeymen in the database
     */
    List<BogeymanDto> findAll();

    /**
     * Deletes a bogeyman from the database.
     *
     * @param bogeyman the bogeyman to be deleted from the database
     */
    void delete(BogeymanDto bogeyman);

    /**
     * Changes the house of the bogeyman to the given house.
     *
     * @param bogeyman the bogeyman whose house is to be changed
     * @param newHouse the new house of the bogeyman
     */
    void relocate(BogeymanDto bogeyman, HouseDto newHouse);

}
