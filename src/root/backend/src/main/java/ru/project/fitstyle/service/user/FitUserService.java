package ru.project.fitstyle.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.fitstyle.exception.permission.EPermissionError;
import ru.project.fitstyle.exception.permission.PermissionException;
import ru.project.fitstyle.exception.profile.EProfileError;
import ru.project.fitstyle.exception.profile.ProfileException;
import ru.project.fitstyle.exception.role.ERoleError;
import ru.project.fitstyle.exception.role.RoleException;
import ru.project.fitstyle.exception.subscriptionType.ESubscriptionTypeError;
import ru.project.fitstyle.exception.subscriptionType.SubscriptionTypeException;
import ru.project.fitstyle.model.subscription.Subscription;
import ru.project.fitstyle.model.subscription.SubscriptionType;
import ru.project.fitstyle.model.user.ERole;
import ru.project.fitstyle.model.user.FitUser;
import ru.project.fitstyle.model.user.Role;
import ru.project.fitstyle.repository.RefreshTokenRepository;
import ru.project.fitstyle.repository.RoleRepository;
import ru.project.fitstyle.repository.SubscriptionTypeRepository;
import ru.project.fitstyle.repository.UserRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class FitUserService implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final SubscriptionTypeRepository subscriptionTypeRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public FitUserService(UserRepository userRepository, RoleRepository roleRepository,
                          SubscriptionTypeRepository subscriptionTypeRepository,
                          RefreshTokenRepository refreshTokenRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.subscriptionTypeRepository = subscriptionTypeRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public FitUser getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ProfileException(EProfileError.NOT_FOUND));
    }

    @Override
    public FitUser getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ProfileException(EProfileError.NOT_FOUND));
    }

    @Override
    public void saveFitUser(FitUser fitUser, Set<String> strRoles,
                            Long subscriptionTypeId, String contractNumber) {

        Subscription subscription = createFitUserSubscription(subscriptionTypeId, contractNumber);

        fitUser.setRoles(createFitUserRoles(strRoles));
        fitUser.setSubscription(subscription);

        userRepository.save(fitUser);
    }

    @Override
    public void logoutFitUserByEmail(String email) {
        refreshTokenRepository
                .deleteByFitUser(userRepository
                        .findByEmail(email).get());
    }

    @Override
    public Set<Role> getFitUserRolesByEmail(String email) {
        FitUser fitUser = userRepository.findByEmail(email).orElseThrow(() ->
                new PermissionException(EPermissionError.NOT_FOUND));
        return fitUser.getRoles();
    }

    @Override
    public Set<Role> createFitUserRoles(Set<String> strRoles) {
        Set<Role> roles = new HashSet<>();

        if (strRoles != null) {
            strRoles.forEach(role -> {
                switch (role) {
                    case "coach":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_COACH)
                                .orElseThrow(() -> new RoleException(ERoleError.NOT_FOUND));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RoleException(ERoleError.NOT_FOUND));
                        roles.add(modRole);

                        break;
                }
            });
        }
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RoleException(ERoleError.NOT_FOUND));
        roles.add(userRole);

        return roles;
    }

    @Override
    public Subscription createFitUserSubscription(Long subscriptionTypeId, String contractNumber) {
        SubscriptionType subscriptionType = subscriptionTypeRepository.findById(subscriptionTypeId)
                .orElseThrow(() -> new SubscriptionTypeException(ESubscriptionTypeError.NOT_FOUND));

        Subscription subscription = new Subscription();
        Date beginDate = new Date(new Date().getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginDate);
        calendar.add(Calendar.MONTH, subscriptionType.getValidityMonths());

        subscription.setBeginDate(beginDate);
        subscription.setEndDate(calendar.getTime());
        subscription.setSubscriptionType(subscriptionType);
        subscription.setContractNumber(contractNumber);

        return subscription;
    }
}
