package ru.project.fitstyle.model.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.project.fitstyle.controller.response.permission.RoleInfo;
import ru.project.fitstyle.model.entity.user.FitUser;
import ru.project.fitstyle.model.entity.user.Role;


@Repository
public interface FitUserRepository extends JpaRepository<FitUser, Long> {

    Optional<FitUser> findByEmail(String email);

    Optional<List<FitUser>> findByRoles(Role role);

    @Query(value = "select new ru.project.fitstyle.controller.response.permission.RoleInfo(w.id, w.name) " +
            "from FitUser v join v.roles w on v.email=:email")
    Optional<List<RoleInfo>> findRolesByEmail(@Param("email") String email);

    @Query(value = "select new ru.project.fitstyle.controller.response.permission.RoleInfo(w.id, w.name) " +
            "from FitUser v join v.roles w on v.id=:id")
    Optional<List<RoleInfo>> findRolesById(@Param("id") Long id);

    Boolean existsByEmail(String email);
}
