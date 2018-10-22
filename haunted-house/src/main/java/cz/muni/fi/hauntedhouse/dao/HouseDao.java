package cz.muni.fi.hauntedhouse.dao;

import cz.muni.fi.hauntedhouse.entity.House;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class HouseDao implements IHouseDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public House findHouseById(Long id) {
        return entityManager.find(House.class, id);
    }

    @Override
    public House findHouseByName(String name) {
        try {
            return entityManager.createQuery("select h from House h where name = :name",
                    House.class).setParameter("name", name).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public void createHouse(House house) throws IllegalArgumentException{
        if (entityManager.find(House.class, house.getId()) != null) {
            throw new IllegalArgumentException("House already exists.");
        }
        if ((house.getAddress() == null) ||
                (house.getDate() == null) ||
                (house.getHistory() == null) ||
                (house.getName() == null)) {
            throw new IllegalArgumentException("House has null property.");
        }
        entityManager.persist(house);
    }

    @Override
    public void deleteHouse(House house) throws IllegalArgumentException{
        if (entityManager.find(House.class, house.getId()) == null) {
            throw new IllegalArgumentException("House to be deleted does not exist");
        }
        entityManager.remove(house);

    }
}
