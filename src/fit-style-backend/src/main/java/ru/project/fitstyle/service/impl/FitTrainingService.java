package ru.project.fitstyle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.project.fitstyle.model.dto.training.*;
import ru.project.fitstyle.model.dto.user.FitUserFullNameDto;
import ru.project.fitstyle.model.entity.training.ETrainingStatus;
import ru.project.fitstyle.model.entity.user.FitUser;
import ru.project.fitstyle.model.repository.FitUserRepository;
import ru.project.fitstyle.model.repository.GroupTrainingRepository;
import ru.project.fitstyle.model.repository.PersonalTrainingRepository;
import ru.project.fitstyle.model.repository.TrainingTypeRepository;
import ru.project.fitstyle.model.entity.training.GroupTraining;
import ru.project.fitstyle.model.entity.training.PersonalTraining;
import ru.project.fitstyle.model.entity.training.TrainingType;
import ru.project.fitstyle.service.TrainingService;
import ru.project.fitstyle.service.exception.training.ExceededMaxPeopleTrainingException;
import ru.project.fitstyle.service.exception.training.TrainingNotFoundException;
import ru.project.fitstyle.service.exception.user.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FitTrainingService implements TrainingService {
    private final TrainingTypeRepository trainingTypeRepository;
    private final GroupTrainingRepository groupTrainingRepository;
    private final PersonalTrainingRepository personalTrainingRepository;
    private final FitUserRepository fitUserRepository;

    private final int maxGroupTrainingUsers = 20;

    @Autowired
    public FitTrainingService(final TrainingTypeRepository trainingTypeRepository,
                              final GroupTrainingRepository groupTrainingRepository,
                              final PersonalTrainingRepository personalTrainingRepository,
                              FitUserRepository fitUserRepository) {
        this.trainingTypeRepository = trainingTypeRepository;
        this.groupTrainingRepository = groupTrainingRepository;
        this.personalTrainingRepository = personalTrainingRepository;
        this.fitUserRepository = fitUserRepository;
    }

    @Override
    public void saveTraining(final TrainingType trainingType) {
        trainingTypeRepository.save(trainingType);
    }

    @Override
    public void saveGroupTraining(final GroupTraining groupTraining) {
        groupTrainingRepository.save(groupTraining);
    }

    @Override
    public void savePersonalTraining(final PersonalTraining personalTraining) {
        personalTrainingRepository.save(personalTraining);
    }

    @Transactional
    @Override
    public void signForPersonalTraining(final String userEmail, final Long trainingId) {
        FitUser fitUser = fitUserRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException("User with that email cannot be found!"));
        PersonalTraining personalTraining = getPersonalTrainingById(trainingId);
        if (personalTraining.getUser() == null) {
            personalTraining.setUser(fitUser);
            fitUser.getPersonalTrainings().add(personalTraining);
            personalTraining.setStatus(ETrainingStatus.ACTIVE);
            fitUserRepository.save(fitUser);
            personalTrainingRepository.save(personalTraining);
        }
        else {
            throw new ExceededMaxPeopleTrainingException("There is already user signed for this personal training!");
        }
    }

    //TODO add max users check before signing
    @Transactional
    @Override
    public void signForGroupTraining(final String userEmail, final Long trainingId) {
        FitUser fitUser = fitUserRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException("User with that email cannot be found!"));
        GroupTraining groupTraining = getGroupTrainingById(trainingId);
        if(groupTraining.getFitUsers().size() <= maxGroupTrainingUsers) {
            fitUser.getGroupTrainings().add(groupTraining);
            groupTraining.getFitUsers().add(fitUser);
            fitUserRepository.save(fitUser);
            groupTrainingRepository.save(groupTraining);
        }
        else {
            throw new ExceededMaxPeopleTrainingException("There are already max users signed for this personal training!");
        }
    }

    @Override
    public void deleteTraining(final Long id) {
        trainingTypeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteGroupTraining(final Long id) {
        GroupTraining groupTraining = groupTrainingRepository.findById(id).orElse(null);
        if(groupTraining == null) {
            return;
        }
        for (FitUser fitUser : groupTraining.getFitUsers()) {
            fitUser.getGroupTrainings().remove(groupTraining);
        }
        groupTrainingRepository.delete(groupTraining);
    }

    @Override
    public void deletePersonalTraining(final Long id) {
        personalTrainingRepository.deleteById(id);
    }

    @Override
    public GroupTraining getGroupTrainingById(final Long id) {
        return groupTrainingRepository.findById(id)
                .orElseThrow(() -> new TrainingNotFoundException("Group training with that id cannot be found!"));
    }

    @Override
    public PersonalTraining getPersonalTrainingById(final Long id) {
        return personalTrainingRepository.findById(id)
                .orElseThrow(() -> new TrainingNotFoundException("Personal training with that id cannot be found!"));
    }

    @Override
    public TrainingType getTrainingById(final Long id) {
        return trainingTypeRepository.findById(id)
                .orElseThrow(() -> new TrainingNotFoundException("Training with that email cannot be found!"));
    }

    @Override
    public List<GroupTrainingDto> getCoachGroupTrainingsByCoachId(final Long id) {
        return groupTrainingRepository.findAllCoachTrainingsWithCoachId(id);
    }

    @Override
    public List<PersonalTrainingDto> getCoachPersonalTrainingsByCoachId(final Long id) {
        return personalTrainingRepository.findAllCoachTrainingsWithCoachId(id);
    }

    @Override
    public List<GroupTrainingDto> getCoachGroupTrainingsByCoachEmail(final String email) {
        return groupTrainingRepository.findAllCoachTrainingsWithCoachEmail(email);
    }

    @Override
    public List<PersonalTrainingDto> getCoachPersonalTrainingsByCoachEmail(final String email) {
        return personalTrainingRepository.findAllCoachTrainingsWithCoachEmail(email);
    }

    @Override
    public List<GroupTrainingDto> getFitUserGroupTrainingsByFitUserEmail(final String email) {
        return groupTrainingRepository.findAllFitUserTrainingsWithFitUserEmail(email);
    }

    @Override
    public List<PersonalTrainingDto> getFitUserPersonalTrainingsByFitUserEmail(final String email) {
        return personalTrainingRepository.findAllFitUserTrainingsWithFitUserEmail(email);
    }

    @Transactional
    @Override
    public List<GroupTrainingWithUsersDto> getAllOccupiedCoachGroupTrainings(String email) {
        List<GroupTrainingWithUsersDto> trainings = new ArrayList<>();
        List<GroupTraining> groupTrainings = groupTrainingRepository.findAllOccupiedCoachTrainingsWithCoachEmail(email);

        for(GroupTraining groupTraining : groupTrainings) {
            List<FitUserFullNameDto> users = new ArrayList<>();
            for(FitUser fitUser : groupTraining.getFitUsers()) {
                users.add(new FitUserFullNameDto(fitUser.getId(), fitUser.getName(), fitUser.getSurname(), fitUser.getPatronymic()));
            }
            trainings.add(new GroupTrainingWithUsersDto(groupTraining.getId(), groupTraining.getStartDate(),
                    groupTraining.getEndDate(), groupTraining.getStatus(), groupTraining.getTraining().getName(), users));
        }

        return trainings;
    }

    @Transactional
    @Override
    public List<PersonalTrainingWithUsersDto> getAllOccupiedCoachPersonalTrainings(String email) {
        List<PersonalTrainingWithUsersDto> trainings = new ArrayList<>();
        List<PersonalTraining> personalTrainings = personalTrainingRepository.findAllOccupiedCoachTrainingsWithCoachEmail(email);

        for(PersonalTraining personalTraining : personalTrainings) {
            trainings.add(new PersonalTrainingWithUsersDto(personalTraining.getId(), personalTraining.getStartDate(),
                    personalTraining.getEndDate(), personalTraining.getStatus(),
                    new FitUserFullNameDto(personalTraining.getUser().getId(), personalTraining.getUser().getName(),
                            personalTraining.getUser().getSurname(), personalTraining.getUser().getPatronymic())));
        }

        return trainings;
    }

    @Override
    public List<TrainingTypeDto> getTrainingNames() {
        return trainingTypeRepository.findAllTrainingNameInfo();
    }
}
