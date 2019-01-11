package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Ability;
import cz.muni.fi.pa165.entity.Bogeyman;
import cz.muni.fi.pa165.entity.BogeymanType;
import cz.muni.fi.pa165.entity.House;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
                .createQuery("select b from Bogeyman b join b.abilities a where a = :ability", Bogeyman.class)
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
    @Transactional
    public void delete(Bogeyman b) {
        Bogeyman bogeyman = findByName(b.getName());
        for (Ability ability : bogeyman.getAbilities()) {
            bogeyman.removeAbility(ability);
        }
        em.remove(bogeyman);
    }

    @Override
    public void update(Bogeyman bogeyman) {
        em.merge(bogeyman);
    }

}
