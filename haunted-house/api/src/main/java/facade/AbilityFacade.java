package facade;

import dto.AbilityDto;

import java.util.List;

/**
 * @autor Martin Wenzl
 */
public interface AbilityFacade {
    Long createAbility(AbilityCreateDto);
    void deleteAbility();
    void updateAbility();
    List<AbilityDto> getAllAbilities();
    AbilityDto getAbilityById(Long id);
    List<AbilityDto> getAbilitiesByBogeyman(BogeymanDto bogeymanDto);




}
