package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.AbilityCreateDto;
import cz.muni.fi.pa165.dto.AbilityDto;
import cz.muni.fi.pa165.entity.Ability;
import cz.muni.fi.pa165.facade.AbilityFacade;
import cz.muni.fi.pa165.service.services.AbilityService;
import cz.muni.fi.pa165.service.services.BeanMappingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Martin Wenzl
 */

@Service
@Transactional
public class AbilityFacadeImpl implements AbilityFacade {

    private final AbilityService as;

    private final BeanMappingService bmp;

    @Inject
    public AbilityFacadeImpl(AbilityService as, BeanMappingService bmp) {
        this.as = as;
        this.bmp = bmp;
    }

    @Override
    public void createAbility(AbilityCreateDto abilityCreateDto) {
        as.createAbility(bmp.mapTo(abilityCreateDto, Ability.class));
    }

    @Override
    public void deleteAbility(AbilityDto abilityDto) {
        as.deleteAbility(bmp.mapTo(abilityDto, Ability.class));
    }

    @Override
    public void updateAbility(AbilityDto abilityDto) {
        as.updateAbility(bmp.mapTo(abilityDto, Ability.class));
    }

    @Override
    public List<AbilityDto> getAllAbilities() {
        List<Ability> abilities = as.getAllAbilities();
        if (abilities == null) {
            return null;
        } else {
            return bmp.mapTo(abilities, AbilityDto.class);
        }
    }

    @Override
    public AbilityDto getAbilityById(Long id) {
        Ability ability = as.getAbilityById(id);
        if (ability == null) {
            return null;
        } else {
            return bmp.mapTo(ability, AbilityDto.class);
        }
    }


    @Override
    public AbilityDto getAbilityByName(String name) {
        Ability ability = as.getAbilityByName(name);
        if (ability == null) {
            return null;
        } else {
            return bmp.mapTo(ability, AbilityDto.class);
        }
    }
}
