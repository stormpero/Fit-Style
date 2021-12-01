package ru.project.fitstyle.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project.fitstyle.model.dto.training.GroupTraining;

import java.util.List;
import java.util.Set;

@Repository
public interface GroupTrainingRepository extends JpaRepository<GroupTraining, Long> {
    List<GroupTraining> findByCoachId(Long id);
}
