package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.User;

import java.util.List;

/**
 * @author Ondrej Krcma 451363
 */
public interface UserDao {

    void create(User user);

    User findById(Long id);

    User findByEmail(String email);

    List<User> findAll();

}
