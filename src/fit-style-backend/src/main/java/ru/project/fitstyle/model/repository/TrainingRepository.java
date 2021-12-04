package ru.project.fitstyle.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ru.project.fitstyle.model.dto.training.TrainingNameDto;
import ru.project.fitstyle.model.entity.training.Training;

import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
    @Query(value = "select new ru.project.fitstyle.model.dto.training.TrainingNameDto(v.id, v.name) " +
            "from Training v")
    List<TrainingNameDto> findAllTrainingNameInfo();
}
