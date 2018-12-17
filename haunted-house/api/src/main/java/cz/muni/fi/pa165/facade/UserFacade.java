package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.UserAuthenticateDto;
import cz.muni.fi.pa165.dto.UserDto;

import java.util.Collection;

/**
 * Ondrej Krcma 451363
 */
public interface UserFacade {

    UserDto findById(Long id);

    UserDto findByEmail(String email);

    void registerUser(UserDto user, String plaintextPassword);

    Collection<UserDto> getAllUsers();

    boolean authenticate(UserAuthenticateDto user);

    boolean isAdmin(UserDto user);

}
