package ru.project.fitstyle.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ru.project.fitstyle.model.dto.training.TrainingNameInfo;
import ru.project.fitstyle.model.entity.training.Training;

import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
    @Query(value = "select new ru.project.fitstyle.model.dto.training.TrainingNameInfo(v.id, v.name) " +
            "from Training v")
    List<TrainingNameInfo> findAllTrainingNames();
}
