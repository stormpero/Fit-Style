package ru.project.fitstyle.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.project.fitstyle.model.dto.training.GroupTrainingDto;
import ru.project.fitstyle.model.entity.training.GroupTraining;

import java.util.List;

@Repository
public interface GroupTrainingRepository extends JpaRepository<GroupTraining, Long> {
    @Query("select new ru.project.fitstyle.model.dto.training.GroupTrainingDto(v.id, v.startDate, v.endDate, v.status, w.id, w.name, w.surname, w.patronymic, v.trainingType.name, count(v.fitUsers)) " +
            "from GroupTraining v inner join FitUser w on (v.coachId=w.id) " +
            "where w.id = :id")
    List<GroupTrainingDto> findAllCoachTrainingsWithCoachId(@Param("id") final Long id);

    @Query("select new ru.project.fitstyle.model.dto.training.GroupTrainingDto(v.id, v.startDate, v.endDate, v.status, w.id, w.name, w.surname, w.patronymic, v.trainingType.name, count(v.fitUsers)) " +
            "from GroupTraining v inner join FitUser w on (v.coachId=w.id) " +
            "where w.email = :email")
    List<GroupTrainingDto> findAllCoachTrainingsWithCoachEmail(@Param("email") final String email);

    @Query("select new ru.project.fitstyle.model.dto.training.GroupTrainingDto(v.id, v.startDate, v.endDate, v.status, t.id, t.name, t.surname, t.patronymic, v.trainingType.name, count(v.fitUsers)) " +
            "from GroupTraining v inner join v.fitUsers w inner join FitUser t on (v.coachId=t.id) " +
            "where w.email=:email")
    List<GroupTrainingDto> findAllFitUserTrainingsWithFitUserEmail(@Param("email") final String email);

    @Query("select v " +
            "from GroupTraining v inner join v.fitUsers w inner join FitUser t " +
            "where t.email=:email")
    List<GroupTraining> findAllOccupiedCoachTrainingsWithCoachEmail(@Param ("email") String email);
}
