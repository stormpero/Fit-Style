package ru.project.fitstyle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.fitstyle.controller.response.permission.RoleInfo;
import ru.project.fitstyle.model.entity.subscription.Subscription;
import ru.project.fitstyle.model.entity.user.ERole;
import ru.project.fitstyle.model.entity.user.FitUser;
import ru.project.fitstyle.model.entity.user.Role;
import ru.project.fitstyle.model.dao.RefreshTokenRepository;
import ru.project.fitstyle.model.dao.FitUserRepository;
import ru.project.fitstyle.service.UserService;
import ru.project.fitstyle.service.exception.user.BalanceLessThanZeroException;
import ru.project.fitstyle.service.exception.user.NotACoachException;
import ru.project.fitstyle.service.exception.user.UserNotFoundException;

import java.util.List;

@Service
public class FitUserService implements UserService {
    private final FitUserRepository fitUserRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public FitUserService(FitUserRepository fitUserRepository,
                          RefreshTokenRepository refreshTokenRepository) {
        this.fitUserRepository = fitUserRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public void validateEmail(String email) {
        if(fitUserRepository.existsByEmail(email)) {
            throw new UserNotFoundException("User with that email cannot be found!");
        }
    }

    @Override
    public FitUser getUserByEmail(String email) {
        return fitUserRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with that email cannot be found!"));
    }

    @Override
    public FitUser getUserById(Long id) {
        return fitUserRepository.findById(id)
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
    public void saveFitUser(FitUser fitUser, List<Role> roles, Subscription subscription) {
        fitUser.setRoles(roles);
        fitUser.setSubscription(subscription);
        fitUserRepository.save(fitUser);
    }

    @Override
    public void changeBalance(FitUser fitUser, Long summary) {
        long result = fitUser.getBalance() + summary;
        if(result >= 0) {
            fitUser.setBalance(result);
            fitUserRepository.save(fitUser);
            return;
        }
        throw new BalanceLessThanZeroException("Balance cannot be less than zero!");
    }

    @Override
    public void logoutFitUserByEmail(String email) {
        refreshTokenRepository
                .deleteByFitUser(fitUserRepository
                        .findByEmail(email)
                        .orElseThrow(() -> new UserNotFoundException("User with that email cannot be found!")));
    }

    @Override
    public List<RoleInfo> getFitUserRolesByEmail(String email) {
        return fitUserRepository.findRolesByEmail(email)
                .filter(list -> list.size() != 0)
                .orElseThrow(() -> new  UserNotFoundException("User with that email cannot be found!"));
    }

    @Override
    public List<RoleInfo> getFitUserRolesById(Long id) {
        return fitUserRepository.findRolesById(id)
                .filter(list -> list.size() != 0)
                .orElseThrow(() -> new  UserNotFoundException("User with that id cannot be found!"));
    }
}
