package ru.project.fitstyle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.project.fitstyle.model.entity.training.ETrainingStatus;
import ru.project.fitstyle.model.entity.user.FitUser;
import ru.project.fitstyle.model.repository.FitUserRepository;
import ru.project.fitstyle.model.repository.GroupTrainingRepository;
import ru.project.fitstyle.model.repository.PersonalTrainingRepository;
import ru.project.fitstyle.model.repository.TrainingRepository;
import ru.project.fitstyle.model.dto.training.GroupTrainingDto;
import ru.project.fitstyle.model.dto.training.PersonalTrainingDto;
import ru.project.fitstyle.model.dto.training.TrainingNameDto;
import ru.project.fitstyle.model.entity.training.GroupTraining;
import ru.project.fitstyle.model.entity.training.PersonalTraining;
import ru.project.fitstyle.model.entity.training.Training;
import ru.project.fitstyle.service.TrainingService;
import ru.project.fitstyle.service.exception.training.ExceededMaxPeopleTrainingException;
import ru.project.fitstyle.service.exception.training.TrainingNotFoundException;
import ru.project.fitstyle.service.exception.user.UserNotFoundException;

import java.util.List;

@Service
public class FitTrainingService implements TrainingService {
    private final TrainingRepository trainingRepository;
    private final GroupTrainingRepository groupTrainingRepository;
    private final PersonalTrainingRepository personalTrainingRepository;
    private final FitUserRepository fitUserRepository;

    @Autowired
    public FitTrainingService(final TrainingRepository trainingRepository,
                              final GroupTrainingRepository groupTrainingRepository,
                              final PersonalTrainingRepository personalTrainingRepository,
                              FitUserRepository fitUserRepository) {
        this.trainingRepository = trainingRepository;
        this.groupTrainingRepository = groupTrainingRepository;
        this.personalTrainingRepository = personalTrainingRepository;
        this.fitUserRepository = fitUserRepository;
    }

    @Override
    public void saveTraining(final Training training) {
        trainingRepository.save(training);
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
        if(groupTraining.getFitUsers().size() <= 100) {
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
        trainingRepository.deleteById(id);
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
    public Training getTrainingById(final Long id) {
        return trainingRepository.findById(id)
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

    @Override
    public List<TrainingNameDto> getTrainingNames() {
        return trainingRepository.findAllTrainingNameInfo();
    }
}
