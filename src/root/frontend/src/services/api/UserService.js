import api from "./Api";
import {
    URL_PROFILE_IMG,
    URL_PROFILE_INFO,
    URL_ROLES,
    URL_SUBSCRIPTIONTYPE,
    URL_TRAINING,
    URL_USERTEST
} from "../utils/consts/urlsApi";

class UserService {
    getUserBoard() {
        return api.get(URL_USERTEST);
    }

    getProfileInfo() {
        return api.get(URL_PROFILE_INFO);
    }

    getProfileImg() {
        return api.get(URL_PROFILE_IMG, {responseType: 'blob'})
    }

    getRoles() {
        return api.get(URL_ROLES);
    }

    getSubscriptionType() {
        return api.get(URL_SUBSCRIPTIONTYPE);
    }

    getTrainings() {
        return api.get(URL_TRAINING);
    }
}

export default new UserService();
