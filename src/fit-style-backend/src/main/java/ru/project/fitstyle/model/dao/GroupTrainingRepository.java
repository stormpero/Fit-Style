package ru.project.fitstyle.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.project.fitstyle.model.dto.training.GroupTrainingInfo;
import ru.project.fitstyle.model.entity.training.GroupTraining;

import java.util.List;

@Repository
public interface GroupTrainingRepository extends JpaRepository<GroupTraining, Long> {
    @Query("select new ru.project.fitstyle.model.dto.training.GroupTrainingInfo(v.id, v.startDate, v.endDate, v.status, w.id, w.name, w.surname, w.patronymic, v.training.name)" +
            "from GroupTraining v inner join FitUser w on (v.coachId=w.id)" +
            "where w.id = :id")
    List<GroupTrainingInfo> findAllCoachTrainingsWithCoachId(@Param("id") Long id);

    @Query("select new ru.project.fitstyle.model.dto.training.GroupTrainingInfo(v.id, v.startDate, v.endDate, v.status, w.id, w.name, w.surname, w.patronymic, v.training.name)" +
            "from GroupTraining v inner join FitUser w on (v.coachId=w.id)" +
            "where w.email = :email")
    List<GroupTrainingInfo> findAllCoachTrainingsWithCoachEmail(@Param("email") String email);

    @Query("select new ru.project.fitstyle.model.dto.training.GroupTrainingInfo(v.id, v.startDate, v.endDate, v.status, w.id, w.name, w.surname, w.patronymic, v.training.name)" +
            "from GroupTraining v inner join v.fitUsers w " +
            "where w.email=:email")
    List<GroupTrainingInfo> findAllFitUserTrainingsWithFitUserEmail(@Param("email") String email);
}
