import api from "../config/Api";
import {
    URL_ADD_TRAINING_GROUP,
    URL_ADD_TRAINING_PERSONAL,
    URL_COACH_TRAININGS,
    URL_COACHES,
    URL_TRAINING
} from "../../../config/consts/urlsApi";

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

    addGroupTraining(data) {
        return api.post(URL_ADD_TRAINING_GROUP, data);
    }

    getTrainingsName() {
        return api.get("training/name");
    }
}

export default new ScheduleApi();