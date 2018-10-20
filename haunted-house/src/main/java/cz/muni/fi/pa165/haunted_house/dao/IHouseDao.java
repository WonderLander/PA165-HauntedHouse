package cz.muni.fi.pa165.haunted_house.dao;

import cz.muni.fi.pa165.haunted_house.entity.House;

/**
 * Interface House Data Access Object
 * @author Lukáš Sadlek
 */
public interface IHouseDao {
    /**
     * Find House which has this id.
     * In a case when such house is found, the instance of class House is returned.
     * Otherwise the return value is null.
     * @param id: id of a house
     * @return instance of class House or null
     */
    House findHouseById(Long id);

    /**
     * Find house which has specified name.
     * In a case when such a house is found, the instance of class House is returned.
     * Otherwise the return value is null.
     * @param name: name of a house
     * @return instance of class House or null
     */
    House findHouseByName(String name);

    /**
     * Creates in the DB row for house which is passed as a parameter.
     * In a case when house with this name already exists, IllegalArgumentException is thrown.
     * In a case when house has a property with null value, IllegalArgumentException is thrown.
     * Otherwise the house is created.
     * @param house: instance of class House to be created
     * @throws IllegalArgumentException
     */
    void createHouse(House house) throws IllegalArgumentException;

    /**
     * Delete specified house passed as a parameter.
     * If this house does not exist, IllegalArgumentException is thrown.
     * @param house: instance of class House to be deleted
     * @throws IllegalArgumentException
     */
    void deleteHouse(House house) throws IllegalArgumentException;
}
