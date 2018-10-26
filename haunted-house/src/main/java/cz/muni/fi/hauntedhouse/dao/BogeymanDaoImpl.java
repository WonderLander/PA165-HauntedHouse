package cz.muni.fi.hauntedhouse.dao;

import cz.muni.fi.hauntedhouse.entity.Ability;
import cz.muni.fi.hauntedhouse.entity.Bogeyman;
import cz.muni.fi.hauntedhouse.entity.BogeymanType;
import cz.muni.fi.hauntedhouse.entity.House;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Ondrej Krcma 451363
 */
@Repository
public class BogeymanDaoImpl implements BogeymanDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Bogeyman bogeyman) {
        em.persist(bogeyman);
    }

    @Override
    public Bogeyman findById(Long id) {
        return em.find(Bogeyman.class, id);
    }

    @Override
    public Bogeyman findByName(String name) {
        try {
            return em
                    .createQuery("select b from Bogeyman b where name = :name", Bogeyman.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public List<Bogeyman> findByHouse(House house) {
        return em
                .createQuery("select b from Bogeyman b where house = :house", Bogeyman.class)
                .setParameter("house", house)
                .getResultList();
    }

    @Override
    public List<Bogeyman> findByAbility(Ability ability) {
        return em
                .createQuery("select b from Bogeyman b where ability = :ability", Bogeyman.class)
                .setParameter("ability", ability)
                .getResultList();
    }

    @Override
    public List<Bogeyman> findByType(BogeymanType type) {
        return em
                .createQuery("select b from Bogeyman b where type = :type", Bogeyman.class)
                .setParameter("type", type)
                .getResultList();
    }

    @Override
    public List<Bogeyman> findAll() {
        return em
                .createQuery("select b from Bogeyman b", Bogeyman.class)
                .getResultList();
    }

    @Override
    public void delete(Bogeyman bogeyman) {
        em.remove(bogeyman);
    }

}
