package cz.muni.fi.hauntedhouse.dao;

import cz.muni.fi.hauntedhouse.entity.Ability;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class AbilityDaoImpl implements AbilityDao {
    @PersistenceContext
    EntityManager em;

    @Override
    public void createAbility(Ability ability) throws IllegalArgumentException {
        if (ability == null) {
            throw new IllegalArgumentException("Argument is null");
        }
        em.persist(ability);
    }

    @Override
    public Ability findAbilityById(Long id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("Id argument is null");
        }
        if (id <= 0) {
            throw new IllegalArgumentException("Id is negative number");
        }
        return em.find(Ability.class, id);
    }

    @Override
    public Ability findAbilityByName(String name) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException("Argument is null");
        }
        try{
            return em.createQuery("select a from Ability a where name = :name", Ability.class)
                    .setParameter("name", name).getSingleResult();
        } catch(NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Ability> findAll() {
        return em.createQuery("select a from Ability a", Ability.class).getResultList();
    }


    @Override
    public void remove(Ability ability) {
        if (ability == null) {
            throw new IllegalArgumentException("Argument is null");
        }
        em.remove(ability);
    }
}
