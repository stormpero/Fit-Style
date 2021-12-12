package ru.project.fitstyle.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.project.fitstyle.model.entity.user.PasswordRecovery;

import java.util.Optional;

@Repository
public interface PasswordRecoveryRepository extends JpaRepository<PasswordRecovery, Long> {
    Optional<PasswordRecovery> findByCode(String code);

    @Transactional
    @Modifying
    void deleteByEmail(String email);
}
