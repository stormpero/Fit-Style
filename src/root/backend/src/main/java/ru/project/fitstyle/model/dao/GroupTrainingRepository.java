package ru.project.fitstyle.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project.fitstyle.model.entity.training.GroupTraining;

import java.util.List;

@Repository
public interface GroupTrainingRepository extends JpaRepository<GroupTraining, Long> {
    List<GroupTraining> findByCoachId(Long id);
}
