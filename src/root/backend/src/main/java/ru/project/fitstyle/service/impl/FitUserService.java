package ru.project.fitstyle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.fitstyle.model.dto.subscription.Subscription;
import ru.project.fitstyle.model.dto.user.ERole;
import ru.project.fitstyle.model.dto.user.FitUser;
import ru.project.fitstyle.model.dto.user.Role;
import ru.project.fitstyle.model.dao.RefreshTokenRepository;
import ru.project.fitstyle.model.dao.UserRepository;
import ru.project.fitstyle.service.UserService;
import ru.project.fitstyle.service.exception.user.NotACoachException;
import ru.project.fitstyle.service.exception.user.UserNotFoundException;

import java.util.Set;

@Service
public class FitUserService implements UserService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public FitUserService(UserRepository userRepository,
                          RefreshTokenRepository refreshTokenRepository) {
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public void validateEmail(String email) {
        if(userRepository.existsByEmail(email)) {
            throw new UserNotFoundException("User with that email cannot be found!");
        }
    }

    @Override
    public FitUser getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with that email cannot be found!"));
    }

    @Override
    public FitUser getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with that id cannot be found!"));
    }

    @Override
    public FitUser getCoachById(Long id) {
        FitUser fitUser = getUserById(id);
        for(Role role : fitUser.getRoles()) {
            if(role.getName() == ERole.ROLE_COACH) {
                return fitUser;
            }
        }
        throw new NotACoachException("Given id doesn't belong to coach");
    }

    @Override
    public void saveFitUser(FitUser fitUser, Set<Role> roles, Subscription subscription) {
        fitUser.setRoles(roles);
        fitUser.setSubscription(subscription);
        userRepository.save(fitUser);
    }

    @Override
    public void logoutFitUserByEmail(String email) {
        refreshTokenRepository
                .deleteByFitUser(userRepository
                        .findByEmail(email)
                        .orElseThrow(() -> new UserNotFoundException("User with that email cannot be found!")));
    }

    @Override
    public Set<Role> getFitUserRolesByEmail(String email) {
        FitUser fitUser = userRepository.findByEmail(email).orElseThrow(() ->
                new  UserNotFoundException("User with that email cannot be found!"));
        return fitUser.getRoles();
    }
}
