package cz.muni.fi.pa165.service.services;

import cz.muni.fi.pa165.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ondrej Krcma 451363
 */
@Service
public interface UserService {

    void registerUser(User user, String plaintextPassword);

    List<User> getAllUsers();

    boolean authenticate(User user, String password);

    boolean isAdmin(User user);

    User findUserById(Long id);

    User findUserByEmail(String email);

}
