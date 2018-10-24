package cz.muni.fi.hauntedhouse.dao;


import cz.muni.fi.hauntedhouse.config.PersistenceSampleApplicationContext;
import cz.muni.fi.hauntedhouse.entity.Ability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ContextConfiguration(classes=PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class TestTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private AbilityDao adao;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void testCreateAbility(){
        Ability a1 = new Ability();
        a1.setName("Blood flood in the second floor");

        adao.createAbility(a1);

        Ability a2 = em.createQuery("select a from Ability a where " +
                "name = :name", Ability.class).setParameter("name", "Blood flood in the second floor")
                .getSingleResult();

        Assert.assertEquals(a2, a1);

    }

    @Test(expectedExceptions=IllegalArgumentException.class)
    public void nullAbilityArgumentCreate(){
        adao.createAbility(null);
    }



}
