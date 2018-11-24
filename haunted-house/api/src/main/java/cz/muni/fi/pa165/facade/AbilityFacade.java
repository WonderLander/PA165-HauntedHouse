package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.AbilityCreateDto;
import cz.muni.fi.pa165.dto.AbilityDto;

import java.util.List;

/**
 * @autor Martin Wenzl
 */
public interface AbilityFacade {
    /**
     * Creates ability
     * @param abilityCreateDto ability to be created
     */
    void createAbility(AbilityCreateDto abilityCreateDto);

    /**
     * Deletes ability
     * @param abilityDto ability to be deleted
     */
    void deleteAbility(AbilityDto abilityDto);

    /**
     * Updates ability
     * @param abilityDto ability to be updated
     */
    void updateAbility(AbilityDto abilityDto);

    /**
     * Finds all abilities
     * @return list of all abilities
     */
    List<AbilityDto> getAllAbilities();

    /**
     * Finds ability by given id
     * @param id id of the ability to be found
     * @return ability with given id
     */
    AbilityDto getAbilityById(Long id);

    /**
     * Finds ability by name
     * @param name
     * @return ability with given name
     */
    AbilityDto getAbilityByName(String name);




}
