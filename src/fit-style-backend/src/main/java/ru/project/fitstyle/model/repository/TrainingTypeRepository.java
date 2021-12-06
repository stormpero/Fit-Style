package ru.project.fitstyle.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ru.project.fitstyle.model.dto.training.TrainingTypeDto;
import ru.project.fitstyle.model.entity.training.TrainingType;

import java.util.List;

@Repository
public interface TrainingTypeRepository extends JpaRepository<TrainingType, Long> {
    @Query("select new ru.project.fitstyle.model.dto.training.TrainingTypeDto(v.id, v.name) " +
            "from TrainingType v")
    List<TrainingTypeDto> findAllTrainingNameInfo();
}
