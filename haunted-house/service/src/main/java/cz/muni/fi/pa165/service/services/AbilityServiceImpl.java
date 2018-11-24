package cz.muni.fi.pa165.service.services;

import cz.muni.fi.pa165.dao.AbilityDao;
import cz.muni.fi.pa165.entity.Ability;
import cz.muni.fi.pa165.entity.Bogeyman;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * @autor Martin Wenzl
 */
@Service
public class AbilityServiceImpl implements AbilityService {

    @Inject
    AbilityDao abilityDao;

    @Override
    public void createAbility(Ability ability) throws IllegalArgumentException, DataAccessException {
        abilityDao.createAbility(ability);
    }

    @Override
    public void deleteAbility(Ability ability) throws IllegalArgumentException, DataAccessException {
        abilityDao.remove(ability);
    }

    @Override
    public void updateAbility(Ability ability) throws IllegalArgumentException, DataAccessException {
        abilityDao.update(ability);
    }

    @Override
    public List<Ability> getAllAbilities() throws DataAccessException {
        return abilityDao.findAll();
    }

    @Override
    public Ability getAbilityById(Long id) throws IllegalArgumentException, DataAccessException {
        return abilityDao.findAbilityById(id);
    }

    @Override
    public Ability getAbilityByName(String name) throws IllegalArgumentException, DataAccessException {
        return abilityDao.findAbilityByName(name);
    }
}
