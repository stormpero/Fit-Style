package ru.project.fitstyle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.fitstyle.model.dto.user.CoachDto;
import ru.project.fitstyle.model.dto.user.FitUserDto;
import ru.project.fitstyle.model.dto.user.RoleDto;
import ru.project.fitstyle.model.dto.user.SubscriptionDto;
import ru.project.fitstyle.model.entity.subscription.Subscription;
import ru.project.fitstyle.model.entity.user.FitUser;
import ru.project.fitstyle.model.entity.user.Role;
import ru.project.fitstyle.model.repository.RefreshTokenRepository;
import ru.project.fitstyle.model.repository.FitUserRepository;
import ru.project.fitstyle.service.UserService;
import ru.project.fitstyle.service.exception.user.BalanceLessThanZeroException;
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
    public void saveUser(FitUser fitUser, List<Role> roles, Subscription subscription) {
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
    public void logoutUserByEmail(String email) {
        refreshTokenRepository
                .deleteByFitUser(fitUserRepository
                        .findByEmail(email)
                        .orElseThrow(() -> new UserNotFoundException("User with that email cannot be found!")));
    }

    @Override
    public FitUserDto getFitUserInfoByEmail(String email) {
        return fitUserRepository.findFitUserInfoWithEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with that email cannot be found!"));
    }

    @Override
    public FitUserDto getFitUserInfoById(Long id) {
        return fitUserRepository.findFitUserInfoWithId(id)
                .orElseThrow(() -> new UserNotFoundException("User with that id cannot be found!"));
    }

    @Override
    public SubscriptionDto getSubscriptionResponseInfoByEmail(String email) {
        return fitUserRepository.findSubscriptionResponseInfoWithEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with that email cannot be found!"));
    }

    @Override
    public SubscriptionDto getSubscriptionResponseInfoById(Long id) {
        return fitUserRepository.findSubscriptionResponseInfoWithId(id)
                .orElseThrow(() -> new UserNotFoundException("User with that id cannot be found!"));
    }

    @Override
    public List<RoleDto> getUserRolesByEmail(String email) {
        return fitUserRepository.findRolesWithEmail(email)
                .filter(list -> list.size() != 0)
                .orElseThrow(() -> new  UserNotFoundException("User with that email cannot be found!"));
    }

    @Override
    public List<RoleDto> getUserRolesById(Long id) {
        return fitUserRepository.findRolesWithId(id)
                .filter(list -> list.size() != 0)
                .orElseThrow(() -> new  UserNotFoundException("User with that id cannot be found!"));
    }

    @Override
    public List<CoachDto> getCoaches() {
        return fitUserRepository.findAllCoaches()
                .filter(list -> list.size() != 0)
                .orElseThrow(() -> new UserNotFoundException("There are no coaches!"));
    }
}
