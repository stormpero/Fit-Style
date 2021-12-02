import api from "../config/Api";
import {URL_COACH_TRAININGS, URL_COACHES, URL_TRAINING} from "../../../config/consts/urlsApi";

class ScheduleApi {
    getTrainings() {
        return api.get(URL_TRAINING);
    };

    getCoachesList() {
        return api.get(URL_COACHES);
    };

    getCoachTrainings(id) {
        return api.get(URL_COACH_TRAININGS + id);
    }
}

export default new ScheduleApi();