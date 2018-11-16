package dao;

import config.PersistenceSampleApplicationContext;
import entity.Ability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Ondrej Krcma 451363
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class AbilityDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    AbilityDao abilityDao;

    @PersistenceContext
    private EntityManager em;

    private Ability invisibility;
    private Ability chill;
    private Ability noDescriptionAbility;
    private Ability noCooldownAbility;
    private Ability negativeCooldownAbility;

    @BeforeMethod
    public void createAbilities() {
        invisibility = new Ability();
        invisibility.setName("Invisibility");
        invisibility.setDescription("The bogeyman becomes invisible for a short duration.");
        invisibility.setCooldown(3600);

        chill = new Ability();
        chill.setName("Chill");
        chill.setDescription("The bogeyman spreads aura of chilling cold.");
        chill.setCooldown(0);

        noDescriptionAbility = new Ability();
        noDescriptionAbility.setName("NoDes");
        noDescriptionAbility.setCooldown(5);

        noCooldownAbility = new Ability();
        noCooldownAbility.setName("NoCool");
        noCooldownAbility.setDescription("This ability doesn't have set cooldown.");

        negativeCooldownAbility = new Ability();
        negativeCooldownAbility.setName("Negative");
        negativeCooldownAbility.setDescription("This ability has negative cooldown");
        negativeCooldownAbility.setCooldown(-5);


        abilityDao.createAbility(invisibility);
        abilityDao.createAbility(chill);
        abilityDao.createAbility(noDescriptionAbility);
        abilityDao.createAbility(noCooldownAbility);
        abilityDao.createAbility(negativeCooldownAbility);
    }

    @Test
    public void testCreateAbility() {
        List<Ability> foundAbilities = em
                .createQuery("select a from Ability a", Ability.class)
                .getResultList();

        Assert.assertEquals(foundAbilities.size(), 5);
        Assert.assertEquals(foundAbilities.get(0).getName(), invisibility.getName());
        Assert.assertEquals(foundAbilities.get(2).getId(), noDescriptionAbility.getId());
        Assert.assertEquals(foundAbilities.get(3).getDescription(), noCooldownAbility.getDescription());
        Assert.assertEquals(foundAbilities.get(4).getCooldown(), negativeCooldownAbility.getCooldown());
    }

    @Test
    public void testCreateNullAbility() {
        Assert.assertThrows(
                IllegalArgumentException.class,
                () -> abilityDao.createAbility(null));
    }

    @Test
    public void testFindAbilityById() {
        Ability foundAbility = abilityDao.findAbilityById(chill.getId());

        Assert.assertEquals(foundAbility.getName(), chill.getName());
    }

    @Test
    public void testFindAbilityByNullId() {
        Assert.assertThrows(
                IllegalArgumentException.class,
                () -> abilityDao.findAbilityById(null)
        );
    }

    @Test
    public void testFindAbilityByNegativeId() {
        Assert.assertThrows(
                IllegalArgumentException.class,
                () -> abilityDao.findAbilityById((long) -1)
        );
    }

    @Test
    public void testNullResultFindAbilityById() {
        long id = invisibility.getId() + chill.getId() + noDescriptionAbility.getId() + noCooldownAbility.getId() + negativeCooldownAbility.getId() + 1;
        Ability foundAbility = abilityDao.findAbilityById(id);

        Assert.assertNull(foundAbility);
    }

    @Test
    public void testFindAbilityByName() {
        Ability foundAbility = abilityDao.findAbilityByName(noDescriptionAbility.getName());

        Assert.assertEquals(foundAbility.getId(), noDescriptionAbility.getId());
    }

    @Test
    public void testFindAbilityByNullName() {
        Assert.assertThrows(
                IllegalArgumentException.class,
                () -> abilityDao.findAbilityByName(null)
        );
    }

    @Test
    public void testNullResultFindAbilityByName() {
        Ability foundAbility = abilityDao.findAbilityByName("aaaaaa");

        Assert.assertNull(foundAbility);
    }

    @Test
    public void testFindAll() {
        List<Ability> foundAbilities = abilityDao.findAll();

        Assert.assertEquals(foundAbilities.size(), 5);
    }

    @Test
    public void testRemove() {
        abilityDao.remove(chill);

        List<Ability> foundAbilities = em
                .createQuery("select a from Ability a", Ability.class)
                .getResultList();

        Assert.assertEquals(foundAbilities.size(), 4);
        Assert.assertEquals(foundAbilities.get(1).getName(), noDescriptionAbility.getName());
    }

    @Test
    public void testRemoveNull() {
        Assert.assertThrows(
                IllegalArgumentException.class,
                () -> abilityDao.remove(null)
        );
    }
}
