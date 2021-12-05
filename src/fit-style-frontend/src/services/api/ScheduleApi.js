import api from "./Api";
import {
    URL_ADD_TRAINING_GROUP,
    URL_ADD_TRAINING_PERSONAL,
    URL_COACH_TRAININGS,
    URL_COACHES, URL_DELETE_TRAINING_GROUP, URL_DELETE_TRAINING_PERSONAL,
    URL_TRAINING, URL_TRAINING_TYPES
} from "../../config/consts/urlsApi";

class ScheduleApi {
    getTrainings() {
        return api.get(URL_TRAINING);
    };

    getCoachesList() {
        return api.get(URL_COACHES);
    };

    getCoachTrainings(id = "") {
        return api.get(URL_COACH_TRAININGS + id);
    }

    addPersonalTraining(data) {
        return api.post(URL_ADD_TRAINING_PERSONAL, data);
    }

    deletePersonalTraining(id) {
        return api.get(URL_DELETE_TRAINING_PERSONAL + id)
    }

    addGroupTraining(data) {
        return api.post(URL_ADD_TRAINING_GROUP, data);
    }

    deleteGroupTraining(id) {
        return api.get(URL_DELETE_TRAINING_GROUP + id)
    }

    getTrainingsName() {
        return api.get(URL_TRAINING_TYPES);
    }
}

export default new ScheduleApi();