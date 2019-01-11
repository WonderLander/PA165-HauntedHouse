package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.House;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class HouseDaoImpl implements HouseDao {
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
        if (house == null) {
            throw new IllegalArgumentException("House is null");
        }
        if ((house.getAddress() == null) ||
                (house.getDate() == null) ||
                (house.getHistory() == null) ||
                (house.getName() == null)) {
            throw new IllegalArgumentException("House has null property.");
        }
        if(house.getId() != null) {
            if (findHouseByName(house.getName()) != null) {
                throw new IllegalArgumentException("House already exists.");
            }
        }
        entityManager.persist(house);
    }

    @Override
    public void deleteHouse(House house) throws IllegalArgumentException{
        if (house == null) {
            throw new IllegalArgumentException("House is null");
        }
        if (findHouseByName(house.getName()) == null) {
            throw new IllegalArgumentException("House to be deleted does not exist.");
        }
        //entityManager.remove(house);
        entityManager.remove(findHouseByName(house.getName()));
    }

    @Override
    public List<House> findAll() {
        return entityManager.createQuery("select h from House h", House.class).getResultList();
    }

    @Override
    public void updateHouse(House house) throws IllegalArgumentException {
        if (house == null) {
            throw new IllegalArgumentException("House is null");
        }
        House h = findHouseByName(house.getName());
        if(h==null){
        //if (findHouseByName(house.getName()) == null) {
            throw new IllegalArgumentException("House to be updated does not exist.");
        }
        //House h = findHouseByName(house.getName());
        h.setName(house.getName());
        h.setDate(house.getDate());
        h.setAddress(house.getAddress());
        h.setHistory(house.getHistory());
        entityManager.merge(h);
    }
}
