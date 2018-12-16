package cz.muni.fi.pa165.service.services;

import cz.muni.fi.pa165.entity.Ability;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @autor Martin Wenzl
 */
@Service
public interface AbilityService {
    /**
     * Creates ability
     * @return  id of the created ability
     * @param ability ability to be created
     */
    void createAbility(Ability ability);

    /**
     * Deletes ability
     * @param ability ability to be deleted
     */
    void deleteAbility(Ability ability);

    /**
     * Updates ability
     * @param ability ability to be updated
     */
    void updateAbility(Ability ability);

    /**
     * Finds all abilities
     * @return list of all abilities
     */
    List<Ability> getAllAbilities();

    /**
     * Finds ability by given id
     * @param id id of the ability to be found
     * @return ability with given id
     */
    Ability getAbilityById(Long id);

    /**
     * Finds ability by name
     * @param name
     * @return ability with given name
     */
    Ability getAbilityByName(String name);

}
