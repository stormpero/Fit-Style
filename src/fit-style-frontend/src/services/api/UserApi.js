import api from "./Api";
import {
    URL_ALL_USERS, URL_ASK_RECOVER_WITH_EMAIL, URL_CONFIRM_RECOVERY, URL_DISABLE_USER, URL_ENABLE_USER, URL_IMG_USER,
    URL_ROLES,
    URL_SUBSCRIPTIONTYPE
} from "../../config/consts/urlsApi";

class UserApi {
    getAllUsers() {
        return api.get(URL_ALL_USERS);
    }
    getRoles() {
        return api.get(URL_ROLES);
    }
    getSubscriptionType() {
        return api.get(URL_SUBSCRIPTIONTYPE);
    }
    getUserImg(id) {
        return api.get(URL_IMG_USER + id, {responseType: 'blob'})
    }

    askForRecoverWithEmail(data) {
        return api.post(URL_ASK_RECOVER_WITH_EMAIL, data);
    }
    confirmRecovery(data) {
        return api.post(URL_CONFIRM_RECOVERY, data);
    }

    disableUser(id) {
        return api.get(URL_DISABLE_USER + id);
    }
    enableUser(id) {
        return api.get(URL_ENABLE_USER + id);
    }
}

export default new UserApi();
