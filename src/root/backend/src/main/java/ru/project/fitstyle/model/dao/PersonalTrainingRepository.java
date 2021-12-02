package ru.project.fitstyle.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project.fitstyle.model.entity.training.PersonalTraining;

import java.util.List;

@Repository
public interface PersonalTrainingRepository extends JpaRepository<PersonalTraining, Long> {
    List<PersonalTraining> findByCoachId(Long id);
}
