package ru.project.fitstyle.service.user;

import ru.project.fitstyle.model.subscription.Subscription;
import ru.project.fitstyle.model.user.FitUser;
import ru.project.fitstyle.model.user.Role;

import java.util.Set;

public interface FitUserService {

    boolean existsByEmail(String email);

    FitUser getUserByEmail(String email);

    FitUser getUserById(Long id);

    void saveFitUser(FitUser fitUser,
                     Set<String> strRoles, Long subscriptionTypeId, String contractNumber);

    void logoutFitUserByEmail(String email);

    Set<Role> getFitUserRolesByEmail(String email);

    Set<Role> createFitUserRoles(Set<String> strRoles);

    Subscription createFitUserSubscription(Long subscriptionTypeId, String contractNumber);
}
