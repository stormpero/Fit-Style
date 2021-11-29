package ru.project.fitstyle.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project.fitstyle.model.dto.training.Training;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
}
