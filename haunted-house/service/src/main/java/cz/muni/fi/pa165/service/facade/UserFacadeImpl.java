package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.UserAuthenticateDto;
import cz.muni.fi.pa165.dto.UserDto;
import cz.muni.fi.pa165.entity.User;
import cz.muni.fi.pa165.facade.UserFacade;
import cz.muni.fi.pa165.service.services.BeanMappingService;
import cz.muni.fi.pa165.service.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Collection;

/**
 * @author Ondrej Krcma 451363
 */
@Service
@Transactional
public class UserFacadeImpl implements UserFacade {

    private final UserService us;
    private final BeanMappingService bmp;

    @Inject
    public UserFacadeImpl(UserService userService, BeanMappingService beanMappingService) {
        this.us = userService;
        this.bmp = beanMappingService;
    }

    @Override
    public UserDto findById(Long id) {
        User user = us.findUserById(id);
        return (user == null) ? null : bmp.mapTo(user, UserDto.class);
    }

    @Override
    public UserDto findByEmail(String email) {
        User user = us.findUserByEmail(email);
        return (user == null) ? null : bmp.mapTo(user, UserDto.class);
    }

    @Override
    public void registerUser(UserDto user, String plaintextPassword) {
        User userEntity = bmp.mapTo(user, User.class);
        us.registerUser(userEntity, plaintextPassword);
        user.setId(userEntity.getId());
    }

    @Override
    public Collection<UserDto> getAllUsers() {
        return bmp.mapTo(us.getAllUsers(), UserDto.class);
    }

    @Override
    public boolean authenticate(UserAuthenticateDto user) {
        return us.authenticate(us.findUserById(user.getUserId()), user.getPassword());
    }

    @Override
    public boolean isAdmin(UserDto user) {
        return us.isAdmin(bmp.mapTo(user, User.class));
    }
}
