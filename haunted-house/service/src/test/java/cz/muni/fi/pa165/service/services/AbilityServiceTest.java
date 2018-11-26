package cz.muni.fi.pa165.service.services;

import cz.muni.fi.pa165.dao.AbilityDao;
import cz.muni.fi.pa165.entity.Ability;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Ondrej Krcma 451363
 */
public class AbilityServiceTest {

    private AbilityService abilityService;


    @Mock
    private AbilityDao mockAbilityDao;
    @Mock
    private Ability mockAbility;

    private Ability invisibility;
    private Ability chill;
    private List<Ability> allAbilities;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        invisibility = new Ability();
        invisibility.setId((long) 0);
        invisibility.setName("Invisibility");
        invisibility.setDescription("The bogeyman becomes invisible for a short duration.");
        invisibility.setCooldown(3600);

        chill = new Ability();
        chill.setId((long) 1);
        chill.setName("Chill");
        chill.setDescription("The bogeyman spreads aura of chilling cold.");
        chill.setCooldown(0);

        allAbilities = new ArrayList<>();
        allAbilities.add(invisibility);
        allAbilities.add(chill);

        abilityService = new AbilityServiceImpl(mockAbilityDao);
        when(mockAbilityDao.findAll()).thenReturn(allAbilities);
        when(mockAbilityDao.findAbilityById((long) 0)).thenReturn(invisibility);
        when(mockAbilityDao.findAbilityById((long) 1)).thenReturn(chill);
        when(mockAbilityDao.findAbilityByName("Invisibility")).thenReturn(invisibility);
        when(mockAbilityDao.findAbilityByName("Chill")).thenReturn(chill);
    }

    @Test
    public void testCreateAbility() {
        abilityService.createAbility(mockAbility);
        verify(mockAbilityDao).createAbility(mockAbility);
    }

    @Test
    public void testDeleteAbility() {
        abilityService.deleteAbility(mockAbility);
        verify(mockAbilityDao).remove(mockAbility);
    }

    @Test
    public void testUpdateAbility() {
        abilityService.updateAbility(mockAbility);
        verify(mockAbilityDao).update(mockAbility);
    }

    @Test
    public void testGetAllAbilities() {
        Assert.assertEquals(abilityService.getAllAbilities(), allAbilities);
    }

    @Test
    public void testGetAbilityById() {
        Assert.assertEquals(abilityService.getAbilityById((long) 0), invisibility);
        Assert.assertNull(abilityService.getAbilityById((long) 2));
    }

    @Test
    public void testGetAbilityByName() {
        Assert.assertEquals(abilityService.getAbilityByName("Chill"), chill);
        Assert.assertNull(abilityService.getAbilityByName("blabla"));
    }

}
