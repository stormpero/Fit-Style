package ru.project.fitstyle.model.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.project.fitstyle.model.dto.user.CoachInfo;
import ru.project.fitstyle.model.dto.user.FitUserInfo;
import ru.project.fitstyle.model.dto.user.RoleInfo;
import ru.project.fitstyle.model.dto.user.SubscriptionInfo;
import ru.project.fitstyle.model.entity.user.FitUser;


@Repository
public interface FitUserRepository extends JpaRepository<FitUser, Long> {

    Optional<FitUser> findByEmail(String email);

    @Query(value = "select new ru.project.fitstyle.model.dto.user.CoachInfo(v.id, v.name, v.surname, v.patronymic) " +
            "from FitUser v inner join v.roles w on w.name='ROLE_COACH'")
    Optional<List<CoachInfo>> findAllCoaches();

    @Query(value = "select new ru.project.fitstyle.model.dto.user.RoleInfo(w.id, w.name) " +
            "from FitUser v inner join v.roles w " +
            "where v.email=:email")
    Optional<List<RoleInfo>> findRolesWithEmail(@Param("email") String email);

    @Query(value = "select new ru.project.fitstyle.model.dto.user.RoleInfo(w.id, w.name) " +
            "from FitUser v inner join v.roles w " +
            "where v.id=:id")
    Optional<List<RoleInfo>> findRolesWithId(@Param("id") Long id);


    @Query(value = "select new ru.project.fitstyle.model.dto.user.FitUserInfo(v.id, v.email, v.password, v.name, v.surname, v.patronymic, v.age, v.gender, v.birthdate, v.telephone, v.passport, v.address, v.imgURL, v.balance) " +
            "from FitUser v " +
            "where v.id=:id")
    Optional<FitUserInfo> findFitUserInfoWithId(@Param("id") Long id);

    @Query(value = "select new ru.project.fitstyle.model.dto.user.SubscriptionInfo(v.subscription.subscriptionType.name, v.subscription.endDate) " +
            "from FitUser v " +
            "where v.id=:id")
    Optional<SubscriptionInfo> findSubscriptionResponseInfoWithId(@Param("id") Long id);

    @Query(value = "select new ru.project.fitstyle.model.dto.user.FitUserInfo(v.id, v.email, v.password, v.name, v.surname, v.patronymic, v.age, v.gender, v.birthdate, v.telephone, v.passport, v.address, v.imgURL, v.balance) " +
            "from FitUser v " +
            "where v.email=:email")
    Optional<FitUserInfo> findFitUserInfoWithEmail(@Param("email") String email);

    @Query(value = "select new ru.project.fitstyle.model.dto.user.SubscriptionInfo(v.subscription.subscriptionType.name, v.subscription.endDate) " +
            "from FitUser v " +
            "where v.email=:email")
    Optional<SubscriptionInfo> findSubscriptionResponseInfoWithEmail(@Param("email") String email);

    Boolean existsByEmail(String email);
}
