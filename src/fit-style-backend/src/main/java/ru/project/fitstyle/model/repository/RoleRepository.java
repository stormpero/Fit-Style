package ru.project.fitstyle.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.project.fitstyle.model.dto.user.RoleDto;
import ru.project.fitstyle.model.entity.user.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(final String name);

    @Query("select new ru.project.fitstyle.model.dto.user.RoleDto(v.id, v.name) " +
            "from Role v")
    List<RoleDto> findAllRoles();

    Boolean existsByName(final String name);
}
