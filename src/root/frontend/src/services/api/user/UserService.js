import api from "../config/Api";
import {
    URL_PROFILE_IMG,
    URL_PROFILE_INFO,
    URL_ROLES,
    URL_SUBSCRIPTIONTYPE,
    URL_USERTEST
} from "../../../config/consts/urlsApi";

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
}

export default new UserService();
