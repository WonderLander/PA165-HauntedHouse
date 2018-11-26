package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.AbilityCreateDto;
import cz.muni.fi.pa165.dto.AbilityDto;
import cz.muni.fi.pa165.entity.Ability;
import cz.muni.fi.pa165.facade.AbilityFacade;
import cz.muni.fi.pa165.service.services.AbilityService;
import cz.muni.fi.pa165.service.services.BeanMappingService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Ondrej Krcma 451363
 */

public class AbilityFacadeTest {

    private AbilityFacade abilityFacade;
    @Mock
    private BeanMappingService mockBeanMappingService;
    @Mock
    private AbilityService mockAbilityService;

    @Mock
    private AbilityCreateDto mockAbilityCreateDto;
    @Mock
    private AbilityDto mockAbility1Dto;
    private AbilityDto mockAbility2Dto;
    @Mock
    private Ability mockAbility1;
    private Ability mockAbility2;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        abilityFacade = new AbilityFacadeImpl(mockAbilityService, mockBeanMappingService);
    }

    @Test
    public void testCreateAbility() {
        when(mockBeanMappingService.mapTo(mockAbilityCreateDto, Ability.class)).thenReturn(mockAbility1);
        abilityFacade.createAbility(mockAbilityCreateDto);
        verify(mockAbilityService).createAbility(mockAbility1);
    }

    @Test
    public void testDeleteAbility() {
        when(mockBeanMappingService.mapTo(mockAbility1Dto, Ability.class)).thenReturn(mockAbility1);
        abilityFacade.deleteAbility(mockAbility1Dto);
        verify(mockAbilityService).deleteAbility(mockAbility1);
    }

    @Test
    public void testUpdateAbility() {
        when(mockBeanMappingService.mapTo(mockAbility1Dto, Ability.class)).thenReturn(mockAbility1);
        abilityFacade.updateAbility(mockAbility1Dto);
        verify(mockAbilityService).updateAbility(mockAbility1);
    }

    @Test
    public void testGetAllAbilities() {
        abilityFacade.getAllAbilities();
        verify(mockAbilityService).getAllAbilities();

        List<Ability> allMockAbilities = new ArrayList<>();
        allMockAbilities.add(mockAbility1);
        allMockAbilities.add(mockAbility2);
        List<AbilityDto> allMockAbilitiesDto = new ArrayList<>();
        allMockAbilitiesDto.add(mockAbility1Dto);
        allMockAbilitiesDto.add(mockAbility2Dto);
        when(mockBeanMappingService.mapTo(allMockAbilities, AbilityDto.class)).thenReturn(allMockAbilitiesDto);
        when(mockBeanMappingService.mapTo(allMockAbilitiesDto, Ability.class)).thenReturn(allMockAbilities);
        when(mockAbilityService.getAllAbilities()).thenReturn(allMockAbilities);

        Assert.assertEquals(abilityFacade.getAllAbilities(), allMockAbilitiesDto);
    }

    @Test
    public void testGetAbilityById() {
        long id = 5;
        when(mockBeanMappingService.mapTo(mockAbility1Dto, Ability.class)).thenReturn(mockAbility1);
        when(mockBeanMappingService.mapTo(mockAbility1, AbilityDto.class)).thenReturn(mockAbility1Dto);
        when(mockAbilityService.getAbilityById(id)).thenReturn(mockAbility1);
        Assert.assertEquals(abilityFacade.getAbilityById(id), mockAbility1Dto);
        Assert.assertNull(abilityFacade.getAbilityById(id + 1));
    }

    @Test
    public void testGetAbilityByName() {
        String name = "blabla";
        when(mockBeanMappingService.mapTo(mockAbility1Dto, Ability.class)).thenReturn(mockAbility1);
        when(mockBeanMappingService.mapTo(mockAbility1, AbilityDto.class)).thenReturn(mockAbility1Dto);
        when(mockAbilityService.getAbilityByName(name)).thenReturn(mockAbility1);
        Assert.assertEquals(abilityFacade.getAbilityByName(name), mockAbility1Dto);
        Assert.assertNull(abilityFacade.getAbilityByName(name + "a"));
    }
}
