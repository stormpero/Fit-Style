package ru.project.fitstyle.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project.fitstyle.model.dto.training.PersonalTraining;

import java.util.Set;

@Repository
public interface PersonalTrainingRepository extends JpaRepository<PersonalTraining, Long> {
    Set<PersonalTraining> findByCoachId(Long id);
}
