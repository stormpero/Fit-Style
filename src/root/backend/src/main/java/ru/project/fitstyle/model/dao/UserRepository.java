package ru.project.fitstyle.model.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project.fitstyle.model.dto.user.FitUser;


@Repository
public interface UserRepository extends JpaRepository<FitUser, Long> {

    Optional<FitUser> findByEmail(String email);

    Boolean existsByEmail(String email);
}
