package ru.project.fitstyle.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.project.fitstyle.model.dto.user.CoachDto;
import ru.project.fitstyle.model.dto.user.FitUserDto;
import ru.project.fitstyle.model.dto.user.RoleDto;
import ru.project.fitstyle.model.dto.user.SubscriptionDto;
import ru.project.fitstyle.model.entity.user.FitUser;


@Repository
public interface FitUserRepository extends JpaRepository<FitUser, Long> {

    Optional<FitUser> findByEmail(String email);

    @Query("select new ru.project.fitstyle.model.dto.user.CoachDto(v.id, v.name, v.surname, v.patronymic) " +
            "from FitUser v inner join v.roles w on w.name='ROLE_COACH'")
    Optional<List<CoachDto>> findAllCoaches();

    @Query("select new ru.project.fitstyle.model.dto.user.RoleDto(w.id, w.name) " +
            "from FitUser v inner join v.roles w " +
            "where v.email=:email")
    Optional<List<RoleDto>> findRolesWithEmail(@Param("email") final String email);

    @Query("select new ru.project.fitstyle.model.dto.user.RoleDto(w.id, w.name) " +
            "from FitUser v inner join v.roles w " +
            "where v.id=:id")
    Optional<List<RoleDto>> findRolesWithId(@Param("id") final Long id);


    @Query("select new ru.project.fitstyle.model.dto.user.FitUserDto(v.id, v.email, v.name, v.surname, v.patronymic, v.age, v.gender, v.birthdate, v.telephone, v.passport, v.address, v.imgURL, v.balance) " +
            "from FitUser v " +
            "where v.id=:id")
    Optional<FitUserDto> findFitUserInfoWithId(@Param("id") final Long id);

    @Query("select new ru.project.fitstyle.model.dto.user.SubscriptionDto(v.subscription.subscriptionType.name, v.subscription.endDate) " +
            "from FitUser v " +
            "where v.id=:id")
    Optional<SubscriptionDto> findSubscriptionResponseInfoWithId(@Param("id") final Long id);

    @Query("select new ru.project.fitstyle.model.dto.user.FitUserDto(v.id, v.email, v.name, v.surname, v.patronymic, v.age, v.gender, v.birthdate, v.telephone, v.passport, v.address, v.imgURL, v.balance) " +
            "from FitUser v " +
            "where v.email=:email")
    Optional<FitUserDto> findFitUserInfoWithEmail(@Param("email") final String email);

    @Query("select new ru.project.fitstyle.model.dto.user.SubscriptionDto(v.subscription.subscriptionType.name, v.subscription.endDate) " +
            "from FitUser v " +
            "where v.email=:email")
    Optional<SubscriptionDto> findSubscriptionResponseInfoWithEmail(@Param("email") final String email);

    Boolean existsByEmail(final String email);
}
